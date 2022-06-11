package org.academiadecodigo.bootcamp;

public enum Scores {

    REGULAR_ENEMY (200),
    BOSS(1000);

   public int scoreValue;

    Scores (int scoreValue){
        this.scoreValue = scoreValue;
    }
}
