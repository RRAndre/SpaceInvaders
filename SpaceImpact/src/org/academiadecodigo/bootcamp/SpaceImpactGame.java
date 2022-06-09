package org.academiadecodigo.bootcamp;

import java.util.LinkedList;

public class SpaceImpactGame {

    private Spaceship spaceship;
    private InitialMenu menu;
    private Background background;

    public SpaceImpactGame() {
        //Initial menu all fucked up
        //menu = new InitialMenu();
        startGameObjects();
    }


   /* public void moveAllEnemies(){
        //Method incomplete
        //Use if you want to break the game
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).moveEnemy();
        }
    }*/

    public void startGameObjects() {
        background = new Background();
        spaceship = new Spaceship();
    }

    public void startGame() {

        while (true) {


            try {
                spaceship.shoot();

                spaceship.moveAllBullets();
                spaceship.createEnemy();
                spaceship.moveAllEnemies();
                spaceship.collision();

                SoundClass.play();
               // Thread.sleep(20);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
