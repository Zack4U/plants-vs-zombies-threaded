package org.example.plants;

import javax.swing.*;
import java.util.Random;

import org.example.engine.SunCollector;

public class Sunflower extends Plant {
    public static final int COST = 50;

    private final SunCollector sunCollector;
    private final int sunProductionRate;
    private final int sunProductionAmount;

    public Sunflower(SunCollector sunCollector) {
        super(100, 0, 50, 0);
        this.sunCollector = sunCollector;
        this.sunProductionRate = 5; // Producción de sol cada 5 segundos
        this.sunProductionAmount = 25; // Cantidad de sol producida
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                Thread.sleep(this.sunProductionRate * 1000); // Esperar el tiempo de producción
                if (isAlive()) {
                    this.sunCollector.addSun(this.sunProductionAmount);
                    System.out.println("Girasol produjo un sol.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Detener el hilo si es interrumpido
            }
        }
    }
}
