package testaskuser;

import askuser.AskBusStop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAskBusStop {

    @Test
    void testdoesBusGoToStop() {
        AskBusStop test = new AskBusStop();
        assertTrue(test.doesBusGoToStop("Nanaimo Station", "25"));
        assertTrue(test.doesBusGoToStop("Granville", "49"));
        assertFalse(test.doesBusGoToStop("Nanaimo", "25"));
        assertFalse(test.doesBusGoToStop("Nanaimo Station", "49"));
        assertFalse(test.doesBusGoToStop("Nanaimo Station", "49"));
    }
}
