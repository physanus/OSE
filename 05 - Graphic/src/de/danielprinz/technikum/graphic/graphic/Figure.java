package de.danielprinz.technikum.graphic.graphic;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Figure {

    protected int x, y;

    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public abstract void draw(Graphics g);
    public abstract boolean collides(MouseEvent e);
    public abstract void setPosition(int x, int y);

}
