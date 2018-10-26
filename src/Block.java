
public class Block {
    
    //x, y coordinates of each block on the board
    private int x;
    private int y;
    private boolean show; // if the block is uncovered show=true, covered show=false 
    public static final int SIZE = 32; // size of each block
    private boolean isMine;  // if block is a mine isMine = true
    private int minesAround; // number of mines around the block (if the block is a mine, minesAround = -1, specified in the Board.generateMines() method)
    private boolean flagged; // if the block is marked as mine flagged=true
    
    public Block(int x, int y){
        this.x = x;
        this.y = y;
        this.isMine = false;
        this.show = false;
        this.flagged = false;
        this.minesAround = 0;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    //--------GETTERS AND SETTERS----------
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getIsMine() {
        return isMine;
    }
    
    public int getMinesAround() {
        return minesAround;
    }
    
    public boolean getShow() {
        return show;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public void setIsMine(boolean isMine){
        this.isMine = isMine;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
