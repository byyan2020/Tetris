package tetris;

public class PieceO extends Tetromino{
    public PieceO() {
        cells[0] = new Cell(0, 4, LoadImage.pieceO);
        cells[1] = new Cell(0, 5, LoadImage.pieceO);
        cells[2] = new Cell(1, 4, LoadImage.pieceO);
        cells[3] = new Cell(1, 5, LoadImage.pieceO);

        // There are 4 rotate states for Piece O.
        rotateStates = new RotateState[0];
    }
}
