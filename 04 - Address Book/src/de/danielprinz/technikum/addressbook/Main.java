package de.danielprinz.technikum.addressbook;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by el17x002 on 27.09.2018.
 */
public class Main {

    private static Main instance;

    private JFrame frame;
    private JTable table;
    private JToolBar toolBar;
    private JButton btnAdd;
    private JButton btnChange;
    private JButton btnRemove;
    private JButton btnSave;
    private AddressTableModel addressTableModel = new AddressTableModel();

    private static final File FILE = new File("tablemodle.ab");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("test"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write("Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.frame.setVisible(true);

                if(FILE.exists()) {

                    AddressTableModel model = null;

                    try {
                        FileInputStream fileIn = new FileInputStream(FILE);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        model = (AddressTableModel) in.readObject();
                        in.close();
                        fileIn.close();

                        Main.getInstance().addressTableModel.loadFromExistingModel(model);

                    } catch (IOException |ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                } else {
                    Main.getInstance().getAddressTableModel().addEntry(new AddressEntry("Daniel", "Prinz", "Address", "+43 123456789", "13.10.1997"));
                    Main.getInstance().getAddressTableModel().addEntry(new AddressEntry("Max", "Mustermann", "Address2", "+43 987654321", "02.05.2001"));
                    Main.getInstance().getAddressTableModel().addEntry(new AddressEntry("Gottfried", "Mustermann", "Address2", "+43 987654321", "02.05.2001"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        instance = this;
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setModel(addressTableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        scrollPane.setViewportView(table);

        toolBar = new JToolBar();
        frame.getContentPane().add(toolBar, BorderLayout.NORTH);

        btnAdd = new JButton("");
        btnAdd.addActionListener(e -> {
//				table.clearSelection();
            new AddressDialog(instance).setVisible(true);
        });
        //btnAdd.setIcon(new ImageIcon(Main.class.getResource("../../../../add.jpg")));
        btnAdd.setIcon(new ImageIcon(Main.class.getResource("../../../../flat/file.png")));
        toolBar.add(btnAdd);

        btnChange = new JButton("");
        btnChange.addActionListener(e -> {
            if(table.getSelectedRow() == -1) return;
            AddressEntry addressEntry = addressTableModel.getAddressEntry(table.getSelectedRow());
            new AddressDialog(instance, table.getSelectedRow(), addressEntry).setVisible(true);
        });
        //btnChange.setIcon(new ImageIcon(Main.class.getResource("../../../../change.jpg")));
        btnChange.setIcon(new ImageIcon(Main.class.getResource("../../../../flat/edit.png")));
        toolBar.add(btnChange);

        btnRemove = new JButton("");
        btnRemove.addActionListener(e -> {
            if(table.getSelectedRow() == -1) return;
            addressTableModel.removeEntry(table.getSelectedRow());
        });
        //btnRemove.setIcon(new ImageIcon(Main.class.getResource("../../../../remove.jpg")));
        btnRemove.setIcon(new ImageIcon(Main.class.getResource("../../../../flat/delete.png")));
        toolBar.add(btnRemove);

        btnSave = new JButton("");
        btnSave.addActionListener(e -> {
            Main.getInstance().getAddressTableModel().saveTableModel(FILE);
            System.out.println("Serialized data is saved in " + FILE.getAbsolutePath());
        });
        btnSave.setIcon(new ImageIcon(Main.class.getResource("../../../../flat/save.png")));
        toolBar.add(btnSave);
    }


    public JFrame getFrame() {
        return frame;
    }


    public static Main getInstance() {
        return instance;
    }

    public AddressTableModel getAddressTableModel() {
        return addressTableModel;
    }
}
