
public class Block {
    
    private int x;
    private int y;
    private boolean show;
    public static final int SIZE = 32;
    private boolean isMine;
    private int minesAround;
    
    public Block(int x, int y){
        this.x = x;
        this.y = y;
        this.isMine = false;
        this.show = false;
        this.minesAround = 0;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getX() {
        return x;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public int getY() {
        return y;
    }
    
    public int getSize() {
        return SIZE;
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

    public int getMinesAround() {
        return minesAround;
    }
    
    public void setIsMine(boolean isMine){
        this.isMine = isMine;
    }
}
