package org.game;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    private final Grid grid;
    private final MatchDetector detector;
    private final ScoreSystem scoreSystem;
    private final History history;
    private final BonusManager bonusManager;

    public Game() {
        this.grid = new Grid(8);
        this.detector = new MatchDetector();
        this.scoreSystem = new ScoreSystem();
        this.history = new History();
        this.bonusManager = new BonusManager();
    }

    public void processTurn(String input) {
        InputHandler handler = new InputHandler();
        Optional<Move> move = handler.parseInput(input);

        if (move.isPresent()) {
            Position from = move.get().from();
            Position to = move.get().to();

            if (grid.isValidSwap(from.x(), from.y(), to.x(), to.y())) {
                grid.swapCells(from.x(), from.y(), to.x(), to.y());

                List<Match> matches = detector.findMatches(grid);
                if (!matches.isEmpty()) {
                    scoreSystem.addPoints(calculateScore(matches));
                    bonusManager.applyBonuses(matches, grid);
                    grid.processMatches(matches);
                    grid.refillGrid();
                    history.recordMove(input);
                } else {
                    grid.swapCells(from.x(), from.y(), to.x(), to.y());
                }
            }
        }
    }

    private int calculateScore(List<Match> matches) {
        return matches.stream().mapToInt(m -> m.positions().size() * 10).sum();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Current Grid:");
            grid.print();
            System.out.println("Enter move (x1 y1 x2 y2) or exit out: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            processTurn(input);
            System.out.println("Score: " + scoreSystem.getScore());
        }
    }

    public static void main(String[] args) {
        new Game().run();
    }
}
