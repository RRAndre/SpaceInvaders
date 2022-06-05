package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Enemy implements Destroyable{

    private int health;
    private int damage;
    private Picture enemy;
    private boolean dead;

    //Methods

    public void shoot(){}
    public void killEnemy(){
        //while enemy not dead bullet.move()
    }

    //Getters

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() {
        return dead;
    }

    //Setters

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
