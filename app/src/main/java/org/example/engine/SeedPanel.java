package org.example.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeedPanel extends JPanel {
    private String selectedPlant = null; // Planta seleccionada
    private final GamePanel gamePanel; // Referencia al GamePanel

    public SeedPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Guardar referencia al GamePanel
        setLayout(new FlowLayout());

        JButton sunflowerButton = new JButton("Girasol");
        sunflowerButton.addActionListener(e -> selectedPlant = "Sunflower");

        JButton peashooterButton = new JButton("Lanzaguisantes");
        peashooterButton.addActionListener(e -> selectedPlant = "Peashooter");

        add(sunflowerButton);
        add(peashooterButton);
    }

    public String getSelectedPlant() {
        return selectedPlant;
    }

    public void clearSelection() {
        selectedPlant = null;
    }
}
