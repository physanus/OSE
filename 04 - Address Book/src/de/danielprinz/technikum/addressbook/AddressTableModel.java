package de.danielprinz.technikum.addressbook;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;

public class AddressTableModel extends AbstractTableModel implements Serializable {
	
	private static final long serialVersionUID = 8905550538968768899L;
	private static final String[] titles = new String[] { "First Name", "Last Name", "Address", "Phone", "Birth Date" };
	private ArrayList<AddressEntry> entries = new ArrayList<>();

	@Override
	public int getRowCount() {
		return entries.size();
	}

	@Override
	public int getColumnCount() {
		return titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		AddressEntry addressEntry = entries.get(rowIndex);
		if(columnIndex == 0)
			return addressEntry.getFirstName();
		else if(columnIndex == 1)
			return addressEntry.getLastName();
		else if(columnIndex == 2)
			return addressEntry.getAddress();
		else if(columnIndex == 3)
			return addressEntry.getPhone();
		else if(columnIndex == 4)
			return addressEntry.getBirthDate();
		else
			return null;
	}

	@Override
	public String getColumnName(int column) {
		return titles[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	

	public void addEntry(AddressEntry addressEntry) {
		entries.add(addressEntry);
		fireTableDataChanged();
	}

	public void updateEntry(int selectedRow, AddressEntry addressEntry) {
		this.entries.set(selectedRow, addressEntry);
		fireTableDataChanged();
	}

	public void removeEntry(int seelctedRow) {
		this.entries.remove(seelctedRow);
		fireTableDataChanged();
	}


	public AddressEntry getAddressEntry(int row) {
		return this.entries.get(row);
	}


	public void saveTableModel(File file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void loadFromExistingModel(AddressTableModel addressTableModel) {

		this.entries = addressTableModel.entries;

	}
}
