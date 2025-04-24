package org.example;

import javax.swing.SwingUtilities;
import org.example.engine.GameFrame;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameFrame();
        });
    }
}
