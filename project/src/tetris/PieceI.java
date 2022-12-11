package tetris;

public class PieceI extends Tetromino{
    public PieceI() {
        cells[0] = new Cell(0, 4, LoadImage.pieceI);
        cells[1] = new Cell(0, 3, LoadImage.pieceI);
        cells[2] = new Cell(0, 5, LoadImage.pieceI);
        cells[3] = new Cell(0, 6, LoadImage.pieceI);

        // There are two rotate states for Piece I.
        rotateStates = new RotateState[2];
        // Initialize rotateStates
        rotateStates[0] = new RotateState(0, 0, 0, -1, 0, 1, 0, 2);
        rotateStates[1] = new RotateState(0, 0, -1, 0, 1, 0, 2, 0);
    }
}
