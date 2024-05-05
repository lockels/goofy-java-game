package inf112.skeleton.app.controller.myInput;

import javax.sound.sampled.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Controls sound effects and background music for the game.
 * Implements the {@link ISoundController} interface.
 */
public class SoundController implements ISoundController {

    private HashMap<String, Clip> clips = new HashMap<>();
    private Music backgroundMusic;

    /**
     * Constructs a new SoundController and initializes the sounds.
     */
    public SoundController() {

        //initializeBackgroundMusic();
        // Map specific sounds to actio
        loadSound("background", "/sounds/background.wav");
        loadSound("dogma", "/sounds/Dogma.wav");
        loadSound("beastGhostrise", "/sounds/Beast_ghostrise1.wav");
        loadSound("technologyShot", "/sounds/Technology_shot.wav");
        loadSound("damage", "/sounds/damageSound.wav");
        loadSound("death", "/sounds/Death_sound.wav");
        loadSound("isaacDeath", "/sounds/Isaac_dies.wav");
        loadSound("pestilence", "/sounds/Pestilence_spit_blob.wav");
    }

    private void loadSound(String key, String path) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(path);
            if (audioSrc == null) {
                System.err.println("Resource not found: " + path);
                return;
            }
            BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clips.put(key, clip);
            // Close the stream after use
            audioStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + key + " - " + e);
        }
    }

    @Override
    public void initializeBackgroundMusic() {
        playSound("background");
    }

    @Override
    public void playSound(String soundKey) {
        Clip clip = clips.get(soundKey);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    public void dispose() {
        for (Clip clip : clips.values()) {
            if (clip != null) {
                clip.close();
            }
        }
        if (backgroundMusic != null) {
            backgroundMusic.dispose();
        }
    }

    @Override
    public void playMoveSound() {
        playSound("move");
    }

    
    @Override
    public void playCoinSound() {
        playSound("coin");
    }

    @Override
    public void playPestilenceSound() {
        playSound("pestilence");
    }

    @Override
    public void playDamageSound() {
        playSound("damage");
    }


    @Override
    public void playGameOverSound() {
        playSound("isaacDeath");
    }

    @Override
    public void playTechoShotSound() {
        playSound("technologyShot");
    }

    @Override
    public void playBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isPlaying()) {
            backgroundMusic.play();
        }
    }

    @Override
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
        }
    }


    @Override
    public void setMusicVolume(float volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    /**
     * Gets the map of sound clips.
     *
     * @return The map of sound clips.
     */
    public HashMap<String, Clip> getClips() {
        return this.clips;
    }

    /**
     * Gets the background music.
     *
     * @return The background music.
     */
    public Music getBackgroundMusic() {
        return this.backgroundMusic;
    }
}
