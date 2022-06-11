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

    public SpaceImpactGame() {
        startGameObjects();
    }

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
                spaceship.spaceshipEnemiesCollision();

                Thread.sleep(400);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
