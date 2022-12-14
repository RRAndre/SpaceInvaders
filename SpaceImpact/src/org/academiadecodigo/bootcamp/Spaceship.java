package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineEvent;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Spaceship implements KeyboardHandler {

    private int health = 3;
    private Picture lives;
    private Picture spaceship;
    private LinkedList<Bullet> bulletList;
    private EnemyFactory factory;
    private LinkedList<Enemy> enemyList;
    private LinkedList<Bullet> enemyBullet;
    private int enemySpeedShot;
    private int enemyCounter = 0;
    private int timer;
    private int timerEnemy;
    private ExecutorService soundThreadPool = Executors.newCachedThreadPool();
    private Sound bulletSound = new Sound("/resources/shoot.wav");
    private Score score;
    private Picture level;
    private int actualLevel;
    private static boolean endGame;
    private int enemyWaves;

    public Spaceship() throws IOException, FontFormatException {
        spaceship = new Picture(10, Background.PADDING + (Background.CELLSIZE * 5), "resources/space5.png");
        spaceship.grow(-20, -20);
        init();
        bulletList = new LinkedList<>();
        enemyList = new LinkedList<>();
        factory = new EnemyFactory();
        score = new Score();
        enemyBullet = new LinkedList<>();
    }

    public void init() {
        spaceship.draw();
        keyboardInit();
        lives = Lives.FULL_LIVES.initPic();
        actualLevel = 1;
        checkLevel();
    }

    //Methods
    public void shoot() {
        if (timer % 3 == 0) {
            bulletList.add(new Bullet(spaceship.getMaxX(), middleY() - 3, "resources/bulletpink.png"));
            soundThreadPool.submit(bulletSound);
            timer++;
        }
        timer++;
    }

    public void moveAllBullets() {
        for (int i = 0; i < bulletList.size(); i++) {
            if (bulletList.get(i).getPos() > (Background.MAXCOLS - Background.CELLSIZE)) {
                bulletList.get(i).removeBullet();
                bulletList.remove(bulletList.get(i));
            } else {
                bulletList.get(i).moveBullet();
            }
        }
    }

    public void enemyShot() {
        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i) instanceof Boss && timerEnemy % enemySpeedShot == 0) {
                enemyBullet.add(new Bullet(enemyList.get(i).hitBox().getX() - (Background.CELLSIZE * 4), middleY(), "resources/bulletyellow.png"));
            timerEnemy++;
            }
            timerEnemy++;
        }
    }

    public void moveEnemyBullets() {
        for (int i = 0; i < enemyBullet.size(); i++) {
            if (enemyBullet.get(i).getX() > Background.PADDING) {
                enemyBullet.get(i).moveEnemyBullet();
            } else {
                enemyBullet.get(i).removeBullet();
                enemyBullet.remove(enemyBullet.get(i));
            }
        }
    }

    public void createEnemy() throws InterruptedException {
        if (enemyCounter < enemyWaves) {
            if (enemyList.size() < 5) {
                enemyList.add(factory.newEnemy());
                enemyCounter++;
            }
        }
        if (enemyCounter == enemyWaves && checkAllEnemyDead()) {
            enemyList.add(factory.createBoss());
            enemyCounter++;
        }
        if (enemyCounter == enemyWaves+1 && checkAllEnemyDead()) {
            actualLevel++;
            enemyCounter = 0;
            checkLevel();

        }
    }

    public void checkLevel() {
        switch (actualLevel) {
            case 1:
                level = LevelUp.LEVEL_ONE.initPic();
                enemyWaves = 10;
                enemySpeedShot = 8;
                break;
            case 2:
                level.delete();
                level = LevelUp.LEVEL_TWO.initPic();
                enemyWaves = 20;
                enemySpeedShot = 5;
                break;
            case 3:
                level.delete();
                level = LevelUp.LEVEL_THREE.initPic();
                enemyWaves = 30;
                enemySpeedShot = 3;
                break;
            case 4:
                level.delete();
                level = LevelUp.LEVEL_FOUR.initPic();
                enemyWaves = 40;
                enemySpeedShot = 2;
                break;
            case 5:
                level.delete();
                level = LevelUp.LEVEL_FIVE.initPic();
                enemyWaves = 50;
                enemySpeedShot = 1;
                break;
        }
    }

    /* if(checkAllEnemyDead() && enemyCounter >= 11 && enemyCounter < 16){
         if (enemyList.size() < 5) {
             enemyList.add(factory.newEnemy());
             enemyCounter++;
         }
     }
     if (enemyCounter == 16 && checkAllEnemyDead()) {
         enemyList.add(factory.createBoss());
         enemyCounter++;
     }

     */
    public boolean checkAllEnemyDead() {
        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void moveAllEnemies() throws IOException {
        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isDestroyed()) {
                enemyList.get(i).moveEnemy();
            }
            if (enemyList.get(i).hitBox().getX() < Background.PADDING) {
                enemyList.get(i).removeEnemy();
                enemyList.remove(enemyList.get(i));
                health--;
                lives();
                loosingLives();
            }
        }
    }

    public void collision() {
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < enemyList.size(); j++) {
                if (i >= bulletList.size()) {
                    continue;
                }
                if (bulletList.get(i).hitBox().getX() < enemyList.get(j).hitBox().getWidth() &&
                        bulletList.get(i).hitBox().getY() < enemyList.get(j).hitBox().getHeight() &&
                        enemyList.get(j).hitBox().getX() < bulletList.get(i).hitBox().getWidth() &&
                        enemyList.get(j).hitBox().getY() < bulletList.get(i).hitBox().getHeight() &&
                        !enemyList.get(j).isDestroyed()) {
                    enemyList.get(j).hit(Bullet.BULLETDAMAGE);
                    bulletList.get(i).removeBullet();
                    bulletList.remove(bulletList.get(i));
                    if (enemyList.get(j).isDestroyed()) {
                        if (enemyList.get(j) instanceof RegularEnemy) {
                            score.setScore(Scores.REGULAR_ENEMY.scoreValue);
                        }
                        if (enemyList.get(j) instanceof Boss) {
                            score.setScore(Scores.BOSS.scoreValue);
                        }
                        enemyList.get(j).removeEnemy();
                        enemyList.remove(enemyList.get(j));
                    }

                }
            }
        }
    }

    public void spaceBossCollision() throws IOException {
        for (int i = 0; i < enemyBullet.size(); i++) {
            if (spaceship.getX() < enemyBullet.get(i).hitBox().getWidth() &&
                    spaceship.getMaxX() > enemyBullet.get(i).hitBox().getX() &&
                    spaceship.getY() < enemyBullet.get(i).hitBox().getHeight() &&
                    spaceship.getMaxY() > enemyBullet.get(i).hitBox().getY()) {
                health--;
                lives();
                loosingLives();
            }
        }
    }

    public void spaceshipEnemiesCollision() throws IOException {
        for (int i = 0; i < enemyList.size(); i++) {
            if (spaceship.getX() < enemyList.get(i).hitBox().getWidth() &&
                    spaceship.getMaxX() > enemyList.get(i).hitBox().getX() &&
                    spaceship.getY() < enemyList.get(i).hitBox().getHeight() &&
                    spaceship.getMaxY() > enemyList.get(i).hitBox().getY()) {
                health--;
                lives();
                loosingLives();
            }
        }
    }

    public void loosingLives() {
        spaceship.translate(-(spaceship.getX() - Background.PADDING), 0);
    }

    public void lives() throws IOException {
        switch (health) {
          /* case 3:
               lives = Lives.FULL_LIVES.initPic();
               break;

           */
            case 2:
                lives.delete();
                lives = Lives.TWO_LIVES.initPic();
                break;
            case 1:
                lives.delete();
                lives = Lives.ONE_LIVES.initPic();
                break;
            case 0:
                lives.delete();
                lives = Lives.NO_LIVES.initPic();
                SpaceImpactGame.endGame();
                endGame = true;
               //Score.saveHighestScore();
                break;
            //TODO Game Over
        }
    }

    public int middleY() {
        return (spaceship.getY() + spaceship.getMaxY()) / 2;
    }

    //Getters
    public int getHealth() {
        return health;
    }
    //Setters

    public void setHealth(int health) {
        this.health = health;
    }

    private void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        //RIGHT KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_RIGHT);
        //LEFT KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_LEFT);
        //UP KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_UP);
        //DOWN KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_DOWN);
        //Q KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_Q);

    }

    //Moves
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            if (spaceship.getX() >= Background.PADDING + Background.CELLSIZE) {
                spaceship.translate(-10, 0);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            if (spaceship.getMaxX() <= (Background.MAXCOLS / 2)) {
                spaceship.translate(10, 0);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            if (spaceship.getY() >= Background.PADDING + (Background.CELLSIZE * 5)) {
                spaceship.translate(0, -10);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            if (spaceship.getMaxY() <= Background.MAXROWS) {
                spaceship.translate(0, 10);
            }
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        //maybe later
    }

    public void removeSpace() {
        spaceship.delete();
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).removeEnemy();
            enemyList.remove(enemyList.get(i));
        }
        score.removeText();
        lives.delete();
        level.delete();
        for (int i = 0; i < enemyBullet.size(); i++) {
            enemyBullet.get(i).removeBullet();
            enemyBullet.remove(enemyBullet.get(i));
        }
    }

    public static boolean isEndGame() {
        return endGame;
    }
}
