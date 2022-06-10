package org.academiadecodigo.bootcamp;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpaceImpactGame {

    private Spaceship spaceship;
    private InitialMenu menu;
    private Background background;
    private SoundClass sound;

   private ExecutorService soundThreadPool = Executors.newCachedThreadPool();

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
        sound = new SoundClass();
    }

    public void startGame() {

        while (true) {


            try {
                spaceship.shoot();
                soundThreadPool.submit(sound);
                spaceship.moveAllBullets();
                spaceship.createEnemy();
                spaceship.moveAllEnemies();
                spaceship.collision();

                Thread.sleep(200);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
