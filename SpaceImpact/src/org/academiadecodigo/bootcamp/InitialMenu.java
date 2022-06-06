package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class InitialMenu implements KeyboardHandler {

    private Picture pictureNewGame;
    private Picture pictureHighScore;

    public InitialMenu(){
        pictureNewGame = new Picture(Background.PADDING, Background.PADDING, "resources/startmenu.png");
        pictureHighScore = new Picture(Background.PADDING, Background.PADDING, "resources/menuHighScore.png");
        initMenu();
    }

    public void initMenu(){
        pictureNewGame.draw();
        keyboardInit();
    }

    private void keyboardInit(){

        Keyboard keyboard = new Keyboard(this);

        //UP KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_UP);

        //DOWN KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_DOWN);

        //SELECT KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_SPACE);

    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_UP) {
            pictureHighScore.delete();
            pictureNewGame.draw();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) {
            pictureHighScore.draw();
            pictureNewGame.delete();

        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            pictureNewGame.delete();
            pictureHighScore.delete();
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
            //maybe later
    }
}
