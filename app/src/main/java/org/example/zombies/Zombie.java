package org.example.zombies;

import org.example.engine.ThreadManager;
import org.example.engine.Tile;
import org.example.plants.Plant;

public abstract class Zombie implements Runnable {
    protected int health;
    protected int damage;
    protected int speed; // Velocidad en milisegundos entre movimientos
    protected boolean isAlive = true;
    protected Tile currentTile;

    public Zombie(int health, int damage, int speed, Tile startTile) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.currentTile = startTile;

        // Marcar al zombi en la casilla inicial
        if (currentTile != null) {
            currentTile.placeZombie(this);
        }
    }

    public synchronized void takeDamage(int damage) {
        this.health -= damage;
        System.err.println(
                getClass().getSimpleName() + " ha recibido " + damage + " de daño. Salud restante: " + this.health);
        if (this.health <= 0) {
            this.isAlive = false;
            ThreadManager.unregisterThread(Thread.currentThread());
            Thread.currentThread().interrupt(); // Interrumpir el hilo
            System.out.println(getClass().getSimpleName() + " ha muerto.");
            if (currentTile != null) {
                currentTile.removeZombie();
            }
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void run() {
        while (isAlive) {
            try {
                Thread.sleep(speed + 100);
                if (currentTile != null) {
                    Plant plant = currentTile.getPlant();
                    if (plant != null) {
                        // Atacar la planta
                        plant.takeDamage(damage);
                        System.out.println("Zombi ataca a " + plant.getClass().getSimpleName());
                        if (!plant.isAlive()) {
                            currentTile.removePlant();
                        }
                    } else {
                        // Mover al siguiente tile
                        Tile nextTile = currentTile.getPreviousTile(); // Método para obtener el siguiente tile
                        if (nextTile != null) {
                            currentTile.removeZombie(); // Quitar el zombi de la casilla actual
                            currentTile = nextTile;
                            currentTile.placeZombie(this); // Marcar al zombi en la nueva casilla
                        } else {
                            // Si el zombi llega al final, es Game Over
                            System.out.println("¡Game Over! Un zombi ha llegado al final.");
                            ThreadManager.stopAllThreads(); // Detener todos los hilos
                            System.exit(0); // Salir del programa
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}