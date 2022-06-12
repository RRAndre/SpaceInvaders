package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import java.io.FileInputStream;
import java.io.IOException;

public class HighScore {

    private Text highestScore;


    public HighScore() throws IOException {
        highestScore = new Text(Background.MAXCOLS/2, Background.MAXROWS - 200, Score.readHighestScore());
        highestScore.setColor(Color.CYAN);
        highestScore.grow(50,50);
        highestScore.draw();
    }

}
