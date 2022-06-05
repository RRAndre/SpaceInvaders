package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bullet {
        private Picture bullet;

        public Bullet(int col, int row) {
            bullet = new Picture(col, row, "resources/bullet.png");
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

        public int getPos(){
            return bullet.getMaxX();
        }
}

