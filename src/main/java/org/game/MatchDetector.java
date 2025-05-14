package org.game;

import java.util.ArrayList;
import java.util.List;

public class MatchDetector {
    public List<Match> findMatches(Grid grid) {
        List<Match> matches = new ArrayList<>();
        int size = grid.getSize();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size - 2; x++) {
                char current = grid.getCell(x, y).getType();
                if (current == grid.getCell(x + 1, y).getType() &&
                        current == grid.getCell(x + 2, y).getType()) {

                    List<Position> positions = new ArrayList<>();
                    int endX = x + 2;
                    while (endX + 1 < size &&
                            current == grid.getCell(endX + 1, y).getType()) {
                        endX++;
                    }
                    for (int i = x; i <= endX; i++) {
                        positions.add(new Position(i, y));
                    }
                    matches.add(new Match(positions, current));
                    x = endX;
                }
            }
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size - 2; y++) {
                char current = grid.getCell(x, y).getType();
                if (current == grid.getCell(x, y + 1).getType() &&
                        current == grid.getCell(x, y + 2).getType()) {

                    List<Position> positions = new ArrayList<>();
                    int endY = y + 2;
                    while (endY + 1 < size &&
                            current == grid.getCell(x, endY + 1).getType()) {
                        endY++;
                    }
                    for (int i = y; i <= endY; i++) {
                        positions.add(new Position(x, i));
                    }
                    matches.add(new Match(positions, current));
                    y = endY;
                }
            }
        }

        return matches;
    }
}
