package com.tetris.model;

public class GameState {
    private boolean isFallingFinished = false;
    private boolean isStarted = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int currentX = 0;
    private int currentY = 0;
    private Shape currentPiece = new Shape();
    private Shape nextPiece = new Shape();

    public boolean isFallingFinished() {
        return isFallingFinished;
    }

    public void setFallingFinished(boolean fallingFinished) {
        isFallingFinished = fallingFinished;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public int getNumLinesRemoved() {
        return numLinesRemoved;
    }

    public void setNumLinesRemoved(int numLinesRemoved) {
        this.numLinesRemoved = numLinesRemoved;
    }

    public void addLinesRemoved(int lines) {
        this.numLinesRemoved += lines;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public Shape getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Shape currentPiece) {
        this.currentPiece = currentPiece;
    }

    public Shape getNextPiece() {
        return nextPiece;
    }

    public void setNextPiece(Shape nextPiece) {
        this.nextPiece = nextPiece;
    }
}