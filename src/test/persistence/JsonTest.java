package persistence;

import model.Goalie;
import model.Skater;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGoalie(Goalie goalie, String name, int age, String position, int number) {
        assertEquals(name, goalie.getName());
        assertEquals(age, goalie.getAge());
        assertEquals(position, goalie.getPosition());
        assertEquals(number, goalie.getNumber());
    }

    protected void checkSkater(Skater skater, String name, int age, String position, int number) {
        assertEquals(name, skater.getName());
        assertEquals(age, skater.getAge());
        assertEquals(position, skater.getPosition());
        assertEquals(number, skater.getNumber());
    }
}
