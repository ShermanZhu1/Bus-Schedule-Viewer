package testaskuser;

import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import askuser.AskBusNumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestAskBusNumber {

    @Test
    public void testisStringBusNumber() throws InvalidInputException {
        AskBusNumber abn = new AskBusNumber();
        assertFalse(abn.isStringBusNumber("51"));
        assertFalse(abn.isStringBusNumber("25 "));
        assertTrue(abn.isStringBusNumber("49"));
        assertTrue(abn.isStringBusNumber("25"));
    }

}
