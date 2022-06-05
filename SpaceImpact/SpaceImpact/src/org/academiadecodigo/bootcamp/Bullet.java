package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Bullet {

    private Picture bullet;
    private Bullet nextBullet;

    public Bullet(int col, int row){
        bullet = new Picture(col, row, "resources/bullet.jpg");
        bullet.grow(-10, -10);
        bullet.draw();
    }

    public void moveBullet(){
        if(bullet.getMaxX() < Background.MAXCOLS){
            bullet.translate(10, 0);
        }
    }
}
