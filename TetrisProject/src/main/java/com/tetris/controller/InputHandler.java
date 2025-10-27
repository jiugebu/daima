package com.tetris.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private GameController gameController;

    public InputHandler(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameController.isAIEnabled()) { // AI模式下禁用手动控制
            int keycode = e.getKeyCode();
            
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    gameController.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    gameController.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    gameController.rotateRight();
                    break;
                case KeyEvent.VK_UP:
                    gameController.rotateLeft();
                    break;
                case KeyEvent.VK_SPACE:
                    gameController.dropDown();
                    break;
                case KeyEvent.VK_P:
                    gameController.pauseGame();
                    break;
                case KeyEvent.VK_A:
                    gameController.toggleAI();
                    break;
            }
        }
    }
}