package ui;

import model.Goalie;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GoalieTableModel extends AbstractTableModel {
    private List<Goalie> goalieList;
    private String[] columnNames = {"Name", "Jersey Number", "Age", "Position"};

    public GoalieTableModel(List<Goalie> goalieList) {
        this.goalieList = goalieList;
    }

    @Override
    public int getRowCount() {
        return goalieList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Goalie goalie = goalieList.get(rowIndex);
        if (columnIndex == 0) {
            return goalie.getName();
        } else if (columnIndex == 1) {
            return goalie.getNumber();
        } else if (columnIndex == 2) {
            return goalie.getAge();
        } else if (columnIndex == 3) {
            return goalie.getPosition();
        } else {
            return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}