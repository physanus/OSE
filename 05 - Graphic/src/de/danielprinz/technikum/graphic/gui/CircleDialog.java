package de.danielprinz.technikum.graphic.gui;

import de.danielprinz.technikum.graphic.graphic.Circle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CircleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txXPos;
	private JTextField txYPos;
	private JTextField txRadius;
	private JButton btnLineColor;
	private JCheckBox ckbxFill;
	private Main main;
	private JButton btnFillColor;
	private Color btnDefaultColor;

	/**
	 * Create the dialog.
	 */
	public CircleDialog(Main main) {
		this.main = main;
		int width = 450;
		int height = 300;
		int x = main.getFrame().getX() + main.getFrame().getWidth()
				/ 2 - width / 2;
		int y = main.getFrame().getY()
				+ main.getFrame().getHeight() / 2 - height / 2;
		setBounds(x, y, width, height);
		setTitle("Circle");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblXpos = new JLabel("X");
		lblXpos.setBounds(30, 35, 70, 15);
		contentPanel.add(lblXpos);

		txXPos = new JTextField();
		txXPos.setBounds(30, 60, 120, 20);
		contentPanel.add(txXPos);
		txXPos.setColumns(10);

		JLabel lblYpos = new JLabel("Y");
		lblYpos.setBounds(230, 35, 70, 15);
		contentPanel.add(lblYpos);

		txYPos = new JTextField();
		txYPos.setBounds(230, 60, 120, 20);
		contentPanel.add(txYPos);
		txYPos.setColumns(10);

		JLabel lblWidth = new JLabel("Radius");
		lblWidth.setBounds(30, 100, 70, 15);
		contentPanel.add(lblWidth);

		txRadius = new JTextField();
		txRadius.setBounds(30, 125, 120, 20);
		contentPanel.add(txRadius);
		txRadius.setColumns(10);

		JLabel lblLineColor = new JLabel("Line Color");
		lblLineColor.setBounds(30, 170, 80, 20);
		contentPanel.add(lblLineColor);

		btnLineColor = new JButton("");
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLineColor.setBackground(JColorChooser.showDialog(
						contentPanel, "Line Color Selection",
						btnLineColor.getBackground()));

			}
		});
		btnDefaultColor = btnLineColor.getBackground();

		btnLineColor.setBounds(120, 170, 30, 20);
		Color color = main.getPanel().getDefaultColor();
		btnLineColor.setBackground(color);
		contentPanel.add(btnLineColor);

		JLabel lblFillColor = new JLabel("Fill Color");
		lblFillColor.setBounds(30, 200, 70, 20);
		contentPanel.add(lblFillColor);

		btnFillColor = new JButton("");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFillColor.setBackground(JColorChooser.showDialog(
						contentPanel, "Fill Color Selection",
						btnFillColor.getBackground()));

			}
		});
		btnFillColor.setBounds(120, 200, 30, 20);

		ckbxFill = new JCheckBox("Fill");
		ckbxFill.addActionListener(e -> {
            if (!ckbxFill.isSelected()) {
                btnFillColor.setBackground(btnDefaultColor);

            }
        });
		ckbxFill.setBounds(228, 170, 130, 20);
		contentPanel.add(ckbxFill);

		contentPanel.add(btnFillColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> {
                    if (addFigure()) {
                        setVisible(false);
                    }
                });
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> setVisible(false));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Check the input fields.
	 * 
	 * @param txField
	 * @return true if input is correct.
	 */
	private boolean checkInput(JTextField txField) {
		String text = txField.getText();
		try {
			int value = Integer.parseInt(text);
			if (value >= 0) {
				return true;
			} else {
				JOptionPane.showMessageDialog(txField,
						"Invalid value, only numbers >= 0 are allowed");
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(txField,
					"Invalid value, only numbers >= 0 are allowed");
			return false;
		}
	}

	/**
	 * Add figure to the graphic panel.
	 * @return true if action succeeds
	 */
	private boolean addFigure() {
		if (checkInput(txXPos) && checkInput(txYPos) && checkInput(txRadius)) {
			int x = Integer.parseInt(txXPos.getText());
			int y = Integer.parseInt(txYPos.getText());
			int radius = Integer.parseInt(txRadius.getText());

			Circle circle = new Circle(x, y, radius, btnLineColor.getBackground(), btnFillColor.getBackground(), ckbxFill.isSelected());
			Main.getPanel().addFigure(circle);

			return true;
		}
		return false;
	}
}
