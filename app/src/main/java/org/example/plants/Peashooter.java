package org.example.plants;

import org.example.engine.GamePanel;
import org.example.engine.Tile;
import org.example.projectiles.Projectile;

public class Peashooter extends Plant {
    public static final int COST = 100; // Costo del lanzaguisantes
    private final GamePanel gamePanel;
    private final Tile currentTile;
    private final int damage = 20; // Daño del lanzaguisantes

    public Peashooter(GamePanel gamePanel, Tile currentTile) {
        super(100, 20, COST, 0); // Salud, daño, costo y cooldown
        this.gamePanel = gamePanel;
        this.currentTile = currentTile;
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                Thread.sleep(2000); // Dispara cada 2 segundos

                if (isAlive() && hasZombiesInLine()) {
                    System.out.println("Peashooter dispara un proyectil.");
                    Projectile projectile = new Projectile(currentTile, gamePanel, damage);

                    // Agregar el proyectil al Tile actual
                    currentTile.addProjectile(projectile);

                    // Iniciar el hilo del proyectil
                    Thread projectileThread = new Thread(projectile);
                    projectileThread.start();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private boolean hasZombiesInLine() {
        int row = currentTile.getRow();
        for (int col = currentTile.getCol(); col < gamePanel.getCols(); col++) {
            Tile tile = gamePanel.getTile(row, col);
            if (tile.getZombie() != null) {
                return true;
            }
        }
        return false;
    }
}
