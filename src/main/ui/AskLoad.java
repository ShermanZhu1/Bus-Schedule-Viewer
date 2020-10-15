package ui;

import saveload.SaveLoadPrev;
import timecalculator.ArriveTimeCalculator;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AskLoad {

    private static boolean skip;

    public AskLoad() throws IOException {
        askLoadSettings();
        if (!skip) {
            new AskUserInput();
        }
    }

    //MODIFIES: skip
    //EFFECTS: Asks whether to load settings or not, if yes, changes skip to true
    public void askLoadSettings() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Load previous settings? (Y/N)");
        String bool = scanner.nextLine();
        if (isStringYN(bool)) {
            System.out.println("Selected " + bool);
            if (Objects.equals(bool, "Y")) {
                skip = true;
                try {
                    loadAll();
                } catch (IOException e) {
                    System.out.println("File not found");
                }
            }
        } else {
            System.out.println("Invalid Input");
            askLoadSettings();
        }
    }

    //EFFECTS: Prints out previous settings and arrival time
    public void loadAll() throws IOException {
        System.out.println("Previous setting were:");
        System.out.print("Time: " + SaveLoadPrev.getPrevTime("outputfile.txt") + " | ");
        System.out.print("Bus Number: " + SaveLoadPrev.getPrevBusnum("outputfile.txt") + " | ");
        System.out.println("Bus Stop: " + SaveLoadPrev.getPrevBusstop("outputfile.txt"));
        String time = SaveLoadPrev.getPrevTime("outputfile.txt");
        String busnum = SaveLoadPrev.getPrevBusnum("outputfile.txt");
        String busstop = SaveLoadPrev.getPrevBusstop("outputfile.txt");
        new ArriveTimeCalculator(busstop, busnum, time);
    }

    //EFFECTS: Returns true if String is Y or N
    private boolean isStringYN(String bool) {
        return Objects.equals(bool, "Y") || Objects.equals(bool, "N");
    }
}
