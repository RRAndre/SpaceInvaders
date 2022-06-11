package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Text;

public class Score {

    private int score = 0;
    private String scoreText;
    private Text actualScore;

    public Score(){
        scoreInit();
    }

    public void scoreInit(){
        actualScore = new Text(Background.MAXCOLS - (Background.CELLSIZE*4), Background.PADDING, scoreText);
        setScore(score);
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
    public Text getActualScore() {
        return actualScore;
    }

}
