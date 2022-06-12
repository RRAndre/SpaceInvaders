package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public enum LevelUp {
    LEVEL_ONE ( new Picture(Background.PADDING, Background.PADDING, "resources/level1.png")),
    LEVEL_TWO ( new Picture(Background.PADDING, Background.PADDING, "resources/level2.png")),
    LEVEL_THREE( new Picture(Background.PADDING, Background.PADDING, "resources/level3.png")),
    LEVEL_FOUR( new Picture(Background.PADDING, Background.PADDING, "resources/level4.png")),
    LEVEL_FIVE( new Picture(Background.PADDING, Background.PADDING, "resources/level5.png"));

    public Picture picture;

    LevelUp(Picture picture){
        this.picture = picture;
    }

    public Picture initPic(){
        picture.draw();
        return picture;
    }

    public void deleteLvl(){
        picture.delete();
    }
}
