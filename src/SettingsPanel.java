package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * The game's settings panel
 */
public class SettingsPanel extends JPanel implements ActionListener {

    protected JPanel sliderPanel;
    protected JPanel buttonsPanel;
    protected JSlider musicSlider;
    protected JSlider sfxSlider;
    protected JButton sfxMuteBtn;
    protected  JButton musicMuteBtn;

    private Hashtable<Integer, JLabel> JSliderLabels;

    /**
     * Default constructor which initiates the frame and sets it up
     */
    public SettingsPanel() {

        super();
        setUpPanel();
        setUpSliderLabels();
        setUpSliders();
        setUpButtons();
        setUpButtonsPanel();

    }

    /**
     * Sets up the sliders
     */
    private void setUpSliders() {
        float[] sliderValuesTHEME = SoundManager.getFloatControlValuesTHEME();
        float[] sliderValuesSFX = SoundManager.getFloatControlValuesSFX();

//        musicSlider = new JSlider(JSlider.HORIZONTAL, (int) sliderValuesTHEME[0], (int) sliderValuesTHEME[1], (int) sliderValuesTHEME[2]);
        musicSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, (int) (sliderValuesTHEME[2] * 100));
        musicSlider.setLabelTable(JSliderLabels);
        musicSlider.setPaintLabels(true);
        musicSlider.addChangeListener(this::stateChanged);
        musicSlider.setBorder(BorderFactory.createTitledBorder("Music"));
        sliderPanel.add(musicSlider);

        sfxSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, (int) (sliderValuesSFX[2] * 100));
        sfxSlider.setLabelTable(JSliderLabels);
        sfxSlider.setPaintLabels(true);
        sfxSlider.addChangeListener(this::stateChanged);
        sfxSlider.setBorder(BorderFactory.createTitledBorder("SFX"));
        sliderPanel.add(sfxSlider);

    }

    /**
     * Sets up the slider labels
     */
    private void setUpSliderLabels() {
        Hashtable<Integer, JLabel> jSliderLabels = new Hashtable<>();
        jSliderLabels.put(0, new JLabel("0"));
        jSliderLabels.put(50, new JLabel("25"));
        jSliderLabels.put(100, new JLabel("50"));
        jSliderLabels.put(150, new JLabel("75"));
        jSliderLabels.put(200, new JLabel("100"));

        JSliderLabels = jSliderLabels;
    }

    /**
     * Sets up the Buttons
     */
    private void setUpButtons() {
        musicMuteBtn = new JButton(!SoundManager.isMutedMusic()? "Mute Music" : "Unmute Music");
        musicMuteBtn.addActionListener(this);
        sfxMuteBtn = new JButton(!SoundManager.isMutedSFX()? "Mute SFX" : "Unmute SFX");
        sfxMuteBtn.addActionListener(this);
    }

    /**
     * Sets up the panel
     */
    private void setUpPanel() {
        this.setLayout(new BorderLayout());
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setVisible(true);
        this.add(sliderPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * sets up button class.
     * @param e event
     */
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        float soundLevel = (float) source.getValue() / 100;
        SoundType soundType = e.getSource() == musicSlider? SoundType.THEME : SoundType.CLIP;
        SoundManager.adjustSound(soundLevel, soundType);


    }

    private void setUpButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(musicMuteBtn);
        buttonsPanel.add(sfxMuteBtn);
        buttonsPanel.setVisible(true);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == musicMuteBtn) {
            SoundManager.toggleMusicMute();
            if (musicMuteBtn.getText().equals("Mute Music"))
                musicMuteBtn.setText("Unmute Music");
            else
                musicMuteBtn.setText("Mute Music");
        } else if (e.getSource() == sfxMuteBtn) {
            SoundManager.toggleSFXMute();
            if (sfxMuteBtn.getText().equals("Mute SFX"))
                sfxMuteBtn.setText("Unmute SFX");
            else
                sfxMuteBtn.setText("Mute SFX");
        }
    }
}
