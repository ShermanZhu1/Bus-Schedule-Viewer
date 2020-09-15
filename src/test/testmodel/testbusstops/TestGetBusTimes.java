package testmodel.testbusstops;

import model.busstops.GetBusTimes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGetBusTimes {

    @Test
    void testGetBusTimes() {
        GetBusTimes gbt = new GetBusTimes("25", "Granville");
        List<String> bustimes49 = gbt.getBusTimes();
        assertEquals(bustimes49.get(0), "1:09");
    }
}
