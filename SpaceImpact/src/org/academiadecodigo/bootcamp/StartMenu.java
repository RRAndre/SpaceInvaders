package org.academiadecodigo.bootcamp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartMenu {
    private ExecutorService sound = Executors.newSingleThreadExecutor();
    private SoundIntro soundIntro;

    public StartMenu(){
        soundIntro = new SoundIntro();
        sound.submit(soundIntro);
    }

    public void removeSound(){
        sound.shutdown();
    }
}
