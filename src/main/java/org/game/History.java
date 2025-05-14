package org.game;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<String> moves = new ArrayList<>();

    public void recordMove(String input) {
        moves.add(input);
    }
}
