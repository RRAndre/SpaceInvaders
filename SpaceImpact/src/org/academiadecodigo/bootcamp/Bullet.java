package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Bullet implements Destroyable{
        private Picture bullet;
        public static final int BULLETDAMAGE = 20;

        public Bullet(int col, int row, String pic) {
            bullet = new Picture(col, row, pic);
            initBullet();
        }

        public void initBullet(){
            //bullet.grow(-5, -5);
            bullet.draw();
        }

        public void removeBullet(){
            bullet.delete();
        }

        public void moveBullet() {
            if (bullet.getMaxX() < (Background.MAXCOLS - Background.CELLSIZE)) {
                bullet.translate(Background.CELLSIZE * 3, 0);
            } else {
                removeBullet();
            }
        }

    public void moveEnemyBullet() {
        if (bullet.getX() > Background.PADDING) {
            bullet.translate( -Background.CELLSIZE, 0);
        } else {
            removeBullet();
        }
    }

        public Rectangle hitBox(){
            Rectangle hitBox = new Rectangle(bullet.getX(), bullet.getY(), bullet.getMaxX(), bullet.getMaxY());
            return  hitBox;
        }
        public int getPos(){
            return bullet.getMaxX();
        }

    @Override
    public void hit(int damage) {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }
}

