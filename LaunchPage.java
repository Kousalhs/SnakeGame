import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton startButton = new JButton("Start game");
    JButton instructionsButton = new JButton("Instructions");

    LaunchPage(){
        instructionsButton.setBounds(100,160,200,40);
        instructionsButton.setFocusable(false);
        instructionsButton.addActionListener(this);

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
            InstructionsWindow startWindow = new InstructionsWindow();
            String soundName = "wav2.wav";
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            try {
                assert clip != null;
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
            clip.start();
        }

        if (e.getSource() == startButton) {
            new GameFrame();
            String soundName = "wav2.wav";
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException ex) {
                ex.printStackTrace();
            }
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            try {
                assert clip != null;
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
            clip.start();
            frame.dispose();
        }
    }
}
