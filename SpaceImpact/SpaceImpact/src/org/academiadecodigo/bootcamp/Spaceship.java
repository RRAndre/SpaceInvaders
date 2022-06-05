package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.*;

public class Spaceship implements KeyboardHandler {

    private int health;
    private int damage;
    private Picture spaceship;
    private Bullet bullet;

    public Spaceship(){
        spaceship = new Picture(10,10, "resources/space.png");
        spaceship.grow(-50,-50);
    }

    public void init(){
        spaceship.draw();
        keyboardInit();
    }

    //Methods
    public void shoot(){
        bullet = new Bullet(spaceship.getMaxX(), middleY());
        bullet.moveBullet();
    }

    public int middleY(){
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
        KeyboardEvent rightKeyPressed = new KeyboardEvent();
        rightKeyPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(rightKeyPressed);


        //LEFT KEY
        KeyboardEvent leftKeyPressed = new KeyboardEvent();
        leftKeyPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leftKeyPressed);

        //UP KEY
        KeyboardEvent upKeyPressed = new KeyboardEvent();
        upKeyPressed.setKey(KeyboardEvent.KEY_UP);
        upKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(upKeyPressed);

        //DOWN KEY
        KeyboardEvent downKeyPressed = new KeyboardEvent();
        downKeyPressed.setKey(KeyboardEvent.KEY_DOWN);
        downKeyPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(downKeyPressed);
    }

    //Moves
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if( keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) {
            if(spaceship.getX() >= Background.PADDING) {
                spaceship.translate(-10, 0);
            }
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) {
            if(spaceship.getMaxX() <= (Background.MAXCOLS/2)) {
                spaceship.translate(10, 0);
            }
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            if (spaceship.getY() >= Background.PADDING) {
                spaceship.translate(0, -10);
            }
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            if (spaceship.getMaxY() <= Background.MAXROWS){
                spaceship.translate(0,10);
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        //maybe later
    }

}