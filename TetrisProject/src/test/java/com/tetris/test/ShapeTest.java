package com.tetris.test;

import com.tetris.model.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {
    
    @Test
    public void testShapeCreation() {
        Shape shape = new Shape();
        assertNotNull(shape);
    }
    
    @Test
    public void testShapeRotation() {
        Shape shape = new Shape();
        shape.setShape(Shape.Tetrominoes.TShape);
        
        Shape rotatedLeft = shape.rotateLeft();
        assertNotNull(rotatedLeft);
        
        Shape rotatedRight = shape.rotateRight();
        assertNotNull(rotatedRight);
    }
    
    @Test
    public void testRandomShape() {
        Shape shape = new Shape();
        shape.setRandomShape();
        
        assertNotNull(shape.getShape());
        assertNotEquals(Shape.Tetrominoes.NoShape, shape.getShape());
    }
}