package org.academiadecodigo.bootcamp;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundIntro implements LineListener, Runnable {

    boolean playCompleted;

    void play(String audioFilePath) {
       // File audioIntroFile = new File(audioFilePath);

        try (InputStream in = getClass().getResourceAsStream(audioFilePath)) {
            InputStream bufferedIn = new BufferedInputStream(in);
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn)){
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
            //AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioIntroFile);
            //AudioFormat format = audioStream.getFormat();

         /*   DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            //audioClip.setMicrosecondPosition(2);
            audioClip.start();

            while (!playCompleted) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            audioClip.close();

          */
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
        } catch (IOException | UnsupportedAudioFileException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;

        }
    }

    public static void play() {
        String audioFilePath = "resources/retro_space.wav";
        SoundClass player = new SoundClass();
        player.play(audioFilePath);
    }

    @Override
    public void run() {
        play();
    }

}
