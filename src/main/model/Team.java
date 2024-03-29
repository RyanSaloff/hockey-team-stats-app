package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a hockey team with a list of Players, list of Goalies, and a team name
public class Team implements Writable {
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

    // MODIFIES: this
    // EFFECTS: changes the team's name to teamName
    public void setName(String teamName) {
        this.name = teamName;
        EventLog.getInstance().logEvent(new Event("Set team name to the " + teamName));
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
        EventLog.getInstance().logEvent(new Event("Added " + goalie.getName() + " to the " + name));
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
        EventLog.getInstance().logEvent(new Event("Added " + skater.getName() + " to the " + name));
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
                EventLog.getInstance().logEvent(new Event("Removed " + goalie.getName()
                        + " from the " + name));
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
                EventLog.getInstance().logEvent(new Event("Removed " + skater.getName()
                        + " from the " + name));
                return true;
            }
        }
        return false;
    }

    // EFFECTS: generates a JSON representation of the current instance of Team
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("goalieList", goaliesToJson());
        json.put("skaterList", skatersToJson());
        return json;
    }

    // EFFECTS: returns goalies on this team as a JSON array
    private JSONArray goaliesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goalie g : goalieList) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns skaters on this team as a JSON array
    private JSONArray skatersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Skater s : skaterList) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}







