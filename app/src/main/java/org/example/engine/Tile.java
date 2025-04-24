package org.example.engine;

import javax.swing.*;

import org.example.plants.Plant;
import org.example.plants.Sunflower;
import org.example.plants.Peashooter;
import org.example.zombies.Zombie;
import org.example.projectiles.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tile extends JPanel {
    private final int row;
    private final int col;
    private Plant plant = null;
    private Zombie zombie = null;
    private final List<Projectile> projectiles = new ArrayList<>(); // Lista de proyectiles en la casilla

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.GREEN);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Plant getPlant() {
        return plant;
    }

    public Plant getNextPlant() {
        GamePanel gamePanel = (GamePanel) SwingUtilities.getAncestorOfClass(GamePanel.class, this);
        if (gamePanel != null) {
            return gamePanel.getTile(row, col + 1).getPlant(); // Obtener la planta de la casilla a la derecha
        }
        return null;
    }

    public void placePlant(String plantType, SunCollector sunCollector, int availableSuns) {
        if (plant == null) {
            int plantCost = 0;

            // Obtener el costo de la planta antes de crearla
            if (plantType.equals("Sunflower")) {
                plantCost = Sunflower.COST; // Usar una constante estática para el costo
            } else if (plantType.equals("Peashooter")) {
                plantCost = Peashooter.COST; // Usar una constante estática para el costo
            } else {
                System.out.println("Tipo de planta no válido: " + plantType);
                return; // Salir si el tipo de planta no es válido
            }

            // Verificar si hay suficientes soles
            if (availableSuns >= plantCost) {
                // Crear la planta solo si hay suficientes soles
                if (plantType.equals("Sunflower")) {
                    plant = new Sunflower(sunCollector);
                }

                if (plantType.equals("Peashooter")) {
                    GamePanel gamePanel = (GamePanel) SwingUtilities.getAncestorOfClass(GamePanel.class, this);
                    if (gamePanel != null) {
                        plant = new Peashooter(gamePanel, this); // Pasar el GamePanel y el Tile actual
                    }
                }

                sunCollector.removeSun(plantCost); // Restar el costo de los soles

                repaint();
            } else {
                System.out.println("No hay suficientes soles para plantar " + plantType);
            }
        } else {
            System.out.println("Ya hay una planta en esta celda.");
        }
    }

    public void removePlant() {
        if (plant != null) {
            plant.onDeath(); // Detener los hilos asociados a la planta
            plant = null;
            repaint();
        }
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void placeZombie(Zombie zombie) {
        if (zombie != null && zombie.isAlive()) {
            this.zombie = zombie;
            repaint();
        }
    }

    public void removeZombie() {
        this.zombie = null;
        repaint();
    }

    public Tile getPreviousTile() {
        GamePanel gamePanel = (GamePanel) SwingUtilities.getAncestorOfClass(GamePanel.class, this);
        if (gamePanel != null) {
            return gamePanel.getTile(row, col - 1); // Obtener la casilla a la derecha
        }
        return null;
    }

    public Tile getNextTile() {
        GamePanel gamePanel = (GamePanel) SwingUtilities.getAncestorOfClass(GamePanel.class, this);
        if (gamePanel != null) {
            return gamePanel.getTile(row, col + 1); // Obtener la casilla a la derecha
        }
        return null;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
        repaint();
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la planta si existe
        if (plant != null) {
            g.setColor(Color.BLACK);
            g.drawString(plant.getClass().getSimpleName(), getWidth() / 2 - 20, getHeight() / 2);
            g.setColor(Color.YELLOW);
        }

        // Dibujar el zombi si existe
        if (zombie != null) {
            setBackground(Color.RED); // Cambiar el fondo a rojo
            g.setColor(Color.WHITE);
            g.drawString(zombie.getClass().getSimpleName(), getWidth() / 2 - 20, getHeight() / 2);
        } else {
            setBackground(Color.GREEN); // Restaurar el fondo a verde si no hay zombi
        }

        // Dibujar los proyectiles
        g.setColor(Color.BLUE);
        for (Projectile projectile : projectiles) {
            g.fillOval(getWidth() / 2 - 5, getHeight() / 2 - 5, 10, 10);
        }
    }
}
