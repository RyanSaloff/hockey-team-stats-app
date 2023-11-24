package ui;

import model.Goalie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GoalieTab extends JPanel {
    public GoalieTab(List<Goalie> goalieList, String teamName) {
        setLayout(new BorderLayout());
        GoalieTableModel goalieTableModel = new GoalieTableModel(goalieList);
        JTable goalieTable = new JTable(goalieTableModel);
        JScrollPane scrollPane = new JScrollPane(goalieTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
