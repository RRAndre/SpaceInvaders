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
    private static boolean gameStarted = false;
    private static boolean endGame;
    private Picture endGamePic;
    private boolean startMenu = true;
    private Picture menuPic;
    private Background background;
    private StartMenu soundStart;
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                startGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }) ;

    public SpaceImpactGame() throws IOException, FontFormatException {
        keyboardInit();

        // createMenu();
    }

    public void createMenu() {
        startMenu = true;
        menuPic = new Picture(Background.PADDING, Background.PADDING, "resources/startmenu.png");
        menuPic.draw();
        soundStart = new StartMenu();
    }

    public void startGameObjects() throws IOException, FontFormatException {
        background = new Background();
        spaceship = new Spaceship();

    }

    public void startGame() throws IOException, FontFormatException {
        thread.start();
        menuPic = new Picture(Background.PADDING, Background.PADDING, "resources/startmenu.png");
        startMenu = true;
        while (startMenu) {

            try {
                menuPic.draw();
                soundStart = new StartMenu();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        startGameObjects();

        while (gameStarted) {
            menuPic.delete();
            soundStart.removeSound();

            try {
                spaceship.shoot();
                spaceship.moveAllBullets();
                spaceship.createEnemy();
                spaceship.moveAllEnemies();
                spaceship.collision();
                spaceship.spaceshipEnemiesCollision();
                spaceship.enemyShot();
                spaceship.moveEnemyBullets();
                Thread.sleep(200);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (endGame) {
            endGamePic = new Picture(Background.PADDING, Background.PADDING, "resources/gameover.png");
            endGamePic.draw();
        }
    }

    public static void endGame() {
        gameStarted = false;
        endGame = true;
    }

    public void reStart() throws IOException, FontFormatException {
        spaceship.removeSpace();
        thread.stop();
        endGame = false;
        endGamePic.delete();
        background.removeBackground();
        startGame();
        System.out.println( Thread.activeCount());
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

            try {
                reStart();
                gameStarted = true;

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FontFormatException e) {
                throw new RuntimeException(e);
            }
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_N) {
           // createMenu();
            startMenu = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_P) {
            gameStarted = false;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            startMenu = false;
            gameStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
