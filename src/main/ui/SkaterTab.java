package ui;

import model.Skater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SkaterTab extends JPanel {
    List<Skater> skaterList;
    SkaterTableModel skaterTableModel;
    String teamName;

    public SkaterTab(List<Skater> skaterList, String teamName) {
        this.skaterList = skaterList;
        this.skaterTableModel = new SkaterTableModel(skaterList);
        this.teamName = teamName;

        setLayout(new BorderLayout());
        JTable skaterTable = new JTable(skaterTableModel);
        JScrollPane scrollPane = new JScrollPane(skaterTable);
        add(scrollPane, BorderLayout.CENTER);

        addButtonPanel();
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,1));
        buttonPanel.setPreferredSize(new Dimension(120,30));

        buttonPanel.add(skaterImage());
        buttonPanel.add(addButton());
        buttonPanel.add(removeButton());
        add(buttonPanel, BorderLayout.WEST);
        setVisible(true);
    }

    private JButton skaterImage() {
        ImageIcon image = new ImageIcon("images/skater.png");
        JButton skaterButton = new JButton(image);
        return skaterButton;
    }

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

    public void addSkaters(List<Skater> skaters) {
        for (Skater nextSkater : skaters) {
            skaterList.add(nextSkater);
            skaterTableModel.addSkater();
        }
    }

    private boolean addSkater() {
        String name = JOptionPane.showInputDialog("Enter skater's name:");
        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's number:"));
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter skater's age:"));
        String position = JOptionPane.showInputDialog("Enter skater's Position:");
        Skater skater = new Skater();
        skater.setName(name);
        skater.setNumber(number);
        skater.setAge(age);
        skater.setPosition(position);

        for (Skater nextSkater : skaterList) {
            if (nextSkater == skater || nextSkater.getNumber() == skater.getNumber()) {
                JOptionPane.showMessageDialog(null, "Player has already been added."
                        + " Try updating the player instead.");
                return false;
            }
        }
        skaterList.add(skater);
        JOptionPane.showMessageDialog(null, "\n" + skater.getName()
                + " has been successfully added!");
        skaterTableModel.addSkater();
        return true;
    }

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

    private boolean removeSkater() {
        int number = Integer.parseInt(JOptionPane.showInputDialog("\n Which skater would you like to remove?"
                + " Please give the skater's jersey number: "));
        for (Skater skater : skaterList) {
            if (skater.getNumber() == number) {
                skaterList.remove(skater);
                skaterTableModel.removeSkater();
                JOptionPane.showMessageDialog(null, "\n Successfully removed!");
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"\n Skater with number " + number + " not found.");
        return false;
    }

    public SkaterTableModel getSkaterTable() {
        return skaterTableModel;
    }

    public void setTable(SkaterTableModel model) {
        this.skaterTableModel = model;
    }


}
