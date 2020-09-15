package saveload;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveLoadPrev {

    //REQUIRES: valid input, output, time, busnum, and busstop
    //MODIFIES: output
    //EFFECTS:  Writes time, busnum and busstop to given output file
    public static void saveAll(String input,
                               String output,
                               String time,
                               String busnum,
                               String busstop) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(input));
        PrintWriter writer = new PrintWriter(output,"UTF-8");
        lines.add(time);
        lines.add(busnum);
        lines.add(busstop);
        for (String line : lines) {
            writer.println(line);
        }
        writer.close();
    }

    //EFFECTS:  Returns line from given output file
    public static String getPrevThing(String output, int n) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(output));
        return lines.get(n);
    }

    //EFFECTS: Returns time from given output file
    public static String getPrevTime(String output) throws IOException {
        return getPrevThing(output, 0);
    }

    //EFFECTS: Returns bus stop from given output file
    public static String getPrevBusstop(String output) throws IOException {
        return getPrevThing(output, 2);
    }

    //EFFECTS: Returns bus num from given output file
    public static String getPrevBusnum(String output) throws IOException {
        return getPrevThing(output, 1);
    }
}
