package tetris;

public class PieceS extends Tetromino{
    public PieceS() {
        cells[0] = new Cell(0, 4, LoadImage.pieceS);
        cells[1] = new Cell(0, 5, LoadImage.pieceS);
        cells[2] = new Cell(1, 3, LoadImage.pieceS);
        cells[3] = new Cell(1, 4, LoadImage.pieceS);

        // There are 4 rotate states for Piece S.
        rotateStates = new RotateState[2];
        // Initialize rotateStates
        rotateStates[0] = new RotateState(0, 0, 0, 1, 1, -1, 1, 0);
        rotateStates[1] = new RotateState(0, 0, 1, 0, -1, -1, 0, -1);
    }
}
