package tetris;

/**
 * Class for tetromino
 * */
public class Tetromino {
    protected Cell[] cells = new Cell[4];
    // rotate state
    protected RotateState[] rotateStates;
    // rotate times
    protected int rotateCount = 10000;

    // Inner class for rotate state
    class RotateState {
        int row0, col0, row1, col1, row2, col2, row3, col3;

        public RotateState() {
        }

        public RotateState(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }

        public int getRow0() {
            return row0;
        }

        public void setRow0(int row0) {
            this.row0 = row0;
        }

        public int getCol0() {
            return col0;
        }

        public void setCol0(int col0) {
            this.col0 = col0;
        }

        public int getRow1() {
            return row1;
        }

        public void setRow1(int row1) {
            this.row1 = row1;
        }

        public int getCol1() {
            return col1;
        }

        public void setCol1(int col1) {
            this.col1 = col1;
        }

        public int getRow2() {
            return row2;
        }

        public void setRow2(int row2) {
            this.row2 = row2;
        }

        public int getCol2() {
            return col2;
        }

        public void setCol2(int col2) {
            this.col2 = col2;
        }

        public int getRow3() {
            return row3;
        }

        public void setRow3(int row3) {
            this.row3 = row3;
        }

        public int getCol3() {
            return col3;
        }

        public void setCol3(int col3) {
            this.col3 = col3;
        }

        @Override
        public String toString() {
            return "state{" +
                    "row0=" + row0 +
                    ", col0=" + col0 +
                    ", row1=" + row1 +
                    ", col1=" + col1 +
                    ", row2=" + row2 +
                    ", col2=" + col2 +
                    ", row3=" + row3 +
                    ", col3=" + col3 +
                    '}';
        }
    }


    // Rotate tetromino right
    public void rotateRight(){
        if(rotateStates.length == 0){
            return;
        }
        rotateCount++;
        RotateState s = rotateStates[rotateCount % rotateStates.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    /// Rotate tetromino left
    public void rotateLeft(){
        //旋转次数减1
        rotateCount--;
        RotateState s = rotateStates[rotateCount % rotateStates.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    // Move game piece left.
    public void moveLeft() {
        for (Cell cell: cells) {
            cell.left();
        }
    }

    //Move game piece right.
    public void moveRight() {
        for (Cell cell: cells) {
            cell.right();
        }
    }

    // Move game piece down.
    public void softDrop() {
        for (Cell cell: cells) {
            cell.drop();
        }
    }

    // Generate random game piece
    public static Tetromino randomOne() {
         int num = (int)(Math.random() * 7);
         Tetromino tetromino = null;
         switch (num) {
             case 0:
                 tetromino = new PieceI();
                 break;
             case 1:
                 tetromino = new PieceJ();
                 break;
             case 2:
                 tetromino = new PieceL();
                 break;
             case 3:
                 tetromino = new PieceO();
                 break;
             case 4:
                 tetromino = new PieceS();
                 break;
             case 5:
                 tetromino = new PieceT();
                 break;
             case 6:
                 tetromino = new PieceZ();
                 break;
         }
         return tetromino;
    }



}
