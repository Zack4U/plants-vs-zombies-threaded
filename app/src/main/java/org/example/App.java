package org.example;

import javax.swing.SwingUtilities;
import org.example.engine.GameFrame;
import org.example.engine.ThreadManager;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();
        });
    }
}
