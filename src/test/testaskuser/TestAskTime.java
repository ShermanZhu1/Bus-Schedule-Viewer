package testaskuser;

import askuser.AskTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TestAskTime {

    @Test
    void testisStringTime() {
        assertTrue(AskTime.isStringTime("12:34"));
        assertFalse(AskTime.isStringTime("1234"));
        assertFalse(AskTime.isStringTime("12.34"));
        assertFalse(AskTime.isStringTime("ab:cd"));
        assertFalse(AskTime.isStringTime("51:34"));
        assertFalse(AskTime.isStringTime("12:61"));
    }
}
