package testtimecalculator;

import org.junit.jupiter.api.Test;
import timecalculator.ArriveTimeCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArriveTimeCalculator {

    @Test
    void testArriveTimeCalculator() {
        new ArriveTimeCalculator("Granville", "25", "12:56");
        new ArriveTimeCalculator("UBC Exchange", "25", "12:56");
        new ArriveTimeCalculator("Nanaimo Station", "25", "12:56");
        new ArriveTimeCalculator("Granville", "49", "12:56");
        new ArriveTimeCalculator("UBC Exchange", "49", "12:56");
    }

    @Test
    void testgetArrivetime() {
        ArriveTimeCalculator atc = new ArriveTimeCalculator("Granville", "25", "12:56");
        atc.giveMessage("Granville", "25", "12:56");
        assertEquals(ArriveTimeCalculator.getArriveTime(), "12:59");
    }

    @Test
    void testgetWaittime() {
        new ArriveTimeCalculator("Granville", "25", "12:56");
        assertEquals(ArriveTimeCalculator.getWaitTime(), 3);
    }
}
