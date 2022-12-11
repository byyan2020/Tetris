package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Tetris extends JPanel {
    // Declare curren falling tetromino.
    private Tetromino currentPiece = Tetromino.randomOne();
    // Declare next falling tetromino.
    private Tetromino nextPiece = Tetromino.randomOne();
    // Declare game area.
    private Cell[][] gameArea = new Cell[18][9];
    // Declare Cell pixel.
    private static final int CELL_SIZE = 48;

    // Declare game scores
    int[] scoresPool = {0, 1, 2, 5, 10};
    private int totalScore = 0;
    private int totalLine = 0;

    // Declare game status.
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    public int gameStatus = 2;
    public String[] showStatus = {"P[pause]", "C[continue]", "S[start]"};

    // Game play with key control
    public void start(){
        gameStatus = PLAYING;
        KeyListener l = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code){
                    case KeyEvent.VK_DOWN:
                        softDropAction(); //soft drop
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeftAction(); //move left
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightAction(); //move right
                        break;
                    case KeyEvent.VK_UP:
                        rotateRightAction(); //rotate right
                        break;
                    case KeyEvent.VK_SPACE:
                        hardDropAction(); //hard drop
                        break;
                    case KeyEvent.VK_P:
                        // Pause game.
                        if(gameStatus == PLAYING){
                            gameStatus = PAUSE;
                        }
                        break;
                    case KeyEvent.VK_C:
                        // Continue game
                        if(gameStatus == PAUSE){
                            gameStatus = PLAYING;
                        }
                        break;
                    case KeyEvent.VK_S:
                        //restart game
                        gameStatus = PLAYING;
                        gameArea = new Cell[18][9];
                        currentPiece = Tetromino.randomOne();
                        nextPiece = Tetromino.randomOne();
                        totalScore = 0;
                        totalLine = 0;
                        break;
                }
            }
        };

        // Set focus to the game
        this.addKeyListener(l);
        this.requestFocus();

        while(true){
            //If game is paying, tetromino will fall every 0.5s
            if(gameStatus == PLAYING){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Judge if the tetromino can drop
                if(canDrop()){
                    currentPiece.softDrop();
                }else{
                    // Add tetromino to game area
                    landToArea();
                    // Judge if line can be destroyed.
                    destroyLine();
                    // Judge if game is over.
                    if(isGameOver()){
                        gameStatus = GAMEOVER;
                    }else{
                        currentPiece = nextPiece;
                        nextPiece = Tetromino.randomOne();
                    }
                }
            }
            repaint();
        }
    }

    // Paint game area.
    @Override
    public void paint(Graphics g) {
        // Add background image.
        g.drawImage(LoadImage.backImage, 0, 0, null);
        // Align game area to background image.
        g.translate(22, 15);
        // Paint cells.
        paintArea(g);
        // Paint current tetromino.
        paintCurrentPiece(g);
        // Paint next tetromino.
        paintNextPiece(g);
        // Paint total score.
        paintScore(g);
        // Paint game status.
        paintStatus(g);
        paintRules(g);
    }

    // Judge if tetromino can drop
    public boolean canDrop() {
        Cell[] cells = currentPiece.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            // if tetromino touch the bottom of the board
            if (row == gameArea.length - 1) {
                return false;
            } else if (gameArea[row + 1][col] != null) {
                return false;
            }
        }
        return true;
    }

    // Judge if current row is full
    public boolean isFullLine(int row) {
        Cell[] cells = gameArea[row];
        for (Cell cell : cells) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }

    // Destroy line if line is full and calculate the score accordingly.
    public void destroyLine() {
        int line = 0;
        Cell[] cells = currentPiece.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            if (isFullLine(row)) {
                line++;
                for (int i = row; i > 0; i--) {
                    System.arraycopy(gameArea[i - 1], 0, gameArea[i], 0, gameArea[0].length);
                }
                gameArea[0] = new Cell[9];
            }
        }
        // Update total score
        totalScore += scoresPool[line];
        // Update total line;
        totalLine += line;
    }

    // Judge if game is over.
    public boolean isGameOver() {
        Cell[] cells = nextPiece.cells;
        for (Cell cell: cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (gameArea[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    // Paint current game status
    private void paintStatus(Graphics g) {
        if (gameStatus == PLAYING) {
            g.drawString(showStatus[PLAYING], 520, 540);
        } else if (gameStatus == PAUSE) {
            g.drawString(showStatus[PAUSE], 520, 540 );
        } else if (gameStatus == GAMEOVER) {
            g.drawString(showStatus[GAMEOVER], 520, 540 );
            g.setColor(Color.red);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            g.drawString("GAME OVER", 30, 400);
        }
    }

    private void paintRules(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.TYPE1_FONT, 15));
        g.drawString("Left Arrow - move left", 520, 660 );
        g.drawString("Right Arrow - move right", 520, 690 );
        g.drawString("Up Arrow - rotate right", 520, 720 );
        g.drawString("Down Arrow - soft drop", 520, 750 );
        g.drawString("Space - hard drop", 520, 780 );
        g.drawString("P - pause game", 520, 810 );
        g.drawString("C - continue game", 520, 840 );
    }

    private void paintScore(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.drawString("Scores: " + totalScore, 520, 230);
        g.drawString("Lines: " + totalLine, 520, 385);

    }

    // Paint next falling tetromino
    private void paintNextPiece(Graphics g) {
        Cell[] cells = nextPiece.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 380;
            int y = cell.getRow() * CELL_SIZE + 20;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    // Paint current falling tetromino.
    private void paintCurrentPiece(Graphics g) {
        Cell[] cells = currentPiece.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    // Paint cells.
    private void paintArea(Graphics g) {
        for (int i = 0; i < gameArea.length; i++) {
            for (int j = 0; j < gameArea[i].length; j++){
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                Cell cell = gameArea[i][j];
                if (cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    // Judge if the current piece is out the bound.
    public boolean outOfBounds() {
        Cell[] cells = currentPiece.cells;
        for (Cell cell : cells) {
            int col = cell.getCol();
            int row = cell.getRow();
            if (row < 0 || row > gameArea.length - 1 || col < 0 || col > gameArea[0].length - 1) {
                return true;
            }
        }
        return false;
    }

    // Judge if the current piece coincides
    public boolean coincide() {
        Cell[] cells = currentPiece.cells;
        for (Cell cell: cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (gameArea[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    // Move tetromino left with key pressed
    public void moveLeftAction() {
        currentPiece.moveLeft();
        if (outOfBounds() || coincide()) {
            currentPiece.moveRight();
        }
    }

    // Move tetromino right with key pressed
    public void moveRightAction() {
        currentPiece.moveRight();
        if (outOfBounds() || coincide()) {
            currentPiece.moveLeft();
        }
    }

    // Rotate tetromino right.
    public void rotateRightAction(){
        currentPiece.rotateRight();
        if(outOfBounds() || coincide()){
            currentPiece.rotateLeft();
        }
    }

    // Press down key to soft drop tetromino.
    public void softDropAction() {
        if (canDrop()) {
            currentPiece.softDrop();
        } else {
            // Add current piece to gameArea.
            landToArea();
            // Destroy line if necessary.
            destroyLine();
            // If game is over.
            if (isGameOver()) {
                gameStatus = GAMEOVER;
            } else {
                // Game not over, generate new Tetromino.
                currentPiece = nextPiece;
                nextPiece = Tetromino.randomOne();
            }

        }
    }

    // Hard drop.
    public void hardDropAction() {
        // Judge if tetromino can drop
        while (true) {
            if (canDrop()) {
                currentPiece.softDrop();
            } else {
                break;
            }
        }
        // Add current piece to gameArea.
        landToArea();
        // Destroy line if necessary.
        destroyLine();
        // If game is over.
        if (isGameOver()) {
            gameStatus = GAMEOVER;
        } else {
            // Game not over, generate new Tetromino.
            currentPiece = nextPiece;
            nextPiece = Tetromino.randomOne();
        }
    }

    // Add current piece to gameArea.
    private void landToArea() {
        Cell[] cells = currentPiece.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            gameArea[row][col] = cell;
        }
    }


}
