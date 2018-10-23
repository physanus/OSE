package de.danielprinz.technikum.graphic.gui;

import de.danielprinz.technikum.graphic.graphic.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RectangleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txXPos;
	private JTextField txYPos;
	private JTextField txWidth;
	private JTextField txHeight;
	private JButton btnLineColor;
	private JCheckBox ckbxFill;
	private Main main;
	private JButton btnFillColor;
	private Color btnDefaultColor;

	/**
	 * Create the dialog.
	 */
	public RectangleDialog(Main main) {
		this.main = main;
		int width = 450;
		int height = 300;
		int x = main.getFrame().getX() + main.getFrame().getWidth()
				/ 2 - width / 2;
		int y = main.getFrame().getY()
				+ main.getFrame().getHeight() / 2 - height / 2;
		setBounds(x, y, width, height);
		setTitle("Rectangle");
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

		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(30, 100, 70, 15);
		contentPanel.add(lblWidth);

		txWidth = new JTextField();
		txWidth.setBounds(30, 125, 120, 20);
		contentPanel.add(txWidth);
		txWidth.setColumns(10);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(230, 100, 70, 15);
		contentPanel.add(lblHeight);

		txHeight = new JTextField();
		txHeight.setBounds(230, 125, 120, 20);
		contentPanel.add(txHeight);
		txHeight.setColumns(10);

		JLabel lblLineColor = new JLabel("Line Color");
		lblLineColor.setBounds(30, 170, 80, 20);
		contentPanel.add(lblLineColor);

		btnLineColor = new JButton("");
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLineColor.setBackground(JColorChooser.showDialog(contentPanel,
						"Line Color Selection", btnLineColor.getBackground()));

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
				btnFillColor.setBackground(JColorChooser.showDialog(contentPanel,
						"Fill Color Selection", btnFillColor.getBackground()));

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
		if (checkInput(txXPos) && checkInput(txYPos) && checkInput(txWidth)
				&& checkInput(txHeight)) {
			int x = Integer.parseInt(txXPos.getText());
			int y = Integer.parseInt(txYPos.getText());
			int width = Integer.parseInt(txWidth.getText());
			int height = Integer.parseInt(txHeight.getText());

			Rectangle rectangle = new Rectangle(x, y, width, height, btnLineColor.getBackground(), btnFillColor.getBackground(), ckbxFill.isSelected());
			Main.getPanel().addFigure(rectangle);

			return true;
		}
		return false;
	}
}
