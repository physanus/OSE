package de.danielprinz.technikum.graphic.gui;

import de.danielprinz.technikum.graphic.graphic.Figure;
import de.danielprinz.technikum.graphic.graphic.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GraphicPanel extends JPanel {

	private List<Figure> figures = new ArrayList<>();
	private Color defaultColor = Color.BLACK;

	/**
	 * Create a graphic panel, add the mouse listener.
	 */
	public GraphicPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintChildren(java.awt.Graphics)
	 */
	@Override
	protected void paintChildren(Graphics g) {
		for(Figure figure : figures) {
			figure.draw(g);
		}
	}

	/**
	 * @return the defaultColor
	 */
	public Color getDefaultColor() {
		return defaultColor;
	}

	/**
	 * @param defaultColor the defaultColor to set
	 */
	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}


	public void loadFile(File file) throws IOException {
		Image image = new Image(file, getWidth(), getHeight());
		addFigure(image);

		if(Main.DEBUG) System.out.println("Loaded " + file.getAbsolutePath());
	}

	public void addFigure(Figure figure) {
		this.figures.add(figure);
		repaint();
	}

	public List<Figure> getFigures() {
		return figures;
	}
}
