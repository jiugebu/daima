package com.tetris.view;

import com.tetris.model.Board;
import com.tetris.model.Shape;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int BLOCK_SIZE = 30;
    private Board board;
    private Shape currentPiece;
    private int currentX;
    private int currentY;

    public GamePanel(Board board) {
        this.board = board;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(
            board.getBoardWidth() * BLOCK_SIZE, 
            board.getBoardHeight() * BLOCK_SIZE
        ));
    }

    public void setCurrentPiece(Shape piece, int x, int y) {
        this.currentPiece = piece;
        this.currentX = x;
        this.currentY = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        
        if (currentPiece != null) {
            drawCurrentPiece(g);
        }
    }

    private void drawBoard(Graphics g) {
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - board.getBoardHeight() * BLOCK_SIZE;

        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                Shape.Tetrominoes shape = board.shapeAt(j, board.getBoardHeight() - i - 1);
                if (shape != Shape.Tetrominoes.NoShape) {
                    drawSquare(g, j * BLOCK_SIZE, boardTop + i * BLOCK_SIZE, shape);
                }
            }
        }
    }

    private void drawCurrentPiece(Graphics g) {
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - board.getBoardHeight() * BLOCK_SIZE;

        for (int i = 0; i < 4; i++) {
            int x = currentX + currentPiece.x(i);
            int y = currentY - currentPiece.y(i);
            drawSquare(g, x * BLOCK_SIZE, 
                      boardTop + (board.getBoardHeight() - y - 1) * BLOCK_SIZE, 
                      currentPiece.getShape());
        }
    }

    private void drawSquare(Graphics g, int x, int y, Shape.Tetrominoes shape) {
        Color colors[] = {
            new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };
        
        Color color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
        
        g.setColor(color.brighter());
        g.drawLine(x, y + BLOCK_SIZE - 1, x, y);
        g.drawLine(x, y, x + BLOCK_SIZE - 1, y);
        
        g.setColor(color.darker());
        g.drawLine(x + 1, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
        g.drawLine(x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y + 1);
    }
}