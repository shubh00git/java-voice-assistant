# Java Voice Command Assistant

A desktop voice assistant built from scratch with Java and the Sphinx4 library. It uses a custom grammar to recognize commands and control application functions through a clean user interface made with Java Swing.

---

## Demo

*[This is the perfect place to put a screenshot or an animated GIF of your application working!]*

![App Screenshot](https://i.imgur.com/example.png)

---

## Key Features

* Real-time voice recognition using a custom grammar.
* Graphical User Interface (GUI) built with Java Swing.
* Responds to commands like "tell me a joke" and "hello computer".
* Packaged as a standalone executable JAR file using Maven.

---

## Tech Stack

* **Language:** Java 17
* **UI:** Java Swing
* **Speech Recognition:** CMU Sphinx4
* **Build Tool:** Apache Maven

---

## How to Build and Run

1.  Clone the repository: `git clone https://github.com/shubh00git/java-voice-assistant.git`
2.  Navigate to the project directory: `cd java-voice-assistant`
3.  Run the application using the Maven exec plugin:
    ```bash
    mvn clean compile exec:java -Dexec.mainClass="com.shubh.SpeechRecognizerMain"
    ```

---

## Challenges & Learning

A major challenge in this project was overcoming a series of complex environment and build issues related to Java's classpath and library packaging. Debugging these problems provided a deep understanding of how Maven works and the importance of creating a robust, self-contained application.
