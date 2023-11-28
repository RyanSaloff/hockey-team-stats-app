package ui;

import model.Skater;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Represents a tab that contains a team's skater panel. The skater tab has one TableModel that is added to the centre
// of the screen.
public class SkaterTab extends JPanel {
    private Team team;
    private List<Skater> skaterList;
    private SkaterTableModel skaterTableModel;

    // EFFECTS: adds the button panel and table to the tab. passes skaterList to the TableModel as the data
    public SkaterTab(Team team) {
        this.team = team;
        this.skaterList = team.getSkaterList();
        this.skaterTableModel = new SkaterTableModel(skaterList);

        setLayout(new BorderLayout());
        JTable skaterTable = new JTable(skaterTableModel);
        JScrollPane scrollPane = new JScrollPane(skaterTable);
        add(scrollPane, BorderLayout.CENTER);

        addButtonPanel();
    }

    // MODIFIES: buttonPanel
    // EFFECTS: creates a buttonPanel with 4 rows and 1 column. 4 buttons are added including an image of a skater,
    //          a button to add a skater, a button to remove a skater, and a button to update an existing skater's info
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.setPreferredSize(new Dimension(130,30));

        buttonPanel.add(skaterImage());
        buttonPanel.add(addButton());
        buttonPanel.add(removeButton());
        buttonPanel.add(updateButton());
        add(buttonPanel, BorderLayout.WEST);
        setVisible(true);
    }

    // MODIFIES: this, updateButton
    // EFFECTS: creates updateButton which updates a skater's information if a matching jersey number is found
    //          and returns updateButton. else creates and returns updateButton
    private JButton updateButton() {
        JButton updateButton = new JButton();
        updateButton.setLabel("Update Skater");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jerseyNumber = Integer.parseInt(JOptionPane.showInputDialog("\n Enter the jersey number of the"
                        + " skater that you will update: "));
                updateSkaterHelper(jerseyNumber);
            }
        });
        return updateButton;
    }

    // MODIFIES: this
    // EFFECTS: checks the list of skaters for a matching jerseyNumber. if there is a matching jerseyNumber, prompts
    //          the user to update the skater's information in the list. else does nothing
    private void updateSkaterHelper(int jerseyNumber) {
        for (Skater nextSkater : skaterList) {
            if (nextSkater.getNumber() == jerseyNumber) {
                String name = JOptionPane.showInputDialog("Enter skater's name:");
                int number = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's number:"));
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's age:"));
                String position = JOptionPane.showInputDialog("Enter skater's position:");
                nextSkater.setName(name);
                nextSkater.setNumber(number);
                nextSkater.setAge(age);
                nextSkater.setPosition(position);
                skaterTableModel.updateSkater();
                JOptionPane.showMessageDialog(null, name + " has been updated!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Skater with jersey number"
                + " " + jerseyNumber + " not found.");
    }

    // MODIFIES: skaterButton
    // EFFECTS: creates skaterButton which displays a picture of a hockey skater
    private JButton skaterImage() {
        ImageIcon image = new ImageIcon("images/skater.png");
        JButton skaterButton = new JButton(image);
        return skaterButton;
    }

    // MODIFIES: this, addButton
    // EFFECTS: creates addButton which will add a skater to the team when clicked
    private JButton addButton() {
        JButton addButton = new JButton();
        addButton.setLabel("Add Skater");
        addButton.setSize(50,30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSkater();
            }
        });
        return addButton;
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for the name, number, age, and position of a skater. adds the skater if there is no
    //          skater with a matching number and returns true. else does nothing and returns false
    private boolean addSkater() {
        String name = JOptionPane.showInputDialog("Enter skater's name:");
        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's number:"));
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's age:"));
        String position = JOptionPane.showInputDialog("Enter skater's position:");
        Skater skater = new Skater();
        skater.setName(name);
        skater.setNumber(number);
        skater.setAge(age);
        skater.setPosition(position);

        if (team.addSkater(skater)) {
            JOptionPane.showMessageDialog(null, "\n" + skater.getName()
                    + " has been successfully added!");
            skaterTableModel.updateSkater();
            return true;
        }
        JOptionPane.showMessageDialog(null, "Player has already been added."
                + " Try updating the player instead.");
        return false;
    }

    // MODIFIES: this
    // EFFECTS: adds the skaters from the loaded file into the table
    public void addSkaters(List<Skater> skaters) {
        for (Skater nextSkater : skaters) {
            skaterList.add(nextSkater);
            skaterTableModel.updateSkater();
        }
    }

    // MODIFIES: this, removeButton
    // EFFECTS: creates removeButton - when clicked, removeButton will give the user the option to remove a skater from
    //          the list
    private JButton removeButton() {
        JButton removeButton = new JButton();
        removeButton.setLabel("Remove Skater");
        removeButton.setSize(50,30);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSkater();
            }
        });
        return removeButton;
    }

    // MODIFIES: this
    // EFFECTS: prompts user for a jersey number. if there is a skater with a matching jersey number,
    // removes that skater from the list and returns true. else does nothing and returns false.
    private boolean removeSkater() {
        int number = Integer.parseInt(JOptionPane.showInputDialog("\n Which skater would you like to remove?"
                + " Please give the skater's jersey number: "));
        if (team.removeSkater(number)) {
            skaterTableModel.updateSkater();
            JOptionPane.showMessageDialog(null, "\n Successfully removed!");
            return true;
        }
        JOptionPane.showMessageDialog(null,"\n Skater with jersey number " + number
                + " not found.");
        return false;
    }
}
