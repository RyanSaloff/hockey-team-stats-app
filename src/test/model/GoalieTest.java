package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalieTest {
    private Goalie testGoalieOne;

    @BeforeEach
    void runBefore() {
        testGoalieOne = new Goalie();
    }

    // Tests the fields for Goalie
    @Test
    void testConstructor() {
        testGoalieOne.setName("Jacob Markstrom");
        testGoalieOne.setAge(33);
        testGoalieOne.setNumber(25);
        testGoalieOne.setPosition("G");
        assertEquals(testGoalieOne.getName(), "Jacob Markstrom");
        assertEquals(testGoalieOne.getAge(), 33);
        assertEquals(testGoalieOne.getNumber(), 25);
        assertEquals(testGoalieOne.getPosition(), "G");
    }
}
