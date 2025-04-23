package org.example.engine;

import java.util.Random;

public class PassiveSunGenerator implements Runnable {
    private final SunCollector sunCollector;
    private final Random random = new Random();
    private boolean running = true;

    public PassiveSunGenerator(SunCollector sunCollector) {
        this.sunCollector = sunCollector;
    }

    public void stop() {
        running = false; // Detener el hilo
    }

    @Override
    public void run() {
        while (running) {
            try {
                // Generar un intervalo aleatorio entre 10 y 15 segundos
                int interval = 10000 + random.nextInt(5000);
                Thread.sleep(interval);

                // Generar 25 soles y notificar por consola
                sunCollector.addSun(25);
                System.out.println("Generación pasiva: Se han añadido 25 soles.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Marcar el hilo como interrumpido
                break;
            }
        }
    }
}