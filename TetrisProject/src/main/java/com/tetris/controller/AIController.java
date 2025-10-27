package com.tetris.controller;

import com.tetris.model.Board;
import com.tetris.model.GameState;
import com.tetris.model.Shape;

public class AIController {
    private Board board;

    public AIController(Board board) {
        this.board = board;
    }

    public void makeMove(GameState gameState) {
        // 简单的AI算法：尝试所有可能的移动，选择最佳位置
        Shape currentPiece = gameState.getCurrentPiece();
        int bestX = gameState.getCurrentX();
        int bestRotation = 0;
        double bestScore = Double.NEGATIVE_INFINITY;

        // 尝试所有旋转
        for (int rotation = 0; rotation < 4; rotation++) {
            Shape testPiece = currentPiece;
            for (int r = 0; r < rotation; r++) {
                testPiece = testPiece.rotateRight();
            }

            // 尝试所有水平位置
            for (int x = 0; x < board.getBoardWidth(); x++) {
                int y = findDropHeight(testPiece, x, gameState.getCurrentY());
                
                if (y >= 0) {
                    double score = evaluatePosition(testPiece, x, y);
                    if (score > bestScore) {
                        bestScore = score;
                        bestX = x;
                        bestRotation = rotation;
                    }
                }
            }
        }

        // 执行最佳移动
        executeBestMove(gameState, bestX, bestRotation);
    }

    private int findDropHeight(Shape piece, int x, int startY) {
        int y = startY;
        while (y > 0 && board.tryMove(piece, x, y - 1)) {
            y--;
        }
        return y;
    }

    private double evaluatePosition(Shape piece, int x, int y) {
        // 简单的评估函数：考虑高度、完整行数和空洞数
        double score = 0;
        
        // 偏好较低的位置
        score -= y * 0.5;
        
        // 检查是否会形成完整行
        int potentialLines = checkPotentialLines(piece, x, y);
        score += potentialLines * 100;
        
        // 避免创建空洞
        int holesCreated = checkHolesCreated(piece, x, y);
        score -= holesCreated * 50;
        
        return score;
    }

    private int checkPotentialLines(Shape piece, int x, int y) {
        // 模拟放置方块后检查完整行
        int lines = 0;
        // 简化实现：实际需要模拟放置并检查
        return lines;
    }

    private int checkHolesCreated(Shape piece, int x, int y) {
        // 检查放置后创建的空洞数量
        int holes = 0;
        // 简化实现
        return holes;
    }

    private void executeBestMove(GameState gameState, int targetX, int targetRotation) {
        // 旋转到最佳方向
        for (int i = 0; i < targetRotation; i++) {
            Shape rotated = gameState.getCurrentPiece().rotateRight();
            if (board.tryMove(rotated, gameState.getCurrentX(), gameState.getCurrentY())) {
                gameState.setCurrentPiece(rotated);
            }
        }

        // 移动到最佳水平位置
        while (gameState.getCurrentX() != targetX) {
            if (gameState.getCurrentX() < targetX) {
                if (board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX() + 1, gameState.getCurrentY())) {
                    gameState.setCurrentX(gameState.getCurrentX() + 1);
                }
            } else {
                if (board.tryMove(gameState.getCurrentPiece(), gameState.getCurrentX() - 1, gameState.getCurrentY())) {
                    gameState.setCurrentX(gameState.getCurrentX() - 1);
                }
            }
        }

        // 快速下落
        gameState.setCurrentY(findDropHeight(gameState.getCurrentPiece(), 
                                           gameState.getCurrentX(), 
                                           gameState.getCurrentY()));
    }
}