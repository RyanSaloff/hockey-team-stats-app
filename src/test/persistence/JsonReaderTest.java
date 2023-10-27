package persistence;

import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakeFile.json");
        try {
            Team team = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testWriteEmptyTeam.json");
        try {
            Team team = reader.read();
            assertEquals("Calgary Flames", team.getName());
            assertEquals(0, team.playerCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriteBostonTeam.json");
        try {
            Team team = reader.read();
            assertEquals("Boston Bruins", team.getName());
            assertEquals(1, team.getGoalieCount());
            assertEquals(1, team.getSkaterCount());

            checkGoalie(team.getGoalieList().get(0), "Jacob Markstrom", 33, "G", 25);
            checkSkater(team.getSkaterList().get(0),"Mikael Backlund", 34, "C", 11);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
