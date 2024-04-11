package inf112.skeleton.app.grid;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inf112.skeleton.app.model.entities.Entity; // Ensure this import matches your actual Entity class import

class GridTest {
    private Grid grid;
    private int rows = 10;
    private int cols = 10;
    private float gridWidth = 200.0f;
    private float gridHeight = 100.0f;

    @BeforeEach
    void setUp() {
        grid = new Grid(rows, cols, gridWidth, gridHeight);
    }

    @Test
    void testGridInitialization() {
        assertEquals(rows, grid.getRows(), "Grid rows not initialized correctly.");
        assertEquals(cols, grid.getCols(), "Grid columns not initialized correctly.");
        assertEquals(gridWidth / cols, grid.getCellWidth(), "Grid cell width not calculated correctly.");
        assertEquals(gridHeight / rows, grid.getCellHeight(), "Grid cell height not calculated correctly.");
    }
    // bruker ATM ikke grid:/
}

