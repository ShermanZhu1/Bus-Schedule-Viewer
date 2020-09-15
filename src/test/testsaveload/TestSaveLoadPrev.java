package testsaveload;

import org.junit.jupiter.api.Test;
import saveload.SaveLoadPrev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSaveLoadPrev {

    @Test
    public void testsaveAll() throws IOException {
        SaveLoadPrev.saveAll("testinputfile.txt",
                "testoutputfile.txt",
                "12:45",
                "25",
                "Nanaimo Station");
        List<String> lines = Files.readAllLines(Paths.get("testoutputfile.txt"));
        assertEquals(lines.get(0), "12:45");
        assertEquals(lines.get(1), "25");
        assertEquals(lines.get(2), "Nanaimo Station");
    }

    @Test
    public void testgetPrevTime() throws IOException {
        SaveLoadPrev.saveAll("testinputfile.txt",
                "testoutputfile.txt",
                "12:13",
                "25",
                "Nanaimo Station");
        String prevtime = SaveLoadPrev.getPrevTime("testoutputfile.txt");
        assertEquals(prevtime, "12:13");
        assertTrue(true);
    }

    @Test
    public void testgetPrevBusstop() throws IOException {
        SaveLoadPrev.saveAll("testinputfile.txt",
                "testoutputfile.txt",
                "12:13",
                "25",
                "UBC Exchange");
        String prevBusstop = SaveLoadPrev.getPrevBusstop("testoutputfile.txt");
        assertEquals(prevBusstop, "UBC Exchange");
    }

    @Test
    public void testgetPrevBusnum() throws IOException {
        SaveLoadPrev.saveAll("testinputfile.txt",
                "testoutputfile.txt",
                "12:13",
                "49",
                "Nanaimo Station");
        String prevBusnum = SaveLoadPrev.getPrevBusnum("testoutputfile.txt");
        assertEquals(prevBusnum, "49");
    }
}
