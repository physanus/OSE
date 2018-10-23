package de.danielprinz.technikum.addressbook;

import com.sun.crypto.provider.TlsRsaPremasterSecretGenerator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

/**
 * Dialog to enter the address.
 * 
 * @author guenter
 */
@SuppressWarnings("serial")
public class AddressDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txFirstName;
	private JTextField txLastName;
	private JTextField txAddress;
	private JFormattedTextField txPhone;
	private JFormattedTextField txBirthDate;


	/**
	 * Create the dialog.
	 *
	 * @param instance
	 *            address book
	 * @param selectedRow
	 */
	public AddressDialog(final Main instance, int selectedRow, AddressEntry prefill) {
		super(instance.getFrame());
		run(instance, selectedRow, prefill);
	}


	/**
	 * Create the dialog.
	 *
	 * @param instance
	 *            address book
	 */
	public AddressDialog(final Main instance) {
		super(instance.getFrame());
		run(instance, -1, null);
	}



	private void run(Main instance, int selectedRow, AddressEntry prefill) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(instance.getFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(30, 25, 120, 14);
		contentPanel.add(lblFirstName);

		txFirstName = new JTextField();
		txFirstName.setBounds(30, 50, 120, 20);
		contentPanel.add(txFirstName);
		txFirstName.setColumns(10);

		JLabel lbLastName = new JLabel("Last Name");
		lbLastName.setBounds(180, 25, 120, 14);
		contentPanel.add(lbLastName);

		txLastName = new JTextField();
		txLastName.setBounds(180, 50, 120, 20);
		contentPanel.add(txLastName);
		txLastName.setColumns(10);

		JLabel lblBirthday = new JLabel("Birth Date");
		lblBirthday.setBounds(330, 25, 80, 14);
		contentPanel.add(lblBirthday);

		DateFormat dateFmt = new SimpleDateFormat("dd.MM.yyyy");
		txBirthDate = new JFormattedTextField(dateFmt);
		txBirthDate.setBounds(330, 50, 80, 20);
		contentPanel.add(txBirthDate);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(30, 75, 380, 14);
		contentPanel.add(lblAddress);

		txAddress = new JTextField();
		txAddress.setBounds(30, 100, 380, 20);
		contentPanel.add(txAddress);
		txAddress.setColumns(10);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(30, 125, 150, 14);
		contentPanel.add(lblPhone);

		MaskFormatter fmaskFmt = null;
		try {
			fmaskFmt = new MaskFormatter("+43 ### ### ## ##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txPhone = new JFormattedTextField(fmaskFmt);
		txPhone.setBounds(30, 150, 150, 20);
		contentPanel.add(txPhone);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> {

					try {

						if(prefill == null) {
							// save
							Main.getInstance().getAddressTableModel().addEntry(new AddressEntry(txFirstName.getText(), txLastName.getText(), txAddress.getText(), txPhone.getText(), txBirthDate.getText()));
						} else {
							// update
							Main.getInstance().getAddressTableModel().updateEntry(selectedRow, new AddressEntry(txFirstName.getText(), txLastName.getText(), txAddress.getText(), txPhone.getText(), txBirthDate.getText()));
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}


					setVisible(false);
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

		if(prefill != null) {
			txFirstName.setText(prefill.getFirstName());
			txLastName.setText(prefill.getLastName());
			txAddress.setText(prefill.getAddress());
			txPhone.setText(prefill.getPhone());
			txBirthDate.setText(prefill.getBirthDateRaw());
		}

	}


}
