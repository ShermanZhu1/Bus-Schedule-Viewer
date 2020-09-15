package testmodel.testbusses;

import model.busses.Bus;
import model.busstops.BusStop;
import model.busstops.GetBusTimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBus {

    List<String> bustimes25;
    List<String> bustimes49;

    @BeforeEach
    void initBusStop() {
        GetBusTimes test49 = new GetBusTimes("49", "UBC Exchange");
        GetBusTimes test25 = new GetBusTimes("25", "UBC Exchange");
        bustimes25 = test25.getBusTimes();
        bustimes49 = test49.getBusTimes();
    }

    @Test
    void testConstructor() {
        Bus bus25 = new Bus("25");
        assertEquals(bus25.getBusnum(), "25");
    }

    @Test
    void testAddBusStop() {
        BusStop ubc = new BusStop("UBC Exchange", bustimes25, bustimes49);
        Bus bus25 = new Bus("25");
        Bus bus49 = new Bus("49");
        bus25.addBusStop(ubc);
        bus25.getBusstops();
        assertTrue(bus25.equals(bus25));
        assertFalse(bus25.equals(bus49));
        assertFalse(bus25.equals(null));
        bus25.hashCode();
    }
}
