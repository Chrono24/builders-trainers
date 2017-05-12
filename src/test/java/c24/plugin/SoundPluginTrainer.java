package c24.plugin;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SoundPluginTrainer {

    private SoundPlugin soundPlugin;

    public SoundPluginTrainer() {
        soundPlugin = mock(SoundPlugin.class);
    }

    public SoundPlugin getMock() {
        return soundPlugin;
    }

    public void thenNoSoundIsPlayed() {
        verify(soundPlugin, never()).playSoundEffect(any());
    }

    public void thenSoundIsPlayed(String soundEffect) {
        verify(soundPlugin).playSoundEffect(soundEffect);
    }
}