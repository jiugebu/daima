package com.tetris.model;

public class Board {
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private Shape.Tetrominoes[] board;

    public Board() {
        board = new Shape.Tetrominoes[BOARD_WIDTH * BOARD_HEIGHT];
        clearBoard();
    }

    public void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {
            board[i] = Shape.Tetrominoes.NoShape;
        }
    }

    public Shape.Tetrominoes shapeAt(int x, int y) {
        return board[y * BOARD_WIDTH + x];
    }

    public void setShapeAt(int x, int y, Shape.Tetrominoes shape) {
        board[y * BOARD_WIDTH + x] = shape;
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }

            if (shapeAt(x, y) != Shape.Tetrominoes.NoShape) {
                return false;
            }
        }

        return true;
    }

    public int removeFullLines() {
        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (shapeAt(j, i) == Shape.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                numFullLines++;
                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        setShapeAt(j, k, shapeAt(j, k + 1));
                    }
                }
            }
        }

        return numFullLines;
    }
}