
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Board {

    private static final int ROWS = 9;
    private static final int COLS = 9;
    private Block[][] board;

    public Board() {
        board = new Block[ROWS][COLS];
        initBoard();
        generateMines();
    }

    private void initBoard() {
        int i, j, x, y;
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
                    g.setColor(Color.white);
                    g.fillRect(board[i][j].getX(), board[i][j].getY(), board[i][j].getSize(), board[i][j].getSize());
                    //draw text block
                    g.setColor(Color.green);
                    String minesAround = Integer.toString(board[i][j].getMinesAround());
                    g.drawString(minesAround, board[i][j].getX() + 13, board[i][j].getY() + 20); // 13 and 20 to be on the center
                } else {
                    if (board[i][j].getIsMine()) {
                        g.setColor(Color.black);
                        g.fillRect(board[i][j].getX(), board[i][j].getY(), board[i][j].getSize(), board[i][j].getSize());
                    } else {
                        g.setColor(Color.gray);
                    }
                    g.drawRect(board[i][j].getX(), board[i][j].getY(), board[i][j].getSize(), board[i][j].getSize());
                }
            }
        }
    }

    private void generateMines() {
        int numMines = 0;
        Random rand = new Random();
        while (numMines <= 10) {
            int mineRow = (int) (rand.nextDouble() * ROWS);
            int mineCol = (int) (rand.nextDouble() * COLS);

            if (!board[mineRow][mineCol].getIsMine()) {
                board[mineRow][mineCol].setIsMine(true);
            }
            numMines++;
        }
        fillBlockNumbers();
    }

    private void fillBlockNumbers() {
        int minesAround = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!board[i][j].getIsMine()) {
                    //blocks over the specific block                    
                    for (int p = i - 1; p <= i + 1; p++) {
                        for (int q = j - 1; q <= j + 1; q++) {
                            if (0 <= p && p < board.length && 0 <= q && q < board.length) {
                                if (board[p][q].getIsMine()) {
                                    ++minesAround;
                                }
                            }
                        }
                    }
                    board[i][j].setMinesAround(minesAround);
                    minesAround = 0;
                }
            }
        }
    }
    
    public void selectBlock(int x, int y) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int boardX = board[i][j].getX();
                int boardY = board[i][j].getY();
                if ((x > boardX && x < boardX + 32) && (y > boardY && y < boardY + 32)) {
                    //If it's a mine player lose
                    if (board[i][j].getIsMine()) {
                        System.out.println("perdeu");
                    } //If it's an empty block( a block with no mines around itself)
                    else if (board[i][j].getMinesAround() == 0) {
                        //open each neighbor
                        for(Block neighbor : getNeighbor(i, j)){
                            neighbor.setShow(true);
                            //https://github.com/KnightMiner/MineSweeper/blob/master/src/java/knightminer/minesweeper/MineSweeperBoard.java
                        }
                    }
                    board[i][j].setShow(true);
                    break;
                }
            }
        }
    }

    private Block[] getNeighbor(int i, int j) {
        Block neighbor[] = new Block[9];
        int numNeighbor = 0; 
        for (int p = i - 1; p <= i + 1; p++) {
            for (int q = j - 1; q <= j + 1; q++) {
                if (0 <= p && p < ROWS && 0 <= q && q < COLS && !board[i][j].getIsMine()) {
                    neighbor[numNeighbor] = board[p][q];
                    numNeighbor++;
                }
            }
        }
        return neighbor;
    }
}
