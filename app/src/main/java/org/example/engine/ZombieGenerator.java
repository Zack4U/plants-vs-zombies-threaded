package org.example.engine;

import org.example.zombies.NormalZombie;
import org.example.zombies.Zombie;

import java.util.Random;

public class ZombieGenerator implements Runnable {
    private final GamePanel gamePanel;
    private final Random random = new Random();
    private boolean running = true;

    public ZombieGenerator(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        int startSleep = 30000; // 30 segundos
        try {
            Thread.sleep(startSleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        while (running) {
            try {
                // Generar un intervalo aleatorio entre 10 y 30 segundos
                int interval = 10000 + random.nextInt(20000);
                Thread.sleep(interval);

                // Generar un zombi en una fila aleatoria
                int row = random.nextInt(gamePanel.getRows());
                int col = gamePanel.getCols() - 1; // Columna de inicio (última columna)
                Tile startTile = gamePanel.getTile(row, col); // Primer tile de la fila
                Zombie zombie = new NormalZombie(startTile);

                // Iniciar el hilo del zombi
                Thread zombieThread = new Thread(zombie);
                zombieThread.start();

                System.out.println("Se ha generado un zombi normal en la fila " + row);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}