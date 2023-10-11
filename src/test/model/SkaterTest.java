package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SkaterTest {
    private Skater testSkaterOne;

    @BeforeEach
    void runBefore() {
        testSkaterOne = new Skater();

    }

    // Tests the fields for Skater
    @Test
    void testConstructor() {
        testSkaterOne.setName("Mikael Backlund");
        testSkaterOne.setAge(34);
        testSkaterOne.setNumber(11);
        testSkaterOne.setPosition("C");
        assertEquals(testSkaterOne.getName(), "Mikael Backlund");
        assertEquals(testSkaterOne.getAge(), 34);
        assertEquals(testSkaterOne.getNumber(), 11);
        assertEquals(testSkaterOne.getPosition(), "C");
    }
}
