package inf112.skeleton.app.grid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.model.entities.Entity;

public class Grid {
    private int rows;
    private int cols;
    private float cellWidth;
    private float cellHeight;
    private Entity[][] cells;

    public Grid(int rows, int cols, float gridWidth, float gridHeight) {
        this.rows = rows;
        this.cols = cols;
        this.cellWidth = gridWidth / cols;
        this.cellHeight = gridHeight / rows;
        this.cells = new Entity[rows][cols];
    }

    public void setEntity(int row, int col, Entity entity) {
        cells[row][col] = entity;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public float getCellWidth() {
        return cellWidth;
    }

    public float getCellHeight() {
        return cellHeight;
    }
}
