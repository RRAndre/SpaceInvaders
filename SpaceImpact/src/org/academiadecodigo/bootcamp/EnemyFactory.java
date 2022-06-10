package org.academiadecodigo.bootcamp;

public class EnemyFactory {

    private int posYCounter = 0;
    private int enemyCounter;
    private Enemy boss;

    public EnemyFactory() {
    }

    public Enemy newEnemy() {
        //Method to define position
       // if (enemyCounter < 10) {
            Enemy newEnemy = new RegularEnemy(creationX(), createPositionY());
           // enemyCounter++;
            //System.out.println(enemyCounter);
            return newEnemy;
        }
       /* if (enemyCounter == 10) {
            boss = new Boss(Background.MAXCOLS - 270, middleY());
            System.out.println("boss");
            // enemyCounter = 0;
        }
        if(boss.isDestroyed()){
            enemyCounter = 0;
        }
         */

    public Enemy createBoss(){
        boss = new Boss(Background.MAXCOLS - 340, middleY());
        return boss;
    }
    public int creationX() {
        return Background.MAXCOLS - 70;
    }

    public int middleY() {
        return (Background.MAXROWS - Background.PADDING) / 2;
    }

    public int createPositionY() {
        if (posYCounter >= (Background.MAXROWS - Background.CELLSIZE * 8)) {
            posYCounter = 0;
        }
        posYCounter += 150;
        return posYCounter;
    }

}


