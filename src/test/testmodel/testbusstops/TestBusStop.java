package testmodel.testbusstops;

import model.busses.Bus;
import model.busstops.BusStop;
import model.busstops.GetBusTimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBusStop {

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
    void testgetBustimes25() {
        BusStop ubc = new BusStop("UBC Exchange", bustimes25, bustimes49);
        List<String> listbustimes25 = ubc.getBustimes25();
        assertEquals(listbustimes25.get(0), "1:12");
        assertEquals(listbustimes25.get(5), "6:45");
        assertEquals(listbustimes25.get(22), "23:11");
    }

    @Test
    void testgetBustimes49() {
        BusStop ubc = new BusStop("UBC Exchange", bustimes25, bustimes49);
        List<String> listbustimes49 = ubc.getBustimes49();
        assertEquals(listbustimes49.get(0), "1:45");
        assertEquals(listbustimes49.get(5), "6:15");
        assertEquals(listbustimes49.get(22), "23:41");
    }

    @Test
    void testName() {
        BusStop granville = new BusStop("Granville");
        assertEquals(granville.getName(), "Granville");
    }

    @Test
    void testBusses() {
        BusStop ubc = new BusStop("UBC Exchange", bustimes25, bustimes49);
        Bus bus25 = new Bus("25");
        Bus bus49 = new Bus("49");
        ubc.addBus(bus25);
        ubc.addBus(bus49);
        ubc.getBusses();
    }
}
