package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    // Tests adapted from the EventTest class in AlarmSystem

    @BeforeEach
    public void runBefore() {
        e = new Event("Skater added to the Calgary Flames");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Skater added to the Calgary Flames", e.getDescription());
        assertEquals(d.toString(), e.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Skater added to the Calgary Flames", e.toString());
    }

    // Tests that the hashcode is not equal for two different events
    @Test
    public void testDifferentHashCode() {
        Event testEventThree = new Event("Goalie added to the Calgary Flames");
        assertTrue(e.hashCode() != testEventThree.hashCode());
    }

    // Tests that objects of different classes are not equal
    @Test
    public void testNotEquals() {
        assertFalse(e.equals(d));
        assertFalse(e.equals(null));
    }
}
