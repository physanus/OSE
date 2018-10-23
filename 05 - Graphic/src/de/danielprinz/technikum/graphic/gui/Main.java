package de.danielprinz.technikum.graphic.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

/**
 * Main class to start the application.
 * 
 * @author guenter
 */
public class Main {

	private JFrame frame;
	private static GraphicPanel panel;

	public static final boolean DEBUG = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                Main window = new Main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the application.
	 */
	private Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final Main main = this;
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Load...");
		mntmLoad.addActionListener(e -> load());
		mnFile.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save...");
		mntmSave.addActionListener(e -> save());
		mnFile.add(mntmSave);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(frame,
                    "Do you want to quit the application?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnNew = new JMenu("New...");
		mnEdit.add(mnNew);

		JMenuItem mntmCircle = new JMenuItem("Circle");
		mntmCircle.addActionListener(e -> new CircleDialog(main).setVisible(true));
		mnNew.add(mntmCircle);

		JMenuItem mntmRectangle = new JMenuItem("Rectangle");
		mntmRectangle.addActionListener(e -> new RectangleDialog(main).setVisible(true));
		mnNew.add(mntmRectangle);

		JMenuItem mntmColor = new JMenuItem("Color...");
		mntmColor.addActionListener(e -> panel.setDefaultColor(JColorChooser.showDialog(frame, "Color Selection", panel.getDefaultColor())));
		mnEdit.add(mntmColor);

		panel = new GraphicPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);


		if(DEBUG) {
			new Thread(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) { e.printStackTrace(); }

				try {
					panel.loadFile(new File("urlaub-in-de-bergen.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}).start();
		}
	}

	/**
	 * Return the main frame.
	 * 
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Return the graphic panel
	 * 
	 * @return graphic panel
	 */
	public static GraphicPanel getPanel() {
		return panel;
	}

	/**
	 * Opens a file chooser and loads the graphic data.
	 */
	private void load() {
		File file;
		do {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG (*.png)", "png");
			fc.setFileFilter(filter);
			int val = fc.showOpenDialog(null);
			if(!(val == JFileChooser.APPROVE_OPTION)) return;

			file = fc.getSelectedFile();
			if(!file.exists()) { continue; }

			break;
		} while(true);

		try {
			panel.loadFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a file chooser and saves the graphic data.
	 */
	private void save() {

	}
}
