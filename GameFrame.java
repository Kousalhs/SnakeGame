 import javax.swing.*;

 public class GameFrame extends JFrame{
     private JPanel panel;
    GameFrame(){
        panel = new GamePanel(this);
        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
} 