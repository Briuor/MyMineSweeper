
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {
    
    private Board board;
    private MouseAdapter mouseAdapter;
    public Game(){
        this.setPreferredSize(new Dimension(288, 288));
        this.setFocusable(true);
        
        mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
               if(e.getButton() == MouseEvent.BUTTON1){
                    board.selectBlock(e.getX(), e.getY());
               }
               else if(e.getButton() == MouseEvent.BUTTON2){
                   //flag
               }
                   
               repaint();
            }
        };
        addMouseListener(mouseAdapter);
        
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
}
