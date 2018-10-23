package de.danielprinz.technikum.graphic.graphic;

import de.danielprinz.technikum.graphic.gui.Main;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by el17x002 on 15.10.2018.
 */
public class Circle extends Figure {

    private int radius;
    private Color lineColor;
    private Color fillColor;
    boolean fill;

    public Circle(int x, int y, int radius, Color lineColor, Color fillColor, boolean fill) {
        super(x, y);
        this.radius = radius;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
        this.fill = fill;
    }

    @Override
    public void draw(Graphics g) {
        if(fill) {
            g.setColor(fillColor);
            g.fillOval(x - radius, y - radius, 2*radius, 2*radius);
        }
        g.setColor(lineColor);
        g.drawOval(x - radius, y - radius, 2*radius, 2*radius);

        g.setColor(Main.getPanel().getDefaultColor());
    }

    @Override
    public boolean collides(MouseEvent e) {
        // x, y: upper left corner
        final int clickX = e.getX();
        final int clickY = e.getY();

        double dx = Math.abs(clickX - x);
        double dy = Math.abs(clickY - y);
        double distance = Math.sqrt(dx * dx + dy * dy);

        if(fill) {
            return distance <= radius;
        } else {
            //return (int)distance == radius;
            return distance <= radius;
        }
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "fill=" + fill +
                ", fillColor=" + fillColor +
                ", lineColor=" + lineColor +
                ", radius=" + radius +
                '}';
    }
}
