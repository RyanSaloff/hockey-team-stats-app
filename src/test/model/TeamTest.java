package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team testTeam;
    private Skater testSkaterA;
    private Skater testSkaterB;
    private Skater testSkaterC;
    private Goalie testGoalieA;
    private Goalie testGoalieB;
    private Goalie testGoalieC;

    @BeforeEach
    void runBefore() {
        testTeam = new Team("Calgary Flames");
        testSkaterA = new Skater();
        testSkaterA.setName("Mikael Backlund");
        testSkaterA.setAge(34);
        testSkaterA.setNumber(11);
        testSkaterA.setPosition("C");
        testSkaterB = new Skater();
        testSkaterB.setName("Noah Hanifin");
        testSkaterB.setAge(26);
        testSkaterB.setNumber(55);
        testSkaterB.setPosition("D");
        testSkaterC = new Skater();
        testSkaterC.setName("Andrew Mangiapane");
        testSkaterC.setAge(27);
        testSkaterC.setNumber(88);
        testSkaterC.setPosition("RW");
        testGoalieA = new Goalie();
        testGoalieA.setName("Linus Ullmark");
        testGoalieA.setAge(30);
        testGoalieA.setNumber(35);
        testGoalieA.setPosition("G");
        testGoalieB = new Goalie();
        testGoalieB.setName("Jacob Markstrom");
        testGoalieB.setAge(33);
        testGoalieB.setNumber(25);
        testGoalieB.setPosition("G");
        testGoalieC = new Goalie();
        testGoalieC.setName("Dan Vladar");
        testGoalieC.setAge(26);
        testGoalieC.setNumber(80);
        testGoalieC.setPosition("G");
    }

    // Checks that constructor returns the proper values for its fields
    @Test
    void testConstructor() {
        assertEquals(testTeam.getName(), "Calgary Flames");
        assertEquals(testTeam.getGoalieCount(), 0);
        assertEquals(testTeam.getSkaterCount(), 0);
        assertEquals(testTeam.playerCount(), 0);
    }

    // Adds a goalie to an empty goalie list
    @Test
    void addGoalieToEmptyList() {
        testTeam.setName("Boston Bruins");
        testTeam.addGoalie(testGoalieA);
        assertEquals(testTeam.getName(), "Boston Bruins");
        List<Goalie> goalieList = new ArrayList<>();
        goalieList.add(testGoalieA);
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 1);
        assertEquals(testTeam.playerCount(), 1);
    }

    // Adds two goalies
    @Test
    void addTwoGoalies() {
        testTeam.addGoalie(testGoalieA);
        testTeam.addGoalie(testGoalieC);
        List<Goalie> goalieList = new ArrayList<>();
        goalieList.add(testGoalieA);
        goalieList.add(testGoalieC);
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 2);
        assertEquals(testTeam.playerCount(), 2);
    }

    // Adds the same goalie twice (second add fails) before adding another goalie. Adds the first goalie again and fails
    @Test
    void addSameGoalie() {
        assertTrue(testTeam.addGoalie(testGoalieC));
        assertFalse(testTeam.addGoalie(testGoalieC));
        assertTrue(testTeam.addGoalie(testGoalieA));
        assertFalse(testTeam.addGoalie(testGoalieC));
        List<Goalie> goalieList = new ArrayList<>();
        goalieList.add(testGoalieC);
        goalieList.add(testGoalieA);
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 2);
        assertEquals(testTeam.playerCount(), 2);
    }

    // Adds a skater to an empty skater list
    @Test
    void addSkaterToEmptyList() {
        testTeam.addSkater(testSkaterA);
        List<Skater> skaterList = new ArrayList<>();
        skaterList.add(testSkaterA);
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 1);
        assertEquals(testTeam.playerCount(), 1);
    }

    // Adds two skaters and one goalie to test that the player count is properly updating
    @Test
    void addTwoSkatersOneGoalie() {
        testTeam.addSkater(testSkaterA);
        testTeam.addSkater(testSkaterC);
        testTeam.addGoalie(testGoalieA);
        List<Skater> skaterList = new ArrayList<>();
        skaterList.add(testSkaterA);
        skaterList.add(testSkaterC);
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 2);
        assertEquals(testTeam.getGoalieCount(), 1);
        assertEquals(testTeam.playerCount(), 3);
    }

    // Tries to add five players to the team. A is added three times and B is added twice so only two players are
    // added
    @Test
    void addSamePlayer() {
        assertTrue(testTeam.addSkater(testSkaterA));
        assertFalse(testTeam.addSkater(testSkaterA));
        assertTrue(testTeam.addSkater(testSkaterB));
        assertFalse(testTeam.addSkater(testSkaterA));
        assertFalse(testTeam.addSkater(testSkaterB));
        List<Skater> skaterList = new ArrayList<>();
        skaterList.add(testSkaterA);
        skaterList.add(testSkaterB);
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 2);
        assertEquals(testTeam.playerCount(), 2);
    }

    // Tests the case where two different goalies have matching numbers (fails to add)
    @Test
    void addGoalieMatchingNumber() {
        testTeam.addGoalie(testGoalieA);
        testGoalieB.setNumber(35);
        assertFalse(testTeam.addGoalie(testGoalieB));
    }

    // Tests the case where two different goalies have matching numbers (fails to add)
    @Test
    void addPlayerMatchingNumber() {
        testTeam.addSkater(testSkaterA);
        testSkaterB.setNumber(11);
        assertFalse(testTeam.addSkater(testSkaterB));
    }

    // Tests the case where removeGoalie is called on an empty list of goalies
    @Test
    void removeGoalieEmptyList() {
        assertFalse(testTeam.removeGoalie(30));
        List<Goalie> goalieList = new ArrayList<>();
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 0);
    }

    // Tests the case where the only goalie is removed
    @Test
    void removeOneGoalie() {
        testTeam.addGoalie(testGoalieC);
        assertTrue(testTeam.removeGoalie(80));
        List<Goalie> goalieList = new ArrayList<>();
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 0);
    }

    // Tests the case where a team of three goalies has the same goalie removed twice
    @Test
    void removeSomeGoalies() {
        testTeam.addGoalie(testGoalieC);
        testTeam.addGoalie(testGoalieB);
        testTeam.addGoalie(testGoalieA);
        assertTrue(testTeam.removeGoalie(80));
        assertFalse(testTeam.removeGoalie(80));
        List<Goalie> goalieList = new ArrayList<>();
        goalieList.add(testGoalieB);
        goalieList.add(testGoalieA);
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 2);
        assertEquals(testTeam.playerCount(), 2);
    }

    // Tests the case where a team of three goalies and one player has all goalies removed but no players removed
    @Test
    void removeAllGoaliesNotPlayers() {
        testTeam.addGoalie(testGoalieC);
        testTeam.addGoalie(testGoalieB);
        testTeam.addGoalie(testGoalieA);
        testTeam.addSkater(testSkaterA);
        assertTrue(testTeam.removeGoalie(80));
        assertTrue(testTeam.removeGoalie(35));
        assertTrue(testTeam.removeGoalie(25));
        List<Goalie> goalieList = new ArrayList<>();
        assertEquals(testTeam.getGoalieList(), goalieList);
        assertEquals(testTeam.getGoalieCount(), 0);
        assertEquals(testTeam.getSkaterCount(), 1);
        assertEquals(testTeam.playerCount(), 1);
    }

    // Tests the case where removeSkater is called on an empty player list
    @Test
    void removeEmptySkaterList() {
        assertFalse(testTeam.removeSkater(11));
        List<Skater> skaterList = new ArrayList<>();
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 0);
    }

    // Tests the case where a skater is added and then removed
    @Test
    void removeOneSkater() {
        testTeam.addSkater(testSkaterA);
        assertTrue(testTeam.removeSkater(11));
        List<Skater> skaterList = new ArrayList<>();
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 0);
        assertEquals(testTeam.playerCount(), 0);
    }

    // Tests the case where a skater is removed several times from a team of skaters and goalies
    @Test
    void removeSomeSkaters() {
        testTeam.addSkater(testSkaterC);
        testTeam.addSkater(testSkaterB);
        testTeam.addSkater(testSkaterA);
        testTeam.addGoalie(testGoalieA);
        assertTrue(testTeam.removeSkater(55));
        assertFalse(testTeam.removeSkater(55));
        assertTrue(testTeam.removeSkater(88));
        assertFalse(testTeam.removeSkater(55));
        List<Skater> skaterList = new ArrayList<>();
        skaterList.add(testSkaterA);
        assertEquals(testTeam.getSkaterList(), skaterList);
        assertEquals(testTeam.getSkaterCount(), 1);
        assertEquals(testTeam.getSkaterCount(), 1);
        assertEquals(testTeam.playerCount(), 2);

    }

    // Tests that the hashcode is not equal for two different teams
    @Test
    void testHashCode() {
        Team testTeamTwo = new Team("Edmonton Oilers");
        assertFalse(testTeam.hashCode() == testTeamTwo.hashCode());
    }

    // Tests that the equals method on objects of different classes
    @Test
    void testEquals() {
        assertFalse(testTeam.equals(null));
        assertFalse(testSkaterA.equals(testGoalieA));
    }


}
