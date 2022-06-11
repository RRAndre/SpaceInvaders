package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Text;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Score {

    private int score = 0;
    private String scoreText;
    //private JLabel actualScore;
    private Text actualScore;

    public Score() throws IOException, FontFormatException {
        //actualScore = new JLabel();
        scoreInit();
    }

    public void scoreInit() throws IOException, FontFormatException {
        /*Font font = Font.createFont(Font.TRUETYPE_FONT, new File("resources/PressStart2P-Regular.ttf"));
        actualScore.setFont(font);
        actualScore.setSize(300,300);
        actualScore.setHorizontalTextPosition(SwingConstants.LEFT);

         */
        actualScore = new Text(Background.PADDING + (Background.CELLSIZE*20), Background.PADDING + (Background.CELLSIZE*3) , scoreText);
        setScore(score);
        actualScore.grow(50,50);
        actualScore.draw();
    }
    public void setScore(int score) {
        this.score += score;
        intToString();
        actualScore.setText(scoreText);
    }
    public void intToString(){
        scoreText = String.valueOf(score);
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
