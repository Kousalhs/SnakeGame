package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame();
    RoundedJButton startButton = new RoundedJButton(new Dimension(20, 20));
    RoundedJButton instructionsButton = new RoundedJButton(new Dimension(20, 20));

    LaunchPage(){
        instructionsButton.setText("Instructions");
        instructionsButton.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.MEDIUM, 24));
        instructionsButton.setBounds(100,160,200,40);
        instructionsButton.setFocusable(false);
        instructionsButton.addActionListener(this);

        startButton.setText("Start Game");
        startButton.setFont(FontManager.getCustomizedFont(FontManager.FontStyle.MEDIUM, 24));
        startButton.setBounds(100,100,200,40);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        frame.add(startButton);
        frame.add(instructionsButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My snake game");
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsButton){
            SoundManager.playClip("button_clicked");
            InstructionsWindow startWindow = new InstructionsWindow();
        }

        if (e.getSource() == startButton) {
            SoundManager.playClip("button_clicked");
            new GameFrame();
            frame.dispose();
        }
    }
}