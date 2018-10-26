
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Board {

    private static final int ROWS = 9; // number of rows of the board[ROWS][]
    private static final int COLS = 9; // number of cols of the board[][COLS]
    private static final int NUM_MINES = 10; // number of mines on the board
    private Block[][] board;  // matrix of blocks

    public Board() {
        board = new Block[ROWS][COLS];
        initBoard();
        generateMines();
        fillBlockMinesAround();
    }

    // fill the matrix board[][] with blocks, and set each block coordinate on the board
    private void initBoard() {
        int i, j; // i, j are board indexes
        int x, y; // x, y are block coordinates
        for (i = 0, x = 0; i < ROWS; i++, x += Block.SIZE) {
            for (j = 0, y = 0; j < COLS; j++, y += Block.SIZE) {
                board[i][j] = new Block(x, y);
            }
        }
    }

    public void draw(Graphics g) {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //if block content must be showed
                if (board[i][j].getShow()) {
                    //draw empty block
                    g.setColor(Color.blue);
                    g.fillRect(board[i][j].getX(), board[i][j].getY(), Block.SIZE, Block.SIZE);
                    g.setColor(Color.gray);
                    g.drawRect(board[i][j].getX(), board[i][j].getY(), Block.SIZE, Block.SIZE);
                    //draw text block
                    g.setColor(Color.green);
                    String minesAround = Integer.toString(board[i][j].getMinesAround());
                    g.drawString(minesAround, board[i][j].getX() + 13, board[i][j].getY() + 20); // 13 and 20 to be on the center
                } else if (board[i][j].isFlagged()) {
                    g.setColor(Color.green);
                    g.fillRect(board[i][j].getX(), board[i][j].getY(), Block.SIZE, Block.SIZE);
                } else {
//                    if (board[i][j].getIsMine()) {
//                        g.setColor(Color.black);
//                        g.fillRect(board[i][j].getX(), board[i][j].getY(), Block.SIZE, Block.SIZE);
//                    } else {
                    g.setColor(Color.gray);
//                    }
                    g.drawRect(board[i][j].getX(), board[i][j].getY(), Block.SIZE, Block.SIZE);
                }
            }
        }
    }

    //generate mines at random positions on the board
    private void generateMines() {
        int numMines = 0;
        Random rand = new Random();
        while (numMines <= NUM_MINES) {
            int mineRow = (int) (rand.nextDouble() * ROWS);
            int mineCol = (int) (rand.nextDouble() * COLS);

            if (!board[mineRow][mineCol].getIsMine()) {
                board[mineRow][mineCol].setIsMine(true);
                board[mineRow][mineCol].setMinesAround(-1);
            }
            numMines++;
        }
    }

    //fill the number of mines around of each block on the board
    private void fillBlockMinesAround() {
        int minesAround = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //if the block is not a mine count the number of mines around it
                if (!board[i][j].getIsMine()) {
                    //loop for the blocks over the specific block                    
                    for (int p = i - 1; p <= i + 1; p++) {
                        for (int q = j - 1; q <= j + 1; q++) {
                            if (0 <= p && p < ROWS && 0 <= q && q < COLS) {
                                //if the block over the specific block is a mine count minesAround+1
                                if (board[p][q].getIsMine()) {
                                    ++minesAround;
                                }
                            }
                        }
                    }
                    board[i][j].setMinesAround(minesAround); // set the number of minesAround 
                    minesAround = 0; //reset the number of minesAround
                }
            }
        }
    }

    // when the user click get the coordinates(x, y) of area clicked and check which block was selected
    // based on the block area on the board
    public void selectBlock(int x, int y) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int boardX = board[i][j].getX();
                int boardY = board[i][j].getY();
                if ((x > boardX && x < boardX + 32) && (y > boardY && y < boardY + 32)) {

                    //if empty block
                    if (board[i][j].getMinesAround() == 0) {
                        revealEmptyBlocks(i, j);
                    } //if mine
                    else if (board[i][j].getIsMine()) {
                        System.out.println("perdeu");
                    }
                    board[i][j].setShow(true);
                    return;
                }
            }
        }
    }

    public void revealEmptyBlocks(int i, int j) {
        for (int p = i - 1; p <= i + 1; p++) {
            for (int q = j - 1; q <= j + 1; q++) {
                if (0 <= p && p < ROWS && 0 <= q && q < COLS) {
                    //If the block is another empty block keep the recursion
                    if (board[p][q].getMinesAround() == 0 && !board[p][q].getShow()) {
                        board[p][q].setShow(true);
                        revealEmptyBlocks(p, q);
                    } //if its a not empty block stop recursion
                    else if (board[p][q].getMinesAround() > 0) {
                        board[p][q].setShow(true);
                    }
                }
            }
        }
    }

    public Block[] getNeighbor(int i, int j) {
        Block[] neighbors = null;
        int k = 0;

        for (int p = i - 1; p <= i + 1; p++) {
            for (int q = j - 1; q <= j + 1; q++) {
                neighbors[k++] = board[p][q];
            }
        }
        return neighbors;
    }

    public boolean checkWon() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                //block is covered and is not a mine, so keep playing
                if (!board[i][j].getShow() && !board[i][j].getIsMine()) {
                    return false;
                }
            }
        }
        //there aren't covered blocks
        return true;
    }

    public void putFlag(int x, int y) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int boardX = board[i][j].getX();
                int boardY = board[i][j].getY();
                if ((x > boardX && x < boardX + 32) && (y > boardY && y < boardY + 32)) {
                    board[i][j].setFlagged(true);
                    return;
                }
            }
        }
    }

    public void openFlaggedField(int x, int y) {
        boolean flagged = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int boardX = board[i][j].getX();
                int boardY = board[i][j].getY();
                if ((x > boardX && x < boardX + 32) && (y > boardY && y < boardY + 32)) {
                    //check if there is a flagged block around it
                    for (int p = i - 1; p <= i + 1; p++) {
                        for (int q = j - 1; q <= j + 1; q++) {
                            if (0 <= p && p < ROWS && 0 <= q && q < COLS) {
                                System.out.println("2");
                                if (board[p][q].isFlagged()) {
                                    flagged = true;
                                }
                            }
                        }
                    }
                    if(flagged)revealEmptyBlocksFlag(i, j);
                    return;
                }
            }
        }
    }

    public void revealEmptyBlocksFlag(int i, int j) {
        for (int p = i - 1; p <= i + 1; p++) {
            for (int q = j - 1; q <= j + 1; q++) {
                if (0 <= p && p < ROWS && 0 <= q && q < COLS) {
                    // block uncovered and not a mine
                    if (!board[p][q].getShow() && !board[p][q].getIsMine()) {
                        board[p][q].setShow(true);
                        // check if any block around is an empty block and open it
                        if(board[p][q].getMinesAround() == 0) {
                            revealEmptyBlocks(p, q);
                        }
                    }
                    // mine not flagged YOU LOSE
                    else if(board[p][q].getIsMine() && !board[p][q].isFlagged()){
                        System.out.println("PERDEU");
                    }
                }
            }
        }
    }
}
