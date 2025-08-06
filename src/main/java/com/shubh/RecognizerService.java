package com.shubh;

import java.util.function.Consumer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

/**
 * A dedicated class to handle all speech recognition logic.
 */
public class RecognizerService {

    private LiveSpeechRecognizer recognizer;
    private volatile boolean isListening = false;
    private Thread listeningThread;

    // This "Consumer" is a callback that allows us to send recognized text back to the UI.
    private final Consumer<String> onSpeechResult;

    public RecognizerService(Consumer<String> onSpeechResult) {
        this.onSpeechResult = onSpeechResult;
        initialize();
    }

    private void initialize() {
        try {
            Configuration configuration = new Configuration();
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setGrammarPath("resource:/");
            configuration.setGrammarName("commands");
            configuration.setUseGrammar(true);

            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (Exception e) {
            // Send error message to the UI
            onSpeechResult.accept("Error initializing recognizer: " + e.getMessage());
        }
    }

    public void startListening() {
        if (isListening) return;

        isListening = true;
        // Run the recognizer in a separate thread so it doesn't freeze the UI
        listeningThread = new Thread(() -> {
            recognizer.startRecognition(true);
            onSpeechResult.accept("Recognizer is ready. Please say a command...");

            while (isListening) {
                SpeechResult result = recognizer.getResult();
                if (result != null) {
                    String command = result.getHypothesis();
                    onSpeechResult.accept("You said: " + command); // Send recognized text to UI
                }
            }
            recognizer.stopRecognition();
            onSpeechResult.accept("Listening stopped.");
        });
        listeningThread.start();
    }

    public void stopListening() {
        isListening = false;
    }
}