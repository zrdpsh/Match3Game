package org.game;

import java.util.Optional;

public class InputHandler {
    public Optional<Move> parseInput(String input) {
        try {
            String[] parts = input.trim().split("\\s+");
            if (parts.length != 4) return Optional.empty();
            int x1 = Integer.parseInt(parts[0]);
            int y1 = Integer.parseInt(parts[1]);
            int x2 = Integer.parseInt(parts[2]);
            int y2 = Integer.parseInt(parts[3]);
            return Optional.of(new Move(new Position(x1, y1), new Position(x2, y2)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
