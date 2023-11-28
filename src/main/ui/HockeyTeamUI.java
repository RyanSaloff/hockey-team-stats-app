package ui;

import model.Event;
import model.EventLog;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



// I used the AccountNotRobust project to help writing this class (mainly with runHockeyTeam)
// Phase 3 Update: I used the AlarmSystem project to help with writing the GUI

// Hockey team roster application
public class HockeyTeamUI extends JFrame implements LogPrinter {
    private static final String JSON_STORE = "./data/team.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Team team;       // reference to a team
    private String teamName;
    private Scanner input;   // stores user input
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GoalieTab goalieTab;
    private SkaterTab skaterTab;

    // EFFECTS: runs the hockey team roster application and adds the
    public HockeyTeamUI() {
        initializeTeam();
        teamName = team.getName();
        setTitle("Hockey Team Roster Program: " + teamName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                printLog(EventLog.getInstance());
            }
        });

        this.goalieTab = new GoalieTab(team);
        this.skaterTab = new SkaterTab(team);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Goalies", goalieTab);
        tabs.add("Skaters", skaterTab);
        add(tabs);

        addTeamMenu();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setVisible(true);
    }

    @Override
    public void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            System.out.println(next.getDate());
            System.out.println(next.getDescription());
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds three options to the menu bar: load team, save team, and edit team name
    private void addTeamMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu teamMenu = new JMenu("Team Settings");
        JMenuItem loadItem = new JMenuItem("Load Team");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTeam();
            }
        });
        teamMenu.add(loadItem);
        JMenuItem saveItem = new JMenuItem("Save Team");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeam();
            }
        });
        teamMenu.add(saveItem);
        JMenuItem editNameItem = new JMenuItem("Edit Team Name");
        editNameHelper(editNameItem);  // helper method to edit the team name
        teamMenu.add(editNameItem);
        menuBar.add(teamMenu);
        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: updates team name to user's input
    private void editNameHelper(JMenuItem editNameItem) {
        editNameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedName = JOptionPane.showInputDialog("Enter team's name: ");
                team.setName(updatedName);
                teamName = updatedName;
                setTitle("Hockey Team Roster Program: " + teamName);
                JOptionPane.showMessageDialog(null, "Team Name has been updated to: " + teamName);
                repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes team
    private void initializeTeam() {
        team = new Team("Calgary Flames");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: loads team from file and adds data to each table
    private void loadTeam() {
        try {
            team = jsonReader.read();
            skaterTab.addSkaters(team.getSkaterList());
            goalieTab.addGoalies(team.getGoalieList());
            teamName = team.getName();
            setTitle("Hockey Team Roster Program: " + teamName);
            JOptionPane.showMessageDialog(null,
                    "Loaded " + team.getName() + " from " + JSON_STORE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the team to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Saved " + team.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
