package org.example.engine;

import javax.swing.*;
import java.awt.*;

public class SunCollector extends JPanel {
    private int sunCount = 0; // Contador de soles
    private final JLabel sunLabel;

    public SunCollector() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.YELLOW);

        sunLabel = new JLabel("Soles: 0");
        sunLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(sunLabel);
    }

    public synchronized void addSun(int amount) {
        sunCount += amount;
        SwingUtilities.invokeLater(() -> sunLabel.setText("Soles: " + sunCount));
    }

    public synchronized int getSunCount() {
        return sunCount;
    }

    public synchronized void removeSun(int amount) {
        if (sunCount >= amount) {
            sunCount -= amount;
            SwingUtilities.invokeLater(() -> sunLabel.setText("Soles: " + sunCount));
        } else {
            System.out.println("No hay suficientes soles para eliminar.");
        }
    }
}