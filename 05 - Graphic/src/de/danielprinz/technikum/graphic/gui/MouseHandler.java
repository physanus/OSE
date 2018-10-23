package de.danielprinz.technikum.graphic.gui;

import de.danielprinz.technikum.graphic.graphic.Figure;

import javax.swing.text.rtf.RTFEditorKit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    private static Figure selectedFigure = null;

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());

        ListIterator<Figure> listIterator = Main.getPanel().getFigures().listIterator(Main.getPanel().getFigures().size());
        while(listIterator.hasPrevious()) {
            Figure figure = listIterator.previous();
            if(figure.collides(e)) {
                selectedFigure = figure;
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        selectedFigure = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedFigure == null) return;
        selectedFigure.setPosition(e.getX(), e.getY());
        Main.getPanel().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
