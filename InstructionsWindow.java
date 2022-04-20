import javax.swing.*;
import java.awt.*;

public class InstructionsWindow {

    JPanel instructionPanel = new JPanel();
    JFrame frame = new JFrame();
    JLabel instructions = new JLabel(   "<html>Rules: 1. You can play with arrow keys. <br/>"  +
            "2. Eat as many apples as you can and beware of the borders (!) <br/>"
            + "3. Don't press the keys fast when you want to change path<br/> "
            + "from up to down :)<br/>"
            + "Have fun!</html>");

    InstructionsWindow(){


        instructions.setBounds(150,0,1000,250);
        instructions.setFont(new Font(null, Font.PLAIN,20));
        frame.add(instructions);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(800,300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
