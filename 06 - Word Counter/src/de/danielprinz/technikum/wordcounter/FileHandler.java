package de.danielprinz.technikum.wordcounter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class FileHandler {

    private File file = null;

    /**
     * Opens a file chooser and loads the content of the selected file into the program
     * @throws IOException The file is not accessible
     */
    public void load() throws IOException {
        do {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(file == null ? new File(System.getProperty("user.dir")) : file);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdateien (*.txt)", "txt");
            fc.setFileFilter(filter);
            int val = fc.showOpenDialog(null);
            if(!(val == JFileChooser.APPROVE_OPTION)) return;

            file = fc.getSelectedFile();
            if(!file.exists()) { file = null; continue; }

            break;
        } while(true);

        String fileContent = getFileContent();
        Map<String, Integer> occurrences = WordAnalyser.getOccurrences(fileContent);
        Main.getInstance().getTableModel().loadData(occurrences);

        PreviewHandler.setText(fileContent);
    }

    /**
     * Tries to grab the file content from the loaded resource, throws IllegalStateException if no file was loaded
     * @return The file content as a string
     * @throws IOException The file is not accessible
     */
    public String getFileContent() throws IOException {
        if(file == null)
            throw new IllegalStateException("File has not yet been loaded");

        return new String(Files.readAllBytes(file.toPath()));
    }

}
