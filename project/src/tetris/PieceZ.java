package tetris;

public class PieceZ extends Tetromino{
    public PieceZ() {
        cells[0] = new Cell(1, 4, LoadImage.pieceZ);
        cells[1] = new Cell(0, 3, LoadImage.pieceZ);
        cells[2] = new Cell(0, 4, LoadImage.pieceZ);
        cells[3] = new Cell(1, 5, LoadImage.pieceZ);

        // There are 4 rotate states for Piece Z.
        rotateStates = new RotateState[2];
        // Initialize rotateStates
        rotateStates[0] = new RotateState(0, 0, -1, -1, -1, 0, 0, 1);
        rotateStates[1] = new RotateState(0, 0, -1, 1, 0, 1, 1, 0);
    }
}
