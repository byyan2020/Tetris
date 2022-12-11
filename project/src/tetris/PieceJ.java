package tetris;

public class PieceJ extends Tetromino{
    public PieceJ() {
        cells[0] = new Cell(0, 4, LoadImage.pieceJ);
        cells[1] = new Cell(0, 3, LoadImage.pieceJ);
        cells[2] = new Cell(0, 5, LoadImage.pieceJ);
        cells[3] = new Cell(1, 5, LoadImage.pieceJ);

        // There are 4 rotate states for Piece J.
        rotateStates = new RotateState[4];
        // Initialize rotateStates
        rotateStates[0] = new RotateState(0, 0, 0, -1, 0, 1, 1, 1);
        rotateStates[1] = new RotateState(0, 0, -1, 0, 1, 0, 1, -1);
        rotateStates[2] = new RotateState(0, 0, 0, 1, 0, -1, -1, -1);
        rotateStates[3] = new RotateState(0, 0, 1, 0, -1, 0, -1, 1);
    }
}
