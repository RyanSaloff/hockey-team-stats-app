package ui;

import model.Goalie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents a tab that contains a team's goalie panel. The goalie tab has one TableModel that is added to the centre
// of the screen.
public class GoalieTab extends JPanel {
    List<Goalie> goalieList;
    GoalieTableModel goalieTableModel;

    // EFFECTS: adds the button panel and table to the tab. passes goalieList to the TableModel as the data
    public GoalieTab(List<Goalie> goalieList) {
        this.goalieList = goalieList;
        this.goalieTableModel = new GoalieTableModel(goalieList);

        setLayout(new BorderLayout());
        JTable goalieTable = new JTable(goalieTableModel);
        JScrollPane scrollPane = new JScrollPane(goalieTable);
        add(scrollPane, BorderLayout.CENTER);

        addButtonPanel();
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates a buttonPanel with 4 rows and 1 column. 4 buttons are added including an image of a goalie,
    //          a button to add a goalie, a button to remove a skater, and a button to update an existing goalie's info
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

    // MODIFIES: goalieButton
    // EFFECTS: creates goalieButton which displays a picture of a hockey goalie
    private JButton goalieImage() {
        ImageIcon image = new ImageIcon("images/goalie.jpg");
        JButton goalieButton = new JButton(image);
        return goalieButton;
    }

    // MODIFIES: this, addButton
    // EFFECTS: creates addButton which will add a goalie to the team when clicked
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

    // MODIFIES: this
    // EFFECTS: adds the goalie from the loaded file into the table
    public void addGoalies(List<Goalie> goalies) {
        for (Goalie nextGoalie : goalies) {
            goalieList.add(nextGoalie);
            goalieTableModel.updateGoalie();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for the name, number, age, and position of a goalie. adds the goalie if there is no
    //          skater with a matching number, then returns true. else does nothing and returns false
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
        goalieTableModel.updateGoalie();
        return true;
    }

    // MODIFIES: this, removeButton
    // EFFECTS: creates removeButton - when clicked, removeButton will give the user the option to remove a goalie from
    //          the list
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

    // MODIFIES: this
    // EFFECTS: prompts user for a jersey number. if there is a goalie with a matching jersey number,
    // removes that goalie from the list and returns true. else does nothing and returns false.
    private boolean removeGoalie() {
        int number = Integer.parseInt(JOptionPane.showInputDialog("\n Which goalie would you like to remove?"
                + " Please give the goalie's jersey number: "));
        for (Goalie goalie : goalieList) {
            if (goalie.getNumber() == number) {
                goalieList.remove(goalie);
                goalieTableModel.updateGoalie();
                JOptionPane.showMessageDialog(null, "\n Successfully removed!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"\n Goalie with jersey number " + number
                + " not found.");
        return false;
    }

    // MODIFIES: this, updateButton
    // EFFECTS: creates updateButton which updates a goalie's information if a matching jersey number is found
    //          and returns updateButton. else creates and returns updateButton
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

    // MODIFIES: this
    // EFFECTS: checks the list of goalies for a matching jerseyNumber. if there is a matching jerseyNumber, prompts
    //          the user to update that goalie's information in the list. else does nothing
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
                goalieTableModel.updateGoalie();
                JOptionPane.showMessageDialog(null, name + " has been updated!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Goalie with jersey number"
                + " " + jerseyNumber + " not found.");
    }
}
