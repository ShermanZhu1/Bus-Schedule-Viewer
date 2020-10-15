package testtimecalculator;

import org.junit.jupiter.api.Test;
import timecalculator.DestTimeCalculator;

public class TestDestTimeCalculator {

    @Test
    void testDestTimeCalculator() {
        new DestTimeCalculator("Granville", "25", "12:56", 123);
        new DestTimeCalculator("Granville", "49", "12:56", 123);
        new DestTimeCalculator("UBC Exchange", "25", "12:56", 123);
        new DestTimeCalculator("UBC Exchange", "49", "12:56", 123);
        DestTimeCalculator dtc = new DestTimeCalculator("Nanaimo Station", "25", "12:56", 123);
        dtc.giveMessage("Nanaimo Station", "25", "12:56", 123);
    }
}
