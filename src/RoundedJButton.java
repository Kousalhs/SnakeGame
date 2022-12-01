package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A simple class that represents a round-shaped button in our game. Objects of this class are utilized in
 * the answer buttons displayed to the player(s). The class is developed as a sub-class of the <code>JButton</code>
 * class.
 *
 * @author Vasileios Papapstergios
 */
public class RoundedJButton extends JButton {
    private Dimension arcs;

    /**
     * Constructs a rounded button with given arc dimensions.
     *
     * @param arcs the dimensions for the button arcs.
     */
    public RoundedJButton(Dimension arcs) {
        this.arcs = arcs;
        setOpaque(false);
        this.addMouseListener(new CustomMouseAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        RoundShapeArtist.drawRoundedShapeAndStroke(this, arcs, g);

        FontMetrics fm = graphics.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        graphics.drawString(getText(), x, y);

        setBorderPainted(false);
    }

    protected class CustomMouseAdapter extends MouseAdapter {
        private static final int RELOCATION_X = 0;
        private static final int RELOCATION_Y = 6;
        private static final int RELOCATION_WIDTH = 6;
        private static final int RELOCATION_HEIGHT = 6;

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            inverseColors(buttonSource);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            inverseColors(buttonSource);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            buttonSource.setBounds(getX() - RELOCATION_X, getY() - RELOCATION_Y, getWidth() + RELOCATION_WIDTH, getHeight() + RELOCATION_HEIGHT);
            buttonSource.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton buttonSource = (JButton) e.getSource();
            buttonSource.setBounds(getX() + RELOCATION_X, getY() + RELOCATION_Y, getWidth() - RELOCATION_WIDTH, getHeight() - RELOCATION_HEIGHT);
            buttonSource.repaint();
        }

        private void inverseColors(JButton buttonSource) {
            Color background = buttonSource.getBackground();
            buttonSource.setBackground(buttonSource.getForeground());
            buttonSource.setForeground(background);
        }
    }
}
