package org.academiadecodigo.bootcamp;

public enum Scores {

    REGULARENEMY (200),
    BOSS(1000);

   public int scoreValue;

    Scores (int scoreValue){
        this.scoreValue = scoreValue;
    }
}
