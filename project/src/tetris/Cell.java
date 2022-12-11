package tetris;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
* Cell is the base class for game pieces(Tetromino)
 * */
public class Cell {
    private int row;
    private int col;
    private BufferedImage image;

    public Cell() {
    }

    public Cell(int row, int col, BufferedImage image) {
        this.row = row;
        this.col = col;
        this.image = image;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col && Objects.equals(image, cell.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, image);
    }

    // Move cell to left.
    public void left() {
        col--;
    }

    // Move cell to right.
    public void right() {
        col++;
    }

    // Move cell down.
    public void drop() {
        row ++;
    }
}
