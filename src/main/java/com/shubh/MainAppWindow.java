package com.shubh;

import javax.swing.*;
import java.awt.*;

/**
 * The main graphical user interface (GUI) window for the application.
 */
public class MainAppWindow {

    private RecognizerService recognizerService;
    private JButton toggleButton;
    private JTextArea outputArea;
    private JLabel statusLabel;
    private boolean isListening = false;

    public MainAppWindow() {
        // Create an instance of our recognizer service.
        // We pass it a method reference (this::onSpeechResult) which will be called
        // every time the recognizer has a result.
        recognizerService = new RecognizerService(this::onSpeechResult);

        // --- Create the UI Components ---
        JFrame frame = new JFrame("Voice Command Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea); // Allow scrolling

        statusLabel = new JLabel("Status: Idle");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        toggleButton = new JButton("Start Listening");
        toggleButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        // --- Set up the button's click action ---
        toggleButton.addActionListener(e -> {
            if (!isListening) {
                recognizerService.startListening();
                toggleButton.setText("Stop Listening");
                statusLabel.setText("Status: Listening...");
            } else {
                recognizerService.stopListening();
                toggleButton.setText("Start Listening");
                statusLabel.setText("Status: Idle");
            }
            isListening = !isListening;
        });

        // --- Arrange components in the window ---
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(toggleButton, BorderLayout.SOUTH);
        frame.getContentPane().add(statusLabel, BorderLayout.NORTH);

        // Make the window visible
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    /**
     * This method is called by the RecognizerService with recognized text.
     * It safely updates the GUI.
     * @param text The text to display.
     */
    private void onSpeechResult(String text) {
        // All UI updates must happen on the Event Dispatch Thread (EDT).
        // SwingUtilities.invokeLater ensures this is the case.
        SwingUtilities.invokeLater(() -> {
            outputArea.append(text + "\n");
        });
    }
}