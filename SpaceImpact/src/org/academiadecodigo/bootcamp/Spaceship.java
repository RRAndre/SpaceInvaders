package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Spaceship implements KeyboardHandler {

    private int health;
    private int damage;
    private Picture spaceship;
    private LinkedList<Bullet> bulletList;

    public Spaceship() {
        spaceship = new Picture(10, 10, "resources/space.png");
        spaceship.grow(-50, -50);
        init();
        bulletList = new LinkedList<>();
    }

    public void init() {
        spaceship.draw();
        keyboardInit();
    }

    //Methods
    public void shoot() {
        bulletList.add(new Bullet(spaceship.getMaxX(), middleY() - 3));
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

    public int middleY() {
        return (spaceship.getY() + spaceship.getMaxY()) / 2;
    }

    //Getters

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }


    //Setters

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
