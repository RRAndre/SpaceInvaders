package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Iterator;
import java.util.LinkedList;

public class Spaceship implements KeyboardHandler {

    private int health;
    private Picture spaceship;
    private LinkedList<Bullet> bulletList;
    private EnemyFactory factory;
    private LinkedList<Enemy> enemyList;

    public Spaceship() {
        spaceship = new Picture(10, 10, "resources/space.png");
        spaceship.grow(-50, -50);
        init();
        bulletList = new LinkedList<>();
        enemyList = new LinkedList<>();
        factory = new EnemyFactory();
    }

    public void init() {
        spaceship.draw();
        keyboardInit();
    }

    //Methods
    public void shoot() {
        bulletList.add(new Bullet(spaceship.getMaxX(), middleY() - 3));
       // SoundClass.play();
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
        if (enemyList.size() < 5) {
            enemyList.add(factory.newEnemy());
        }
    }

    public void moveAllEnemies() {
        for (int i = 0; i < enemyList.size(); i++) {
            if (!enemyList.get(i).isDestroyed()) {
                enemyList.get(i).moveEnemy();
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
                    enemyList.get(j).setDestroyed();
                    enemyList.get(j).removeEnemy();
                    enemyList.remove(enemyList.get(j));
                }
            }
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
            if (spaceship.getX() >= Background.PADDING) {
                spaceship.translate(-10, 0);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            if (spaceship.getMaxX() <= (Background.MAXCOLS / 2)) {
                spaceship.translate(10, 0);
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            if (spaceship.getY() >= Background.PADDING) {
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
