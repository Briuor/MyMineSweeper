
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

    private Board board;
    private MouseAdapter mouseAdapter;

    public Game() {
        this.setPreferredSize(new Dimension(288, 288));
        this.setFocusable(true);

        initMouseAdapter();
        addMouseListener(mouseAdapter);

        board = new Board();
    }

    public void initMouseAdapter() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // left button click
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // double left button click on a flagged field
                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        e.consume();
                        System.out.println("Double");
                        board.openFlaggedField(e.getX(), e.getY());
                    } 
                    // normal left button click
                    else {
                        board.selectBlock(e.getX(), e.getY());
                    }
                    //check the if player won
                    if(board.checkWon()) {
                        System.out.println("Won");
                    }
                }
                //right button set a flag on a block
                else if (e.getButton() == MouseEvent.BUTTON3) {
                    board.putFlag(e.getX(), e.getY());
                }
                //draw everything again
                repaint();
            }
        };
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("MyMineSweeper");
        window.add(new Game());
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
