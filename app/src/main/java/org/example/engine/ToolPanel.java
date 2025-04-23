package org.example.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {
    private boolean shovelActive = false; // Estado de la herramienta
    private final GamePanel gamePanel; // Referencia al GamePanel

    public ToolPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Guardar referencia al GamePanel
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(Color.GRAY);

        JButton shovelBtn = new JButton("🧹 Quitar Planta");
        shovelBtn.addActionListener(e -> toggleShovelMode());
        add(shovelBtn);
    }

    private void toggleShovelMode() {
        shovelActive = !shovelActive; // Alternar el estado de la herramienta
        gamePanel.setShovelMode(shovelActive); // Informar al GamePanel
    }
}
