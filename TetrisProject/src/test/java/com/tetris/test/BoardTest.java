package com.tetris.test;

import com.tetris.model.Board;
import com.tetris.model.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    
    @Test
    public void testBoardCreation() {
        Board board = new Board();
        assertNotNull(board);
        assertEquals(10, board.getBoardWidth());
        assertEquals(20, board.getBoardHeight());
    }
    
    @Test
    public void testClearBoard() {
        Board board = new Board();
        board.clearBoard();
        
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
                assertEquals(Shape.Tetrominoes.NoShape, board.shapeAt(j, i));
            }
        }
    }
    
    @Test
    public void testTryMove() {
        Board board = new Board();
        Shape shape = new Shape();
        shape.setShape(Shape.Tetrominoes.SquareShape);
        
        // 测试在空板上移动应该成功
        assertTrue(board.tryMove(shape, 5, 19));
        
        // 测试超出边界应该失败
        assertFalse(board.tryMove(shape, -1, 19));
        assertFalse(board.tryMove(shape, 10, 19));
        assertFalse(board.tryMove(shape, 5, -1));
        assertFalse(board.tryMove(shape, 5, 20));
    }
}