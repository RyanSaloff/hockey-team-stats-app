package model;

// REQUIRES: age >= 0, position == {"RW", "C", "LW", "D", "G"}, 1 <= number <= 99
// EFFECTS: constructs a player with a name, age, position, and jersey number
public interface Player {

    void setName(String name);

    void setAge(int age);

    void setPosition(String position);

    void setNumber(int number);

    String getName();

    int getAge();

    String getPosition();

    int getNumber();
}
