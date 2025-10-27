package com.tetris.controller;

import com.tetris.model.*;
import com.tetris.view.GamePanel;
import com.tetris.view.StatusPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private Board board;
    private GameState gameState;
    private GamePanel gamePanel;
    private StatusPanel statusPanel;
    private Timer gameTimer;
    private AIController aiController;
    private boolean aiEnabled = false;

    public GameController(Board board, GameState gameState, GamePanel gamePanel, StatusPanel statusPanel) {
        this.board = board;
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.aiController = new AIController(board);
        
        initializeGameTimer();
    }

    private void initializeGameTimer() {
        gameTimer = new Timer(400, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gameState.isFallingFinished()) {
                    gameState.setFallingFinished(false);
                    newPiece();
                } else {
                    oneLineDown();
                }
            }
        });
    }

    public void startGame() {
        if (gameState.isPaused()) {
            return;
        }

        gameState.setStarted(true);
        gameState.setFallingFinished(false);
        gameState.setNumLinesRemoved(0);
        
        board.clearBoard();
        statusPanel.updateScore(0);
        statusPanel.updateStatus("游戏中");

        if (gameTimer != null) {
            gameTimer.stop();
        }

        gameTimer.start();
        newPiece();
    }

    public void pauseGame() {
        if (!gameState.isStarted()) {
            return;
        }

        gameState.setPaused(!gameState.isPaused());
        
        if (gameState.isPaused()) {
            gameTimer.stop();
            statusPanel.updateStatus("已暂停");
        } else {
            gameTimer.start();
            statusPanel.updateStatus("游戏中");
        }
    }

    public void toggleAI() {
        aiEnabled = !aiEnabled;
        if (aiEnabled) {
            statusPanel.updateStatus("AI模式");
            // AI会在游戏循环中自动操作
        } else {
            statusPanel.updateStatus("手动模式");
        }
    }

    private void newPiece() {
        gameState.setCurrentPiece(gameState.getNextPiece());
        gameState.getNextPiece().setRandomShape();
        gameState.setCurrentX(board.getBoardWidth() / 2);
        gameState.setCurrentY(board.getBoardHeight() - 1 + gameState.getCurrentPiece().minY());

        if (!board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY())) {
            gameState.getCurrentPiece().setShape(Shape.Tetrominoes.NoShape);
            gameTimer.stop();
            gameState.setStarted(false);
            statusPanel.updateStatus("游戏结束");
        }
        
        gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
        gamePanel.repaint();
    }

    private void oneLineDown() {
        if (!board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY() - 1)) {
            pieceDropped();
        } else {
            gameState.setCurrentY(gameState.getCurrentY() - 1);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    private void pieceDropped() {
        for (int i = 0; i < 4; i++) {
            int x = gameState.getCurrentX() + gameState.getCurrentPiece().x(i);
            int y = gameState.getCurrentY() - gameState.getCurrentPiece().y(i);
            board.setShapeAt(x, y, gameState.getCurrentPiece().getShape());
        }

        removeFullLines();

        if (!gameState.isFallingFinished()) {
            newPiece();
        }
    }

    private void removeFullLines() {
        int numFullLines = board.removeFullLines();
        
        if (numFullLines > 0) {
            gameState.addLinesRemoved(numFullLines);
            statusPanel.updateScore(gameState.getNumLinesRemoved());
            gameState.setFallingFinished(true);
            gameState.getCurrentPiece().setShape(Shape.Tetrominoes.NoShape);
            gamePanel.repaint();
        }
    }

    public void moveLeft() {
        if (!gameState.isStarted() || gameState.getCurrentPiece().getShape() == Shape.Tetrominoes.NoShape) {
            return;
        }

        if (board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX() - 1, gameState.getCurrentY())) {
            gameState.setCurrentX(gameState.getCurrentX() - 1);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    public void moveRight() {
        if (!gameState.isStarted() || gameState.getCurrentPiece().getShape() == Shape.Tetrominoes.NoShape) {
            return;
        }

        if (board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX() + 1, gameState.getCurrentY())) {
            gameState.setCurrentX(gameState.getCurrentX() + 1);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    public void rotateLeft() {
        if (!gameState.isStarted() || gameState.getCurrentPiece().getShape() == Shape.Tetrominoes.NoShape) {
            return;
        }

        Shape newPiece = gameState.getCurrentPiece().rotateLeft();
        if (board.tryMove(newPiece, gameState.getCurrentX(), gameState.getCurrentY())) {
            gameState.setCurrentPiece(newPiece);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    public void rotateRight() {
        if (!gameState.isStarted() || gameState.getCurrentPiece().getShape() == Shape.Tetrominoes.NoShape) {
            return;
        }

        Shape newPiece = gameState.getCurrentPiece().rotateRight();
        if (board.tryMove(newPiece, gameState.getCurrentX(), gameState.getCurrentY())) {
            gameState.setCurrentPiece(newPiece);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    public void dropDown() {
        if (!gameState.isStarted() || gameState.getCurrentPiece().getShape() == Shape.Tetrominoes.NoShape) {
            return;
        }

        int newY = gameState.getCurrentY();
        while (newY > 0) {
            if (!board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX(), newY - 1)) {
                break;
            }
            newY--;
        }

        pieceDropped();
    }

    public boolean isAIEnabled() {
        return aiEnabled;
    }

    public void performAIMove() {
        if (aiEnabled && gameState.isStarted() && !gameState.isPaused()) {
            aiController.makeMove(gameState);
            gamePanel.setCurrentPiece(gameState.getCurrentPiece(), gameState.getCurrentX(), gameState.getCurrentY());
            gamePanel.repaint();
        }
    }

    public void exitGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        System.exit(0);
    }
}