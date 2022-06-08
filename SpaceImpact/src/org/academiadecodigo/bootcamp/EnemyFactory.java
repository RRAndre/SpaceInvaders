package org.academiadecodigo.bootcamp;

public class EnemyFactory {

    private int posYCounter = 0;
    private int[] waveOne;
    public EnemyFactory(){
        waveOne = new int[4];
    }

    public Enemy newEnemy(){
        //Method to define position
       Enemy newEnemy = new RegularEnemy(creationX(), createPositionY());
        return newEnemy;
    }

    //Update method to create order position coming from MAXCOLS
    //public int createPositionX(){
       /* int randomX = (int) (Math.random() * creationLimit());
        int xPos = creationLimit() + randomX;
        if(xPos < Background.MAXCOLS - Background.CELLSIZE * 3) {
            return xPos;
        }
        return creationLimit() + Background.CELLSIZE * 4; */

    public int creationX(){
        return Background.MAXCOLS - 70;
    }

    public int createPositionY(){
        if(posYCounter >= (Background.MAXROWS - Background.CELLSIZE*8)){
            posYCounter = 0;
        }
            posYCounter += 150;
            return posYCounter;
        }

    }


