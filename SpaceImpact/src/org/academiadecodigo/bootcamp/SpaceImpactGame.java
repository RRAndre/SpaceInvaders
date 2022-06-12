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
    private static boolean gameIsActive = true;
    private static boolean endGame;
    private Picture endGamePic;
    private boolean startMenu = true;
    private Picture menuPic;
    private Background background;
    private boolean holdGame = false;
    private StartMenu soundStart;
    private boolean actualGame;

    private Sound introSound = new Sound("resources/retro_space.wav");

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
    });

    public SpaceImpactGame() throws IOException, FontFormatException {
        keyboardInit();
        // createMenu();
    }

    public void createMenu() {
        startMenu = true;
        menuPic = new Picture(Background.PADDING, Background.PADDING, "resources/startmenu.png");
        menuPic.draw();
        //soundStart = new StartMenu();
        introSound.play(true);

    }

    public void startGameObjects() throws IOException, FontFormatException {
        background = new Background();
        spaceship = new Spaceship();

    }

    synchronized public void startGame() throws IOException, FontFormatException {

        menuPic = new Picture(Background.PADDING, Background.PADDING, "resources/startmenu.png");
        menuPic.draw();
        soundStart = new StartMenu();
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menuPic.delete();
        introSound.stop();


        startGameObjects();

        while (gameIsActive) {

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
            if (endGame) {
                endGamePic = new Picture(Background.PADDING, Background.PADDING, "resources/gameover.png");
                endGamePic.draw();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                reStart();
                startGameObjects();
            }
        }
    }


    public static void endGame() {
        // gameStarted = false;
        endGame = true;
    }

    public void reStart() throws IOException, FontFormatException {
        endGame = false;
        endGamePic.delete();
        background.removeBackground();
        spaceship.removeSpace();
    }

    private void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);
        //RESTART KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_R);
        //P KEY
        //KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_P);
        //N KEY
        //KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_N);
        //START KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_S);
        //QUIT KEY
        KeyboardSetup.keyboardInit(keyboard, KeyboardEventType.KEY_PRESSED, KeyboardEvent.KEY_Q);

    }

    @Override
    synchronized public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R && endGame) {
            gameIsActive = true;
            this.notifyAll();
        }
        /*if (keyboardEvent.getKey() == KeyboardEvent.KEY_N) {
            holdGame = true;
            startMenu = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_P) {
            gameIsActive = false;
        }

         */
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_S) {
            this.notifyAll();
            startMenu = false;
            gameIsActive = true;
        }
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
