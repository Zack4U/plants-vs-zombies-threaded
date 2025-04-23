package org.example.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private final int rows = 5;
    private final int cols = 9;
    private final Tile[][] tiles;
    private boolean shovelMode = false; // Estado del modo "Quitar Planta"

    public GamePanel() {
        setLayout(new GridLayout(rows, cols));
        tiles = new Tile[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile tile = new Tile(row, col);
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleTileClick(tile);
                    }
                });
                tiles[row][col] = tile;
                add(tile);
            }
        }
    }

    public Tile getTile(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return tiles[row][col];
        }
        return null;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setShovelMode(boolean shovelMode) {
        this.shovelMode = shovelMode; // Activar/desactivar el modo "Quitar Planta"
    }

    private void handleTileClick(Tile tile) {
        if (shovelMode) {
            tile.removePlant(); // Quitar planta si el modo está activo
            shovelMode = false; // Desactivar el modo después de usarlo
        } else {
            SeedPanel seedPanel = ((GameFrame) SwingUtilities.getWindowAncestor(this)).getSeedPanel();
            String selectedPlant = seedPanel.getSelectedPlant();

            if (selectedPlant != null) {
                SunCollector sunCollector = ((GameFrame) SwingUtilities.getWindowAncestor(this)).getSunCollector();
                if (sunCollector == null) {
                    System.err.println("SunCollector es null. Verifica su inicialización en GameFrame.");
                    return;
                }
                int availableSuns = sunCollector.getSunCount();

                tile.placePlant(selectedPlant, sunCollector, availableSuns);
                seedPanel.clearSelection();
            }
        }
    }
}
