package org.example.projectiles;

import org.example.engine.GamePanel;
import org.example.engine.Tile;
import org.example.zombies.Zombie;

public class Projectile implements Runnable {
    private Tile currentTile;
    private final GamePanel gamePanel;
    private boolean active = true;
    private final int damage;

    public Projectile(Tile startTile, GamePanel gamePanel, int damage) {
        this.currentTile = startTile;
        this.gamePanel = gamePanel;
        this.damage = damage;
    }

    @Override
    public void run() {
        while (active) {
            try {
                Thread.sleep(500); // Moverse a 2 casillas por segundo

                synchronized (currentTile) {
                    // Verificar si hay un zombi en la casilla actual
                    Zombie zombie = currentTile.getZombie();
                    if (zombie != null) {
                        // Dañar al zombi y desaparecer
                        System.out.println("El proyectil impactó a un zombi.");
                        zombie.takeDamage(damage); // Infligir daño al zombi
                        active = false;
                        currentTile.removeProjectile(this);
                        break;
                    }

                    // Verificar si el proyectil ha llegado al final del mapa
                    Tile nextTile = currentTile.getNextTile(); // Obtener la casilla a la derecha
                    if (nextTile == null) {
                        // Si el proyectil sale del mapa, desaparece
                        System.out.println("El proyectil salió del mapa.");
                        active = false;
                        currentTile.removeProjectile(this);
                        break;
                    }

                    synchronized (nextTile) {
                        // Verificar si hay un zombi en la siguiente casilla
                        zombie = nextTile.getZombie();
                        if (zombie != null) {
                            // Dañar al zombi y desaparecer
                            System.out.println("El proyectil impactó a un zombi.");
                            zombie.takeDamage(damage); // Infligir daño al zombi
                            active = false;
                            currentTile.removeProjectile(this);
                            break;
                        }

                        // Mover el proyectil a la siguiente casilla
                        currentTile.removeProjectile(this);
                        currentTile = nextTile;
                        currentTile.addProjectile(this);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}