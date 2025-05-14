package org.example;

import java.util.*;


public class Grid {
    private final int size;
    private Cell[][] cells;

    public Grid(int size) {
        this.size = size;
        initialize();
    }

    private void initialize() {
        cells = new Cell[size][size];
        Random rand = new Random();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cells[x][y] = new Cell((char) ('A' + rand.nextInt(5)));
            }
        }
    }

    public boolean isValidSwap(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 >= size || y1 < 0 || y1 >= size) return false;
        if (x2 < 0 || x2 >= size || y2 < 0 || y2 >= size) return false;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2) == 1;
    }

    public void swapCells(int x1, int y1, int x2, int y2) {
        Cell temp = cells[x1][y1];
        cells[x1][y1] = cells[x2][y2];
        cells[x2][y2] = temp;
    }

    public void processMatches(List<Match> matches) {
        matches.forEach(m ->
                m.positions().forEach(p ->
                        cells[p.x()][p.y()].mark()
                )
        );
    }

    public void refillGrid() {
        for (int x = 0; x < size; x++) {
            for (int y = size - 1; y >= 0; y--) {
                if (cells[x][y].isMarked()) {
                    cells[x][y] = new Cell((char) ('A' + new Random().nextInt(5)));
                }
            }
        }
    }

    public Cell getCell(int x, int y) { return cells[x][y]; }
    public int getSize() { return size; }

    public void print() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(getCell(x, y).getType() + " ");
            }
            System.out.println();
        }
    }
}

