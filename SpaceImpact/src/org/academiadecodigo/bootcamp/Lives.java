package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public enum Lives {

    FULL_LIVES(new Picture(820, Background.PADDING + Background.CELLSIZE, "resources/fulllife.png")),
    TWO_LIVES(new Picture(820, Background.PADDING + Background.CELLSIZE, "resources/2lifes.png")),
    ONE_LIVES(new Picture(820, Background.PADDING + Background.CELLSIZE, "resources/1life.png")),
    NO_LIVES(new Picture(820, Background.PADDING + Background.CELLSIZE, "resources/nolifes.png"));

    public Picture picture;

    Lives(Picture picture){
        this.picture = picture;
    }

    public Picture initPic(){
        picture.draw();
        return picture;
    }

    public void deleteLives(){
        picture.delete();
    }
}
