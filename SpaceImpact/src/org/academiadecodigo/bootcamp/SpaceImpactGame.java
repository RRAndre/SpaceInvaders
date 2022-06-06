package org.academiadecodigo.bootcamp;

import java.util.LinkedList;

public class SpaceImpactGame {

    private Spaceship spaceship;
    private InitialMenu menu;
    private Background background;
    private EnemyFactory factory;
    private LinkedList<Enemy> enemyList;

    public SpaceImpactGame(){
        //Initial menu all fucked up
        //menu = new InitialMenu();
        startGameObjects();
    }

    public void createEnemy(){
        enemyList.add(factory.newEnemy());
    }

    public void moveAllEnemies(){
        //Method incomplete
        //Use if you want to break the game
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).moveEnemy();
        }
    }

    public void startGameObjects(){
        background = new Background();
        spaceship = new Spaceship();
        enemyList = new LinkedList<>();
        factory = new EnemyFactory();
    }
    public void startGame(){

        while(true){

            try {
                spaceship.shoot();
                spaceship.moveAllBullets();
                createEnemy();
                Thread.sleep(200);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
