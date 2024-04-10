package inf112.skeleton.app.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.model.entities.Entity;

public class Grid {
    private int rows;
    private int cols;
    private float cellWidth;
    private float cellHeight;
    private Entity[][] cells;

    /**
     * Constructs a new Grid with the specified number of rows and columns,
     * and dimensions for the grid.
     *
     * @param rows       the number of rows in the grid
     * @param cols       the number of columns in the grid
     * @param gridWidth  the width of the grid
     * @param gridHeight the height of the grid
     */
    public Grid(int rows, int cols, float gridWidth, float gridHeight) {
        this.rows = rows;
        this.cols = cols;
        this.cellWidth = gridWidth / cols;
        this.cellHeight = gridHeight / rows;
        this.cells = new Entity[rows][cols];
    }

    /**
     * Sets the entity at the specified position in the grid.
     *
     * @param row    the row index of the cell
     * @param col    the column index of the cell
     * @param entity the entity to be set
     */
    public void setEntity(int row, int col, Entity entity) {
        cells[row][col] = entity;
    }

    /**
     * Gets the number of rows in the grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the width of each cell in the grid.
     *
     * @return the width of each cell
     */
    public float getCellWidth() {
        return cellWidth;
    }

    /**
     * Gets the height of each cell in the grid.
     *
     * @return the height of each cell
     */
    public float getCellHeight() {
        return cellHeight;
    }
}
