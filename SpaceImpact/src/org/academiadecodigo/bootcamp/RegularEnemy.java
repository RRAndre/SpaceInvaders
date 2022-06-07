package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class RegularEnemy extends Enemy {

    private Picture enemy;
    private Rectangle hitBox;

    public RegularEnemy(int col, int row) {
        enemy = new Picture(col, row, "resources/enemy3.png");
        initEnemy();
    }

    public void initEnemy() {
        enemy.grow(-10, -10);
        enemy.draw();
        setHealth(20);
    }

    public void removeEnemy() {
        enemy.delete();
    }

    public void moveEnemy() {
        int enemyMoveX = enemy.getX() - (Background.CELLSIZE * 2);
        int enemyMoveY = enemy.getY() + (2 * Background.CELLSIZE);
        int moveX = enemyMoveX < Background.MAXCOLS ? enemyMoveY : Background.MAXCOLS;
        int moveY = enemyMoveY < Background.MAXROWS ? enemyMoveY : Background.MAXROWS;
        enemy.translate(moveX, moveY);
        System.out.println("here");
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
