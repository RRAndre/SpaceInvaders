package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Score {

    private int score = 0;
    private String scoreText;
    private Text actualScore;

    public Score() throws IOException, FontFormatException {
        scoreInit();
    }

    public int readHighestScore() throws IOException{
        System.out.println("Reading highest score");
        FileInputStream inputStream = new FileInputStream("resources/HighestScore.txt");
        byte[] buffer = new byte[1024];
        int highScore = inputStream.read(buffer);
        System.out.println(highScore);
        return highScore;
    }
    public void saveHighestScore() throws IOException {

        if(Spaceship.isEndGame() && score > readHighestScore()){
            System.out.println("score is higher than previous");
            FileOutputStream outputStream = new FileOutputStream("resources/HighestScore.txt");
            outputStream.write(score);
            outputStream.close();
        }

    }

    public void scoreInit() throws IOException, FontFormatException {
        /*InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/PressStart2P-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
//Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/PressStart2P-Regular.ttf"));
        actualScoreJL.setFont(font);
        actualScoreJL.setSize(300,300);
        actualScoreJL.setHorizontalTextPosition(SwingConstants.LEFT);
         */
        actualScore = new Text(Background.PADDING + (Background.CELLSIZE * 18), Background.PADDING + (Background.CELLSIZE * 2) +3, scoreText);
        setScore(score);
        actualScore.setColor(Color.CYAN);
        actualScore.grow(33, 33);
        actualScore.draw();

    }

    public void setScore(int score) {
        this.score += score;
        intToString();
        actualScore.setText(scoreText);
    }

    public void intToString() {
        scoreText = String.valueOf(score);
    }

    public void removeText(){
        actualScore.delete();
    }

    //Getters
    public int getScore() {
        return score;
    }
    /*public Text getActualScore() {
        return actualScore;
    }
     */

}
