package org.academiadecodigo.bootcamp;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundClass implements LineListener, Runnable {

    boolean playCompleted;
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
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
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
        } catch (IOException ex) {
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
        String audioFilePath = "resources/shootsound.wav";
        SoundClass player = new SoundClass();
        player.play(audioFilePath);
    }

    @Override
    public void run() {
        play();
    }
}
