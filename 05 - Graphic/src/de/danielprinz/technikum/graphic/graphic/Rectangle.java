package de.danielprinz.technikum.graphic.graphic;

import de.danielprinz.technikum.graphic.gui.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by el17x002 on 15.10.2018.
 */
public class Rectangle extends Figure {

    private int width, height;
    private Color lineColor;
    private Color fillColor;
    private boolean fill;

    public Rectangle(int x, int y, int width, int height, Color lineColor, Color fillColor, boolean fill) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.fill = fill;
    }

    @Override
    public void draw(Graphics g) {
        if(fill) {
            g.setColor(fillColor);
            g.fillRect(x, y, width, height);
        }
        g.setColor(lineColor);
        g.drawRect(x, y, width, height);

        g.setColor(Main.getPanel().getDefaultColor());
    }

    @Override
    public boolean collides(MouseEvent e) {
        // x, y: upper left corner
        final int clickX = e.getX();
        final int clickY = e.getY();

        if(fill) {
            return x <= clickX && x + width  >= clickX &&
                   y <= clickY && y + height >= clickY;
        } else {
            // TODO implement
            return x <= clickX && x + width  >= clickX &&
                   y <= clickY && y + height >= clickY;
        }

    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x - width / 2;
        this.y = y - height / 2;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", lineColor=" + lineColor +
                ", fillColor=" + fillColor +
                ", fill=" + fill +
                '}';
    }
}
