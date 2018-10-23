package de.danielprinz.technikum.wordcounter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class Main {

    private static Main instance;
    private static JFrame jFrame;
    public static JEditorPane jEditorPane;

    private FileHandler fileHandler;
    private TableModel tableModel;

    public static final boolean DEBUG = true;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Main();
                jFrame.setTitle("Word Counter");
                jFrame.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/documents-button.png")));
                jFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Initializes the GUI
     */
    public Main() {

        instance = this;
        fileHandler = new FileHandler();
        tableModel = new TableModel();

        jFrame = new JFrame();
        jFrame.setBounds(100, 100, 800, 600);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        jFrame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmLoad = new JMenuItem("Open...");
        mntmLoad.addActionListener(e -> {
            try {
                fileHandler.load();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog( null, "The specified file could not be read", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        mnFile.add(mntmLoad);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(jFrame,
                    "Do you want to quit the application?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
        mnFile.add(mntmExit);



        GridLayout gridLayout = new GridLayout(0, 2);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(gridLayout);

        JTable jtable = new JTable();
        jtable.setModel(tableModel);
        jtable.getColumnModel().getColumn(0).setPreferredWidth(100);
        jtable.getColumnModel().getColumn(1).setPreferredWidth(100);
        JScrollPane scrollPaneTable = new JScrollPane();
        scrollPaneTable.setViewportView(jtable);
        jPanel.add(scrollPaneTable);
        jtable.getSelectionModel().addListSelectionListener(e -> {

            ArrayList<String> strings = new ArrayList<>();
            for(int row : jtable.getSelectedRows()) {
                strings.add((String) jtable.getValueAt(row, 0));
            }

            PreviewHandler.selectText(strings.toArray(new String[0]));
        });

        jEditorPane = new JEditorPane("text/html", "");
        JScrollPane scrollPaneTextArea = new JScrollPane();
        scrollPaneTextArea.setViewportView(jEditorPane);
        jPanel.add(scrollPaneTextArea);

        jFrame.getContentPane().add(jPanel, BorderLayout.CENTER);

    }


    public static Main getInstance() {
        return instance;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
