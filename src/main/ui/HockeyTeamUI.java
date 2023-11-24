package ui;

import model.Goalie;
import model.Skater;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.JTableHeader;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;



// I used the AccountNotRobust project to help writing this class (mainly with runHockeyTeam)

// Hockey team roster application
public class HockeyTeamUI extends JFrame {
    private static final String JSON_STORE = "./data/team.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private Team team;       // reference to a team
    private String teamName;
    private Scanner input;   // stores user input
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTabbedPane tabs;
    private GoalieTab goalieTab;
    private SkaterTab skaterTab;

    // EFFECTS: runs the hockey team roster application
    public HockeyTeamUI() {
        initializeTeam();
        teamName = team.getName();
        setTitle("Hockey Team Roster Program: " + teamName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        this.goalieTab = new GoalieTab(team.getGoalieList(), team.getName());
        this.skaterTab = new SkaterTab(team.getSkaterList(), team.getName());

        tabs = new JTabbedPane();
        tabs.add("Goalies", goalieTab);
        tabs.add("Skaters", skaterTab);
        add(tabs);

        addLabel();
        addTeamMenu();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setVisible(true);
    }

    private void addLabel() {
        JLabel teamNameLabel = new JLabel(teamName);
        teamNameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Customize font if needed
        teamNameLabel.setHorizontalAlignment(JLabel.CENTER);
    }

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
    // EFFECTS: processes the user's input
    private void runHockeyTeam() {
        boolean notQ = true;
        String command = null;
        initializeTeam();
        while (notQ) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                notQ = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Application closed.");
    }

    // MODIFIES: this
    // EFFECTS: initializes team
    private void initializeTeam() {
        team = new Team("Calgary Flames");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays options for the user
    private void displayOptions() {
        System.out.println("\n" + team.getName());
        System.out.println("\tl -> load team from file");
        System.out.println("\ts -> save current team to file");
        System.out.println("\tc -> change team name");
        System.out.println("\tv -> view the list of players");
        System.out.println("\tn -> show the number of players");
        System.out.println("\ta -> add a player");
        System.out.println("\tr -> remove a player");
        System.out.println("\tu -> update a player's information");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "l": loadTeam();
            break;
            case "s": saveTeam();
                break;
            case "c": doChangeName();
                break;
            case "v": doViewPlayers();
                break;
            case "n": doPlayerCount();
                break;
            case "a": doAddPlayer();
                break;
            case "r": doRemovePlayer();
                break;
            case "u": doUpdatePlayer();
                break;
            default: System.out.println("Invalid input");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team from file
    private void loadTeam() {
        try {
            team = jsonReader.read();
            skaterTab.addSkaters(team.getSkaterList());
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

    // MODIFIES: this
    // EFFECTS: prompts user to choose to update a goalie or a skater's information
    private void doUpdatePlayer() {
        System.out.println("\n Modify a goalie or a skater?");
        String choice = choosePlayerType();
        if (choice.equals("g")) {
            doUpdateGoalie();
        } else {
            doUpdateSkater();
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the goalie and adds them to the end of the list, else returns an error message
    private void doUpdateGoalie() {
        doViewGoalies();
        System.out.println("\n Enter the jersey number of the goalie that you will update: ");
        int number = input.nextInt();
        if (team.removeGoalie(number)) {
            doAddGoalie();
        } else {
            System.out.println("Unable to find a goalie with that jersey number.");
        }

    }

    // MODIFIES: this
    // EFFECTS: updates the skater and adds them to the end of the list, else returns an error message
    private void doUpdateSkater() {
        doViewSkaters();
        System.out.println("\n Enter the jersey number of the skater that you will update: ");
        int number = input.nextInt();
        if (team.removeSkater(number)) {
            doAddSkater();
        } else {
            System.out.println("Unable to find a skater with that jersey number.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose to remove either a goalie or a skater
    private void doRemovePlayer() {
        System.out.println("\n Remove a goalie or a skater?");
        String choice = choosePlayerType();
        if (choice.equals("g")) {
            doRemoveGoalie();
        } else {
            doRemoveSkater();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for the goalie's jersey number. if a goalie with that jersey number if found, the goalie is
    //          removed from the team. else, the team is unchanged
    private void doRemoveGoalie() {
        doViewGoalies();
        System.out.println("\n Which goalie would you like to remove? Please give the goalie's jersey number: ");
        int number = input.nextInt();
        if (team.removeGoalie(number)) {
            System.out.println("\n Successfully removed!");
        } else {
            System.out.println("\n Goalie with number " + number + " not found.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for the skater's jersey number. if a skater with that jersey number if found, the skater is
    //          removed from the team. else, the team is unchanged
    private void doRemoveSkater() {
        doViewSkaters();
        System.out.println("\n Which skater would you like to remove? Please give the skater's jersey number: ");
        int number = input.nextInt();
        if (team.removeSkater(number)) {
            System.out.println("\n Successfully removed!");
        } else {
            System.out.println("\n Skater with number " + number + " not found.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose to add a skater or goalie
    private void doAddPlayer() {
        System.out.println("\n Add new goalie or skater?");
        String choice = choosePlayerType();
        if (choice.equals("g")) {
            doAddGoalie();
        } else {
            doAddSkater();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for the goalie's information. if there is no duplicate, adds the goalie to the team.
    //          else, prints error message
    private void doAddGoalie() {
        System.out.print("\n First and last name: ");
        String name = input.next(); // User inputs goalie's name
        System.out.print("\n Jersey number (1-99): ");
        int number = input.nextInt(); // User inputs goalie's number
        System.out.print("\n Age: ");
        int age = input.nextInt(); // User inputs goalie's age
        Goalie goalie = new Goalie();
        goalie.setName(name);      // Sets the goalie's information
        goalie.setNumber(number);
        goalie.setAge(age);
        goalie.setPosition("G");

        if (!(team.addGoalie(goalie))) {
            printDuplicate();
        }
        System.out.println("\n" + name + " has been successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for the skater's information. if there is no duplicate, adds the skater to the team.
    //          else, prints error message
    private void doAddSkater() {
        System.out.print("\n First and last name: ");
        String name = input.next(); // User inputs skater's name
        System.out.print("\n Jersey number (1-99): ");
        int number = input.nextInt(); // User inputs skater's number
        System.out.print("\n Age: ");
        int age = input.nextInt(); // User inputs skater's age
        System.out.print("\n Position (RW, LW, C, D): ");
        String position = input.next();
        Skater skater = new Skater();
        skater.setName(name);      // Sets the skater's information
        skater.setNumber(number);
        skater.setAge(age);
        skater.setPosition(position);

        if (!(team.addSkater(skater))) {
            printDuplicate();
        } else {
            System.out.println("\n" + name + " has been successfully added!");
        }
    }

    // EFFECTS: prints error message
    private void printDuplicate() {
        System.out.println("Player has already been added. Try updating the player instead.");
    }

    // EFFECTS: prints the number of players (goalies + skaters). when there is only 1 player, the output is changed
    //          to be grammatically correct.
    private void doPlayerCount() {
        int players;                    // number of players on the team
        players = team.playerCount();
        int goalies;
        goalies = team.getGoalieCount();
        int skaters;
        skaters = team.getSkaterCount();
        if (players == 1) {
            System.out.println("There is 1 player on the Calgary Flames, including " + goalies + " goalie(s) and "
                    + skaters + " skater(s).");
        } else {
            System.out.println("There are " + players + " players on the Calgary Flames, including " + goalies
                            + " goalie(s) and "  + skaters + " skater(s).");
        }
    }

    // EFFECTS: prompts user to choose to view goalie list or player list
    private void doViewPlayers() {
        System.out.println("\n View skater list or goalie list?");
        String choice = choosePlayerType();
        if (choice.equals("g")) {
            doViewGoalies();
        } else {
            doViewSkaters();
        }
    }

    // EFFECTS: prints list of goalies and their information
    private void doViewGoalies() {
        List<Goalie> goalieList;
        goalieList = team.getGoalieList();
        System.out.println("\n" + team.getName() + " goalies list: ");
        for (Goalie nextGoalie : goalieList) {
            System.out.println("\t Name: " + nextGoalie.getName() + ", Jersey Number: #" + nextGoalie.getNumber()
                    + ", Age: " + nextGoalie.getAge() + ", Position: " + nextGoalie.getPosition());
        }
    }

    // EFFECTS: prints list of skaters and their information
    private void doViewSkaters() {
        List<Skater> skaterList;
        skaterList = team.getSkaterList();
        System.out.println("\n " + team.getName() + " skaters list: ");
        for (Skater nextSkater : skaterList) {
            System.out.println("\t Name: " + nextSkater.getName() + ", Jersey Number: #" + nextSkater.getNumber()
                    + ", Age: " + nextSkater.getAge() + ", Position: " + nextSkater.getPosition());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the new team name
    private void doChangeName() {
        System.out.print("Current team name: " + team.getName() + ". Enter new team name:");
        String name = input.next();
        team.setName(name);
        System.out.println("New team name is: " + team.getName());
    }

    // MODIFIES: selection
    // EFFECTS: prompts user to select either a goalie or a skater
    private String choosePlayerType() {
        String selection = "";

        while (!(selection.equals("g") || selection.equals("s"))) {
            System.out.println("\tg -> select for goalie");
            System.out.println("\ts -> select for skater");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        return selection;
    }

}
