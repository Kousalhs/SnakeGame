package src;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame{
     private JPanel panel;
    GameFrame(){
        panel = new GamePanel(this);
        SoundManager.playClip("snake_game_theme");
        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}