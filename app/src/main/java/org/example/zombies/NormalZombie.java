package org.example.zombies;

import org.example.engine.Tile;

public class NormalZombie extends Zombie {
    public NormalZombie(Tile startTile) {
        super(100, 25, 3500, startTile); // Salud: 100, Daño: 10, Velocidad: 1 segundo por movimiento
    }
}