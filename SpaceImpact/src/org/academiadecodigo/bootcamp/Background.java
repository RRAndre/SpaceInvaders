package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import javax.swing.*;
import java.awt.*;

public class Background {

    private Picture background;
    private Rectangle temp;
    public static final int CELLSIZE = 20;
    public static final int MAXCOLS = 95 * CELLSIZE;
    public static final int MAXROWS = 45 * CELLSIZE;;
    public static final int PADDING = 10;

    public Background(){
        temp = new Rectangle(PADDING,PADDING,MAXCOLS, MAXROWS);
        background = new Picture(PADDING, PADDING, "resources/background.png");
        init();
    }

    public void init(){
        background.draw();
        //temp.setColor(Color.LIGHT_GRAY);
        //temp.fill();
    }

    public static int middleX(){
        return Background.MAXCOLS/2;
    }

}
