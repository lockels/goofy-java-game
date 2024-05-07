package inf112.skeleton.app.controller.myInput;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.sound.sampled.Clip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.audio.Music;

public class SoundControllerTest {
    @Mock private Music backgroundMusic;
    @Mock private Clip clip;
    @InjectMocks private SoundController soundController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Assuming getClips() and getBackgroundMusic() are provided and accessible for testing
        soundController.getClips().put("move", clip);
        soundController.getClips().put("coin", clip);
        soundController.getClips().put("damage", clip);
        soundController.getClips().put("technologyShot", clip);
        soundController.getClips().put("isaacDeath", clip);
        soundController.getBackgroundMusic(); // Assume this method is implemented
    }
    
    @Test
    void testDispose() {
        soundController.dispose();
        verify(clip, times(5)).close(); // assuming there are 5 clips loaded
        verify(backgroundMusic).dispose();
    }

    @Test
void testGetBackgroundMusic() {
    assertSame(backgroundMusic, soundController.getBackgroundMusic());
}

    @Test
void testGetClips() {
    assertNotNull(soundController.getClips());
    assertTrue(soundController.getClips().containsKey("move"));
}

    // @Test
    // void testInitializeBackgroundMusic() {
    //     // Assuming initializeBackgroundMusic is called during setUp
    //     verify(backgroundMusic).setLooping(true);
    //     verify(backgroundMusic).setVolume(0.5f);
    //     verify(backgroundMusic).play();
    // }


    @Test
    void testPlayBackgroundMusic() {
        when(backgroundMusic.isPlaying()).thenReturn(false);
        soundController.playBackgroundMusic(0.2f);
        verify(backgroundMusic).play();
    }


    @Test
    void testPlayCoinSound() {
        soundController.playCollectCoinSound();
        verify(clip).start();
    }

    @Test
    void testPlayDamageSound() {
        soundController.playDamageSound();
        verify(clip).start();
    }

    @Test
    void testPlayGameOverSound() {
        soundController.playGameOverSound();
        verify(clip).start();
    }

    @Test
    void testPlayMoveSound() {
        soundController.playMoveSound();
        verify(clip).start();
    }

    // @Test
    // void testPlayPestilenceSound() {
    //     soundController.playPestilenceSound();
    //     verify(clip).start();
    // }

    // @Test
    // void testPlaySound_NotPlaying() {
    //     // Setup the clip to not be playing
    //     when(clip.isRunning()).thenReturn(false);

    //     // Call playSound with a valid key
    //     soundController.playSound("move");

    //     // Verify the clip is stopped (to reset it) and started
    //     verify(clip).stop();  // To ensure it is stopped before starting again
    //     verify(clip).setFramePosition(0);  // Resetting to start
    //     verify(clip).start();  // Should start the clip
    // }


    @Test
    void testPlayTechoShotSound() {
        soundController.playTechoShotSound();
        verify(clip).start();
    }

    @Test
    void testSetMusicVolume() {
        float volume = 0.75f;
        soundController.setMusicVolume(volume);
        verify(backgroundMusic).setVolume(volume);
    }


    @Test
    void testStopBackgroundMusic() {

    }
}
