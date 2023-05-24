package fisherman;
import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        setSize(1200,675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Fishman");
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel();
        frame.setResizable(false);
        panel.action();
        frame.add(panel);
        panel.requestFocus();
        frame.setVisible(true);

    }
}
