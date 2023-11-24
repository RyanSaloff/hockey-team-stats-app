package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Goalie (G) that has a name, age, and position.
// Implements the setter and getter methods from Player
public class Goalie implements Player, Writable {
    private String name;
    private int age;
    private String position;
    private int number;

    // MODIFIES: this
    // EFFECTS: updates the goalie's name
    public void setName(String goalieName) {
        this.name = goalieName;
    }

    // REQUIRES: goalieAge >= 0
    // MODIFIES: this
    // EFFECTS: updates the goalie's age
    public void setAge(int goalieAge) {
        this.age = goalieAge;
    }

    // REQUIRES: goaliePosition == {"RW", "C", "LW", "D"}
    // MODIFIES: this
    // EFFECTS: updates the goalie's position
    public void setPosition(String goaliePosition) {
        this.position = goaliePosition;
    }

    // REQUIRES: 1 <= goalieNumber <= 99
    // MODIFIES: this
    // EFFECTS: updates the player's jersey number
    public void setNumber(int goalieNumber) {
        this.number = goalieNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    // add specification
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("position", position);
        json.put("number", number);
        return json;
    }
}
