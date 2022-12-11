package tetris;

public class PieceT extends Tetromino{
    public PieceT() {
        cells[0] = new Cell(0, 4, LoadImage.pieceT);
        cells[1] = new Cell(0, 3, LoadImage.pieceT);
        cells[2] = new Cell(0, 5, LoadImage.pieceT);
        cells[3] = new Cell(1, 4, LoadImage.pieceT);

        // There are 4 rotate states for Piece T.
        rotateStates = new RotateState[4];
        // Initialize rotateStates.
        rotateStates[0] = new RotateState(0, 0, 0, -1, 0, 1, 1, 0);
        rotateStates[1] = new RotateState(0, 0, -1, 0, 1, 0, 0, -1);
        rotateStates[2] = new RotateState(0, 0, 0, 1, 0, -1, -1, 0);
        rotateStates[3] = new RotateState(0, 0, 1, 0, -1, 0, 0, 1);
    }
}
