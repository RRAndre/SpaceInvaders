package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Boss extends Enemy {

    private Picture boss;
    private Rectangle hitBox;
    private int health = 1500;
    private int timer;

    public Boss(int col, int row) {
        boss = new Picture(col, row, "resources/boss2.png");
        initBoss();
    }

    public void initBoss() {
        boss.draw();
        boss.grow(100,100);
    }

    public void removeEnemy() {
            boss.delete();
    }

    public void moveEnemy() {
        if(timer >= 0 && timer < 12){
            boss.translate(0, -Background.CELLSIZE);
            timer++;
        }
        if (timer >= 12 && timer < 24) {
            boss.translate(0, Background.CELLSIZE);
            timer++;
        }
        if (timer == 24) {
            timer = 0;
        }
    }
    public int getWidth(){
        return boss.getWidth();
    }

    @Override
    public void hit(int damage) {
            health -= damage;
            if(health <= 0){
                setDestroyed();
            }

    }

    public Rectangle hitBox() {
        this.hitBox = new Rectangle(boss.getX(), boss.getY(), boss.getMaxX(), boss.getMaxY());
        return hitBox;
    }
}
