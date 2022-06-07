package org.academiadecodigo.bootcamp;

public class EnemyFactory {


    public EnemyFactory(){

    }

    public Enemy newEnemy(){
        //Method to define position
       Enemy newEnemy = new RegularEnemy(createPositionX(), createPositionY());
        return newEnemy;
    }

    //Update method to create order position coming from MAXCOLS
    public int createPositionX(){
        int randomX = (int) (Math.random() * creationLimit());
        int xPos = creationLimit() + randomX;
        if(xPos < Background.MAXCOLS - Background.CELLSIZE * 3) {
            return xPos;
        }
        return creationLimit() + Background.CELLSIZE * 4;
    }
    public int creationLimit(){
        return Background.MAXCOLS / 2;
    }

    public int createPositionY(){
        int randomY = (int) (Math.random() * Background.MAXROWS);
        if(randomY > (Background.PADDING * 5) && randomY < (Background.MAXROWS - (Background.PADDING * 6))){
            return randomY;
        }
        return Background.MAXROWS /2;
    }

}
