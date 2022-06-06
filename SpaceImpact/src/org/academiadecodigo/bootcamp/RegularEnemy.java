package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class RegularEnemy extends Enemy {

    private Picture enemy;

    public RegularEnemy(int col, int row) {
        enemy = new Picture(col, row, "resources/enemy3.png");
        initEnemy();
    }

    public void initEnemy() {
        enemy.grow(-10,-10);
        enemy.draw();


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
    public int enemyGetX() {
        return enemy.getX();
    }
}
