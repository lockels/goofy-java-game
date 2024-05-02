package inf112.skeleton.app.controller.myInput;

public interface ISoundController {
    // Method to play sound effects for different actions
    void playMoveSound();
    void playCoinSound();
    void playDamageSound();
    void playBackgroundMusic();
    void stopBackgroundMusic();
    void playPestilenceSound();
    void playTechoShotSound();
    void playSound(String soundKey);
    void playGameOverSound();
    void initializeBackgroundMusic();  
    void dispose();
    void setMusicVolume(float volume) ;
    // Method to set the volume for sounds
    //void setVolume(float volume); // General volume setter
    //void setSpecificSoundVolume(String soundName, float volume); // Specific sound volume setter
}
