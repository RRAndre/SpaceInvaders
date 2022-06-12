package org.academiadecodigo.bootcamp;

import javax.sound.sampled.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartMenu {
    private ExecutorService sound = Executors.newSingleThreadExecutor();
    private SoundIntro soundIntro;
    private Clip clip;

    public StartMenu(){
        soundIntro = new SoundIntro();
        sound.submit(soundIntro);

    }

    public void removeSound(){

        //soundIntro.update( new LineEvent.Type("Stop"));
        //sound.shutdown();
        sound.shutdownNow();

    }
}
