package ui;

import model.Skater;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Represents a Table Model that creates a table with skaterList as the data for the table and uses columnNames for the
// column names.
public class SkaterTableModel extends AbstractTableModel {
    private List<Skater> skaterList;
    private String[] columnNames = {"Name", "Jersey Number", "Age", "Position"};

    // EFFECTS: sets the data as the skaterList
    public SkaterTableModel(List<Skater> skaterList) {
        this.skaterList = skaterList;
    }

    @Override
    public int getRowCount() {
        return skaterList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Skater skater = skaterList.get(rowIndex);
        if (columnIndex == 0) {
            return skater.getName();
        } else if (columnIndex == 1) {
            return skater.getNumber();
        } else if (columnIndex == 2) {
            return skater.getAge();
        } else if (columnIndex == 3) {
            return skater.getPosition();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // MODIFIES: this
    // EFFECTS: updates the cell values in each row
    public void updateSkater() {
        fireTableDataChanged();
    }
}
