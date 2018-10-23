package de.danielprinz.technikum.wordcounter;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.*;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class TableModel extends AbstractTableModel implements Serializable {

    private Map<String, Integer> occurrences = new HashMap<>();

    @Override
    public int getRowCount() {
        return this.occurrences.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //return this.occurrences.;
        String  key   = new ArrayList<>(this.occurrences.keySet()).get(rowIndex);
        Integer value = this.occurrences.get(key);
        return columnIndex == 0 ? key : value;
    }

    @Override
    public String getColumnName(int column) {
        return column == 0 ? "word" : "occurrences";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    /**
     * Loads the data from the map into the table model
     * @param occurrences Map of the words and their count
     */
    public void loadData(Map<String, Integer> occurrences) {
        this.occurrences = MapUtils.sortByValue(occurrences);
        fireTableDataChanged();
    }





}
