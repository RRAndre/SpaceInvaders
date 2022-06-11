package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.io.IOException;

public class SpaceImpactGame implements KeyboardHandler {

    private Spaceship spaceship;
    private SoundClass sound;
    private static boolean gameStarted = true;
    private static boolean endGame;
    private boolean startMenu = true;
    private Picture menuPic;
    //private StartMenu menu;

    public SpaceImpactGame() throws IOException, FontFormatException {
        keyboardInit();
        createMenu();
    }

    public void createMenu() {
        menuPic = new Picture(Background.PADDING, Background.PADDING, "resources/startgame.png");
        menuPic.draw();
       // menu = new StartMenu();
    }

    public void startGameObjects() throws IOException, FontFormatException {
        spaceship = new Spaceship();
    }

    public void startMenu(){
        while (startMenu) {
            System.out.println("ola");
        }
        menuPic.delete();
        gameStarted = true;
    }
    public void startGame() throws IOException, FontFormatException {
        while (gameStarted) {
            startGameObjects();
            try {
                spaceship.shoot();
                spaceship.moveAllBullets();
                spaceship.createEnemy();
                spaceship.moveAllEnemies();
                spaceship.collision();
                spaceship.spaceshipEnemiesCollision();

                Thread.sleep(200);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (endGame) {
            Picture gameOver = new Picture(Background.PADDING, Background.PADDING, "resources/gameover.png");
            gameOver.draw();
        }
    }

    public static void endGame() {
        gameStarted = false;
        endGame = true;
    }

    public static void reStart() {
        gameStarted = true;
    }

    private void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);
        //Y KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_Y);
        //P KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_P);
        //N KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_N);
        //S KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_S);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Y) {
            reStart();
            try {
                startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_N) {
            createMenu();
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_P) {
            gameStarted = false;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            startMenu = false;
            //menu.removeSound();
            try {
                startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
