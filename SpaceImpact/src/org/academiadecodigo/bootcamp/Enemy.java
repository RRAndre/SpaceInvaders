package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy implements Destroyable {

    public int X;
    private int health;
    private int damage;
    private Picture enemy;
    private boolean destroyed;

    //Methods

    public Rectangle hitBox() {
        return null;
    }

    public void initEnemy() {
    }

    public void moveEnemy() {
    }


    //Getters
    public int getPos() {
        return enemy.getX();
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxX() {
        return enemy.getMaxX();
    }

    public int getWidth() {
        return 0;
    }

    ;


    //Setters
    public void removeEnemy() {
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDestroyed() {
        this.destroyed = true;
    }


    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    // update this ------> public int getX();
}
