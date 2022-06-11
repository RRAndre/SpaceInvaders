package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

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
    private int enemyCounter = 0;
    private int timer;
    private ExecutorService soundThreadPool = Executors.newCachedThreadPool();
    private SoundClass sound;
    private Score score;

    public Spaceship() {
        spaceship = new Picture(10, Background.PADDING + (Background.CELLSIZE*5), "resources/space5.png");
        spaceship.grow(-20, -20);
        init();
        bulletList = new LinkedList<>();
        enemyList = new LinkedList<>();
        factory = new EnemyFactory();
        sound = new SoundClass();
        score = new Score();
    }

    public void init() {
        spaceship.draw();
        keyboardInit();
        lives = Lives.FULL_LIVES.initPic();
    }

    //Methods
    public void shoot() {
        if (timer % 3 == 0) {
            bulletList.add(new Bullet(spaceship.getMaxX(), middleY() - 3));
            soundThreadPool.submit(sound);
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

    public void createEnemy() throws InterruptedException {
        if (enemyCounter < 10) {
            if (enemyList.size() < 5) {
                enemyList.add(factory.newEnemy());
                enemyCounter++;
            }
        }
        if (enemyCounter == 10 && checkAllEnemyDead()) {
            enemyList.add(factory.createBoss());
            enemyCounter++;
        }
    }

    public boolean checkAllEnemyDead() {
        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isDestroyed()) {
                return false;
            }
        }
        return true;
    }

    public void moveAllEnemies() {
        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isDestroyed()) {
                enemyList.get(i).moveEnemy();
            }
            if(enemyList.get(i).hitBox().getX() < Background.PADDING){
                enemyList.get(i).removeEnemy();
                enemyList.remove(enemyList.get(i));
            }
        }
    }

    public void collision() {
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < enemyList.size(); j++) {
                if (bulletList.get(i).hitBox().getX() < enemyList.get(j).hitBox().getWidth() &&
                        bulletList.get(i).hitBox().getY() < enemyList.get(j).hitBox().getHeight() &&
                        enemyList.get(j).hitBox().getX() < bulletList.get(i).hitBox().getWidth() &&
                        enemyList.get(j).hitBox().getY() < bulletList.get(i).hitBox().getHeight()) {
                    enemyList.get(j).hit(Bullet.BULLETDAMAGE);
                    bulletList.get(i).removeBullet();
                    bulletList.remove(bulletList.get(i));
                    if (enemyList.get(j).isDestroyed()) {
                        System.out.println(score.getScore());
                        score.setScore(Scores.REGULARENEMY.scoreValue);
                        enemyList.get(j).removeEnemy();
                        enemyList.remove(enemyList.get(j));
                    }

                }
            }
        }
    }

    public void spaceshipEnemiesCollision() {
        for (int i = 0; i < enemyList.size(); i++) {
            if(spaceship.getX() < enemyList.get(i).hitBox().getWidth() &&
            spaceship.getMaxX() > enemyList.get(i).hitBox().getX() &&
            spaceship.getY() < enemyList.get(i).hitBox().getHeight() &&
            spaceship.getMaxY() > enemyList.get(i).hitBox().getY()){
                enemyList.get(i).removeEnemy();
                enemyList.remove(enemyList.get(i));
                health--;
                lives();
                //shakeSpaceship();
                if(health == 0){
                    //Canvas.snapshot();
                    //Canvas.pause();
                }
                System.out.println(health);
            }
        }
    }

    public void shakeSpaceship(){
        try {
        spaceship.delete();
        spaceship.draw();
        spaceship.delete();
        spaceship.draw();
            Thread.sleep(100);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public void lives(){
       switch (health){
           case 2:
               lives = Lives.TWO_LIVES.initPic();
               break;
           case 1 :
               lives = Lives.ONE_LIVES.initPic();
               break;
           case 0:
               lives = Lives.NO_LIVES.initPic();
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
            if (spaceship.getY() >= Background.PADDING + (Background.CELLSIZE*5)) {
                spaceship.translate(0, -10);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            if (spaceship.getMaxY() <= Background.MAXROWS) {
                spaceship.translate(0, 10);
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        //maybe later
    }

}
