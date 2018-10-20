
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener{
    
    private Board board;
    
    public Game(){
        this.setPreferredSize(new Dimension(288, 288));
        addMouseListener(this);
        board = new Board();
    }
    
    public static void main(String[] args){
        JFrame window = new JFrame("MyMineSweeper");
        window.add(new Game());
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        board.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        board.selectBlock(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
