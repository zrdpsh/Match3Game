package org.example;

public class ScoreSystem {
    private int score = 0;

    public void addPoints(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }
}
