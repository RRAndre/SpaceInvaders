package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Score {

    private int score = 0;
    private static String scoreText;
    private Text actualScore;

    private static String readingHighScore;
    private static Integer scores;

    public Score() {
        scoreInit();
    }

    public void scoreInit() {
        actualScore = new Text(Background.PADDING + (Background.CELLSIZE * 18), Background.PADDING + (Background.CELLSIZE * 2) + 3, scoreText);
        setScore(score);
        actualScore.setColor(Color.CYAN);
        actualScore.grow(33, 33);
        actualScore.draw();
        scores = score;
    }

    public static String readHighestScore() throws IOException {
        System.out.println("Reading highest score");
        FileReader fileReader = new FileReader("resources/HighestScore.txt");
        BufferedReader scoreReader = new BufferedReader(fileReader);
        readingHighScore = scoreReader.readLine();
        scoreReader.close();
        System.out.println(readingHighScore);
        return readingHighScore;
    }

    public static void saveHighestScore() throws IOException {
        if (Spaceship.isEndGame() && scores > Integer.parseInt(readingHighScore)) {
            System.out.println("score is higher than previous");
            FileOutputStream outputStream = new FileOutputStream("resources/HighestScore.txt");
            byte[] highestScore = scoreText.getBytes(StandardCharsets.UTF_8);
            outputStream.write(highestScore);
            outputStream.close();
        }
    }


    public void setScore(int score) {
        this.score += score;
        intToString();
        actualScore.setText(scoreText);
    }

    public void intToString() {
        scoreText = String.valueOf(score);
    }

    public void removeText() {
        actualScore.delete();
    }
}
