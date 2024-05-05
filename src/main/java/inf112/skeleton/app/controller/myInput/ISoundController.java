package inf112.skeleton.app.controller.myInput;

public interface ISoundController {
    // Method to play sound effects for different actions

    /**
     * Plays a sound for movement.
     */
    void playMoveSound();

    /**
     * Plays a sound for collecting a coin.
     */
    void playCoinSound();

    /**
     * Plays a sound for taking damage.
     */
    void playDamageSound();

    /**
     * Plays the background music if it's not already playing.
     */
    void playBackgroundMusic();

    /**
     * Stops the background music if it's playing.
     */
    void stopBackgroundMusic();

    /**
     * Plays a sound for the Pestilence attack.
     */
    void playPestilenceSound();

    /**
     * Plays a sound for a technology shot.
     */
    void playTechoShotSound();

    /**
     * Plays a sound mapped to a specified key.
     *
     * @param soundKey The key of the sound to play.
     */
    void playSound(String soundKey);

    /**
     * Plays a sound for game over.
     */
    void playGameOverSound();

    /**
     * Initializes the background music.
     */
    void initializeBackgroundMusic();  

    /**
     * Releases resources associated with the sounds and music.
     */
    void dispose();

    /**
     * Sets the volume for the background music.
     *
     * @param volume The volume to set for the background music.
     */
    void setMusicVolume(float volume) ;
}
