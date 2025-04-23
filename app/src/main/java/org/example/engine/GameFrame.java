package org.example.engine;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final SeedPanel seedPanel; // Referencia al SeedPanel
    private final SunCollector sunCollector; // Referencia al SunCollector
    private final PassiveSunGenerator passiveSunGenerator; // Generador de soles pasivo
    private final Thread passiveSunThread; // Hilo para el generador de soles pasivo
    private final ZombieGenerator zombieGenerator;
    private final Thread zombieGeneratorThread;

    public GameFrame() {
        setTitle("Plantas vs Zombis - Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        GamePanel gamePanel = new GamePanel();
        seedPanel = new SeedPanel(gamePanel);
        ToolPanel toolPanel = new ToolPanel(gamePanel);
        sunCollector = new SunCollector();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(seedPanel, BorderLayout.CENTER);
        topPanel.add(sunCollector, BorderLayout.WEST); // Agregar el recolector de soles al lado derecho

        add(gamePanel, BorderLayout.CENTER);
        add(toolPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Crear el generador pasivo de soles
        passiveSunGenerator = new PassiveSunGenerator(sunCollector);
        passiveSunThread = new Thread(passiveSunGenerator);
        passiveSunThread.start(); // Iniciar el hilo
        ThreadManager.registerThread(passiveSunThread); // Registrar el hilo en el ThreadManager
        ThreadManager.getThreads();

        pack();
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);

        zombieGenerator = new ZombieGenerator(gamePanel);
        zombieGeneratorThread = new Thread(zombieGenerator);
        zombieGeneratorThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }

    public SeedPanel getSeedPanel() {
        return this.seedPanel;
    }

    public SunCollector getSunCollector() {
        return this.sunCollector;
    }

    @Override
    public void dispose() {
        zombieGenerator.stop();
        zombieGeneratorThread.interrupt();
        super.dispose();
    }
}
