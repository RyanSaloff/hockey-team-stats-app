package persistence;

import model.Goalie;
import model.Skater;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    Goalie goalie;
    Skater skater;

    @BeforeEach
    void runBefore() {
        goalie = new Goalie();
        goalie.setName("Jacob Markstrom");
        goalie.setAge(33);
        goalie.setPosition("G");
        goalie.setNumber(25);
        skater = new Skater();
        skater.setName("Mikael Backlund");
        skater.setAge(34);
        skater.setPosition("C");
        skater.setNumber(11);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Team team = new Team("Calgary Flames");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriteEmptyTeam() {
        try {
            Team team = new Team("Calgary Flames");
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyTeam.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyTeam.json");
            team = reader.read();
            assertEquals("Calgary Flames", team.getName());
            assertEquals(0, team.playerCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTeam() {
        try {
            Team team = new Team("Boston Bruins");
            team.addGoalie(goalie);
            team.addSkater(skater);
            JsonWriter writer = new JsonWriter("./data/testWriteBostonTeam.json");
            writer.open();
            writer.write(team);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteBostonTeam.json");
            team = reader.read();
            assertEquals("Boston Bruins", team.getName());
            assertEquals(1, team.getGoalieCount());
            assertEquals(1, team.getSkaterCount());

            checkGoalie(team.getGoalieList().get(0), "Jacob Markstrom", 33, "G", 25);
            checkSkater(team.getSkaterList().get(0),"Mikael Backlund", 34, "C", 11);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
