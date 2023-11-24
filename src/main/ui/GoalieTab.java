package ui;

import model.Goalie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GoalieTab extends JPanel {
    List<Goalie> goalieList;
    GoalieTableModel goalieTableModel;
    String teamName;

    public GoalieTab(List<Goalie> goalieList, String teamName) {
        this.goalieList = goalieList;
        this.goalieTableModel = new GoalieTableModel(goalieList);
        this.teamName = teamName;

        setLayout(new BorderLayout());
        JTable goalieTable = new JTable(goalieTableModel);
        JScrollPane scrollPane = new JScrollPane(goalieTable);
        add(scrollPane, BorderLayout.CENTER);

        addButtonPanel();
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.setPreferredSize(new Dimension(130,30));

        buttonPanel.add(goalieImage());
        buttonPanel.add(addButton());
        buttonPanel.add(removeButton());
        buttonPanel.add(updateButton());
        add(buttonPanel, BorderLayout.WEST);
        setVisible(true);
    }

    private JButton goalieImage() {
        ImageIcon image = new ImageIcon("images/goalie.jpg");
        JButton goalieButton = new JButton(image);
        return goalieButton;
    }

    private JButton addButton() {
        JButton addButton = new JButton();
        addButton.setLabel("Add Goalie");
        addButton.setSize(50,30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGoalie();
            }
        });
        return addButton;
    }

    public void addGoalies(List<Goalie> goalies) {
        for (Goalie nextGoalie : goalies) {
            goalieList.add(nextGoalie);
            goalieTableModel.addGoalie();
        }
    }

    private boolean addGoalie() {
        String name = JOptionPane.showInputDialog("Enter goalie's name:");
        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter goalie's number:"));
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter goalie's age:"));
        String position = JOptionPane.showInputDialog("Enter goalie's position:");
        Goalie goalie = new Goalie();
        goalie.setName(name);
        goalie.setNumber(number);
        goalie.setAge(age);
        goalie.setPosition(position);

        for (Goalie nextGoalie : goalieList) {
            if (nextGoalie == goalie || nextGoalie.getNumber() == goalie.getNumber()) {
                JOptionPane.showMessageDialog(null, "Player has already been added."
                        + " Try updating the player instead.");
                return false;
            }
        }
        goalieList.add(goalie);
        JOptionPane.showMessageDialog(null, "\n" + goalie.getName()
                + " has been successfully added!");
        goalieTableModel.addGoalie();
        return true;
    }

    private JButton removeButton() {
        JButton removeButton = new JButton();
        removeButton.setLabel("Remove Goalie");
        removeButton.setSize(50,30);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeGoalie();
            }
        });
        return removeButton;
    }

    private boolean removeGoalie() {
        int number = Integer.parseInt(JOptionPane.showInputDialog("\n Which goalie would you like to remove?"
                + " Please give the goalie's jersey number: "));
        for (Goalie goalie : goalieList) {
            if (goalie.getNumber() == number) {
                goalieList.remove(goalie);
                goalieTableModel.removeGoalie();
                JOptionPane.showMessageDialog(null, "\n Successfully removed!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"\n Goalie with jersey number " + number
                + " not found.");
        return false;
    }

    private JButton updateButton() {
        JButton updateButton = new JButton();
        updateButton.setLabel("Update Goalie");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jerseyNumber = Integer.parseInt(JOptionPane.showInputDialog("\n Enter the jersey number of the"
                        + " goalie that you will update: "));
                updateGoalieHelper(jerseyNumber);
            }
        });
        return updateButton;
    }

    private void updateGoalieHelper(int jerseyNumber) {
        for (Goalie nextGoalie : goalieList) {
            if (nextGoalie.getNumber() == jerseyNumber) {
                String name = JOptionPane.showInputDialog("Enter goalie's name:");
                int number = Integer.parseInt(JOptionPane.showInputDialog("Enter goalie's number:"));
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter goalie's age:"));
                String position = JOptionPane.showInputDialog("Enter goalie's position:");
                nextGoalie.setName(name);
                nextGoalie.setNumber(number);
                nextGoalie.setAge(age);
                nextGoalie.setPosition(position);
                goalieTableModel.addGoalie();
                JOptionPane.showMessageDialog(null, name + " has been updated!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Goalie with jersey number"
                + " " + jerseyNumber + " not found.");
    }

    public void setTable(GoalieTableModel model) {
        this.goalieTableModel = model;
    }
}
