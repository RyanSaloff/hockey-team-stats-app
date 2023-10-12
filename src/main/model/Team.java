package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {
    private int skaters;             // number of skaters on a team
    private int goalies;             // number of goalies on a team
    private int players;             // number of players (goalies + skaters) on a team
    private String name;             // the team's name
    private List<Skater> skaterList; // team's list of skaters
    private List<Goalie> goalieList; // team's list of goalies

    // EFFECTS: Constructs a team with a given name and no skaters/goalies
    public Team(String teamName) {
        this.name = teamName;
        this.skaterList = new ArrayList<>();
        this.goalieList = new ArrayList<>();
        this.skaters = 0;
        this.goalies = 0;
        this.players = 0;
    }

    public void setName(String teamName) {
        this.name = teamName;
    }

    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: if goalie is already in the team's list of goalies, returns false. otherwise adds a goalie to the end
    //          of the team's list of goalie and also adds one to the goalie count
    public boolean addGoalie(Goalie goalie) {
        for (Goalie nextGoalie : goalieList) {
            if (nextGoalie == goalie || nextGoalie.getNumber() == goalie.getNumber()) {
                return false;
            }
        }
        goalieList.add(goalie);
        this.goalies++;
        return true;
    }

    public List<Goalie> getGoalieList() {
        return this.goalieList;
    }

    public int getGoalieCount() {
        return this.goalies;
    }

    // MODIFIES: this
    // EFFECTS: if skater is already in the team's list of skaters, returns false. otherwise adds a skater to the end
    //          of the team's list of skaters and also adds one to the skater count
    public boolean addSkater(Skater skater) {
        for (Skater nextSkater : skaterList) {
            if (nextSkater == skater || nextSkater.getNumber() == skater.getNumber()) {
                return false;
            }
        }
        skaterList.add(skater);
        this.skaters++;
        return true;
    }

    public List<Skater> getSkaterList() {
        return this.skaterList;
    }

    public int getSkaterCount() {
        return this.skaters;
    }

    // MODIFIES: this
    // EFFECTS: finds and returns how many players are on a team
    public int playerCount() {
        this.players = goalies + skaters;
        return players;
    }


    // REQUIRES: 1 <= jerseyNumber <= 99
    // MODIFIES: this
    // EFFECTS: if the jersey number matches with a goalie, removes goalie from the list and returns true.
    //          otherwise returns false
    public boolean removeGoalie(int jerseyNumber) {
        for (Goalie goalie : goalieList) {
            if (goalie.getNumber() == jerseyNumber) {
                goalieList.remove(goalie);
                this.goalies--;
                return true;
            }
        }
        return false;
    }

    // REQUIRES: 1 <= jerseyNumber <= 99
    // MODIFIES: this
    // EFFECTS: if the jersey number matches with a skater, removes skater from the list and returns true.
    //          otherwise returns false
    public boolean removeSkater(int jerseyNumber) {
        for (Skater skater : skaterList) {
            if (skater.getNumber() == jerseyNumber) {
                skaterList.remove(skater);
                this.skaters--;
                return true;
            }
        }
        return false;
    }
}







