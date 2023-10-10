package model;

// Represents a Skater (RW, C, LW, D) that has a name, age, and position.
// Implements the getter methods from Player
public class Skater implements Player {
    private String name;
    private int age;
    private String position;
    private int number;



    public Skater(String skaterName, int skaterAge, String skaterPosition, int skaterNumber) {

    }

    // MODIFIES: this
    // EFFECTS: updates the skater's name
    @Override
    public void setName(String skaterName) {
        this.name = skaterName;
    }

    // REQUIRES: skaterAge >= 0
    // MODIFIES: this
    // EFFECTS: updates the skater's age
    @Override
    public void setAge(int skaterAge) {
        this.age = skaterAge;
    }

    // REQUIRES: skaterPosition == "RW", "C", "LW", "D"
    // MODIFIES: this
    // EFFECTS: updates the skater's position
    @Override
    public void setPosition(String skaterPosition) {
        this.position = skaterPosition;
    }

    // REQUIRES: 1 <= skaterNumber <= 99
    // MODIFIES: this
    // EFFECTS: updates the skater's jersey number
    @Override
    public void setNumber(int skaterNumber) {
        this.number = skaterNumber;
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
