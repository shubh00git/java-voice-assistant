package com.shubh;

import javax.swing.SwingUtilities;

/**
 * The main entry point of the application. Its only job is to launch the GUI.
 */
public class SpeechRecognizerMain {
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread (EDT) for thread safety.
        SwingUtilities.invokeLater(MainAppWindow::new);
    }
}