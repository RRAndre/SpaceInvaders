package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class RegularEnemy extends Enemy {

    private Picture enemy;
    private Rectangle hitBox;

    public RegularEnemy(int col, int row) {
        enemy = new Picture(col, row, "resources/enemy.png");
       //enemy.grow(-15, -15);
        setHealth(20);
        initEnemy();
    }

    public void initEnemy() {
        enemy.draw();
    }

    public void removeEnemy() {
        enemy.delete();
    }

    public void moveEnemy() {
        enemy.translate(-Background.CELLSIZE, 0);
    }

    //Getters
    public int getPosX(){
        return enemy.getX();
    }

    public int getMaxX(){
        return enemy.getMaxX();
    }

    @Override
    public void hit(int damage) {
        if (getHealth() > 0) {
            setHealth(getHealth() - damage);
        } else {
            setDestroyed();
            removeEnemy();
        }
    }

    public Rectangle hitBox(){
        this.hitBox = new Rectangle(enemy.getX(), enemy.getY(), enemy.getMaxX(), enemy.getMaxY());
        return  hitBox;
    }
}
