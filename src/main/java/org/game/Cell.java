package org.game;

class Cell {
    private final char type;
    private boolean marked;

    public Cell(char type) {
        this.type = type;
        this.marked = false;
    }

    public char getType() { return type; }
    public boolean isMarked() { return marked; }
    public void mark() { marked = true; }
}