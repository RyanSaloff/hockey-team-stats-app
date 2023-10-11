package model;

// Represents a Goalie (G) that has a name, age, and position.
// Implements the setter and getter methods from Player
public class Goalie implements Player {
    private String name; // add comments
    private int age;
    private String position;
    private int number;

    // MODIFIES: this
    // EFFECTS: updates the goalie's name
    @Override
    public void setName(String goalieName) {
        this.name = goalieName;
    }

    // REQUIRES: goalieAge >= 0
    // MODIFIES: this
    // EFFECTS: updates the goalie's age
    @Override
    public void setAge(int goalieAge) {
        this.age = goalieAge;
    }

    // REQUIRES: goaliePosition == {"RW", "C", "LW", "D"}
    // MODIFIES: this
    // EFFECTS: updates the goalie's position
    @Override
    public void setPosition(String goaliePosition) {
        this.position = goaliePosition;
    }

    // REQUIRES: 1 <= goalieNumber <= 99
    // MODIFIES: this
    // EFFECTS: updates the player's jersey number
    @Override
    public void setNumber(int goalieNumber) {
        this.number = goalieNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
