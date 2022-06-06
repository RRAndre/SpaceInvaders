package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;

public class KeyboardSetup {

    public static void keyboardInit(Keyboard keyboard, KeyboardEventType keyboardEventType, int key){

        KeyboardEvent pressedKey = new KeyboardEvent();
        pressedKey.setKey(key);
        pressedKey.setKeyboardEventType(keyboardEventType);
        keyboard.addEventListener(pressedKey);

    }
}
