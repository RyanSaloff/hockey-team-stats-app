package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Skater (RW, C, LW, D) that has a name, age, and position.
// Implements the getter methods from Player
public class Skater implements Player, Writable {
    private String name;
    private int age;
    private String position;
    private int number;

    // MODIFIES: this
    // EFFECTS: updates the skater's name
    @Override
    public void setName(String skaterName) {
        this.name = skaterName;
    }

    // REQUIRES: skaterAge >= 0
    // MODIFIES: this
    // EFFECTS: updates the skater's age
    public void setAge(int skaterAge) {
        this.age = skaterAge;
    }

    // REQUIRES: skaterPosition == "RW", "C", "LW", "D"
    // MODIFIES: this
    // EFFECTS: updates the skater's position
    public void setPosition(String skaterPosition) {
        this.position = skaterPosition;
    }

    // REQUIRES: 1 <= skaterNumber <= 99
    // MODIFIES: this
    // EFFECTS: updates the skater's jersey number
    public void setNumber(int skaterNumber) {
        this.number = skaterNumber;
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
