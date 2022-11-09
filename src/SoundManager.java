package src;

import javax.sound.sampled.FloatControl;
import java.util.HashMap;

/**
 * The class which handles the top level music and sound effect playback, sound volume, muting etc.
 * All of the class' fields are static, since the amount of clips during a game session won't be changing, and
 * as such, this class can be used without instantiation.
 */
public class SoundManager {

    private static SoundManager manager = new SoundManager();

    private static boolean mutedMusic = false;
    private static boolean mutedSFX = false;
    private static HashMap<String, SoundClip> clips;
    private static float[] floatControlValuesSFX, floatControlValuesTHEME;


    /**
     * Constructor which creates the SoundManager object and creates both the HashMap containing all the clips in the
     * SoundClips folder, as well as the FloatControl valued array.
     */
    private SoundManager() {
        createSoundMap();
        createFloatValues();
    }

    public static SoundManager getManager() {
        return manager;
    }


    /**
     * Creates a HashMap containing all the available audio clips from the SoundClips folder.
     */
    private static void createSoundMap() {

        clips = new HashMap<>();

        for (String filename : Constants.SOUND_PATHS) {

            String[] tokenResult = filename.split("/");
            String clipName = tokenResult[tokenResult.length - 1].split("\\.")[0];
            SoundClip soundClip;
            if (clipName.contains("theme")) {
                soundClip = new SoundClip(filename, clipName, true, SoundType.THEME);
            } else {
                soundClip = new SoundClip(filename, clipName, false, SoundType.CLIP);
            }
            clips.put(clipName, soundClip);
        }
    }

    /**
     * Creates two {@code float[]} arrays, one containing the minimum, maximum and current values for the MASTER_GAIN
     * of the current line for Music clips, and another one for SFX clips.
     */
    private void createFloatValues() {
        FloatControl floatControlSFX = getFloatControlSFX();
        FloatControl floatControlTHEME = getFloatControlTHEME();

        floatControlValuesSFX = new float[]{
                floatControlSFX.getMinimum(),
                floatControlSFX.getMaximum(),
                1
        };
        floatControlValuesTHEME = new float[]{
                floatControlTHEME.getMinimum(),
                floatControlTHEME.getMaximum(),
                1
        };
    }

    /**
     * Adjusts the sound for all sound clips that match the {@code SoundType} soundType value.
     *
     * @param soundLevel the new value of the sound level
     * @param soundType  the type of clips of which to adjust the sound of
     */
    public static void adjustSound(float soundLevel, SoundType soundType) {
        for (String clipName : clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == soundType) {
                clip.setSoundLevel(20f * (float) Math.log10(soundLevel));
            }
            if (soundType == SoundType.CLIP)
                floatControlValuesSFX[2] = soundLevel;
            else
                floatControlValuesTHEME[2] = soundLevel;
        }
    }

    /**
     * Toggles music mute.
     */
    public static void toggleMusicMute() {
        mutedMusic = !mutedMusic;
        for (String clipName : clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == SoundType.THEME)
                clip.toggleMute();
        }
    }

    /**
     * Toggles SFX mute.
     */
    public static void toggleSFXMute() {
        mutedSFX = !mutedSFX;
        for (String clipName : clips.keySet()) {
            SoundClip clip = clips.get(clipName);
            if (clip.getSoundType() == SoundType.CLIP)
                clip.toggleMute();
        }
    }

    /**
     * Returns the FloatControl values array for SFX.
     *
     * @return a {@code float[]} array containing FloatControl values for min, max and current
     */
    public static float[] getFloatControlValuesSFX() {
        return floatControlValuesSFX;
    }

    /**
     * Returns the FloatControl values array for THEME
     *
     * @return a {@code float[]} array containing FloatControl values for min, max and current
     */
    public static float[] getFloatControlValuesTHEME() {
        return floatControlValuesTHEME;
    }

    /**
     * Searches for the given name of the clip, and if such a clip exists, begins playback.
     *
     * @param name the name of the clip we wish to play
     */
    public static void playClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).play();
        else
            System.out.println("Clip doesn't exist...");
    }

    /**
     * Searches for the given name of the clip, and if such a clip exists, stops playback.
     *
     * @param name the name of the clip we wish to cease playback of
     */
    public static void stopClip(String name) {
        if (clips.containsKey(name))
            clips.get(name).stop();
        else
            System.out.println("Clip doesn't exist...");
    }

    /**
     * Returns the FloatControl object for SFX sound clips.
     *
     * @return the FloatControl object for SFX clips
     */
    private static FloatControl getFloatControlSFX() {
        return clips.get("snake_game_theme").getFloatControl();
    }

    /**
     * Returns the FloatControl object for THEME sound clips.
     *
     * @return the FloatControl object for THEME clips
     */
    private static FloatControl getFloatControlTHEME() {
        return clips.get("game_start").getFloatControl();
    }

    /**
     * Let's us know whether or not the THEME clips are muted or not.
     *
     * @return {@code true} if THEME clips are muted, else {@code false}
     */
    public static boolean isMutedMusic() {
        return mutedMusic;
    }

    /**
     * Let's us know whether or not the SFX clips are muted or not.
     *
     * @return {@code true} if SFX clips are muted, else {@code false}
     */
    public static boolean isMutedSFX() {
        return mutedSFX;
    }
}
