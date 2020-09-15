package ui;

import askuser.AskBusNumber;
import askuser.AskBusStop;
import askuser.AskTime;
import exceptions.BusDoesNotGoToStopException;
import exceptions.InvalidInputException;
import saveload.SaveLoadPrev;
import timecalculator.ArriveTimeCalculator;
import timecalculator.DestTimeCalculator;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static saveload.SaveLoadPrev.saveAll;

public class AskUserInput extends Subject {

    private static String time = "";
    private static String busnum;
    private static String busstop;

    public AskUserInput() throws IOException {
        CheckBusStopCancelled cbsc = new CheckBusStopCancelled();
        addObserver(cbsc);
        askTime();
        askBusnum();
        askBusstop();
        notifyObservers(busstop);
        new ArriveTimeCalculator(busstop, busnum, time);
        askSave();
        askDestination();
    }

    //MODIFIES: time
    //EFFECTS: Asks user for time, and changes time to input
    public void askTime() {
        while (!AskTime.isStringTime(time)) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("input time");
                time = scanner.nextLine();
                if (!AskTime.isStringTime(time)) {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                continue;
            } finally {
                if (AskTime.isStringTime(time)) {
                    System.out.println("time inputted is " + time);
                }
            }
        }
    }

    //MODIFIES: busnum
    //EFFECTS: Asks user for bus number, changes busnum to input
    public void askBusnum() {
        AskBusNumber abn = new AskBusNumber();
        while (!abn.isStringBusNumber(busnum)) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("input Bus Number");
                busnum = scanner.nextLine();
                if (!abn.isStringBusNumber(busnum)) {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                continue;
            } finally {
                if (abn.isStringBusNumber(busnum)) {
                    System.out.println("Bus Number inputted is " + busnum);
                }
            }
        }
    }

    private Scanner scanner1 = new Scanner(System.in);
    private AskBusStop thisthing1 = new AskBusStop();

    //MODIFIES: busstop
    //EFFECTS: Asks user for bus stop, changes busstop to input
    public void askBusstop() {
        while (!thisthing1.doesBusGoToStop(busstop, busnum)) {
            try {
                System.out.println("input Bus Stop");
                busstop = scanner1.nextLine();
                if (!thisthing1.doesBusGoToStop(busstop, busnum)) {
                    throw new BusDoesNotGoToStopException();
                }
            } catch (BusDoesNotGoToStopException e) {
                continue;
            } finally {
                if (thisthing1.doesBusGoToStop(busstop, busnum)) {
                    System.out.println("Bus Stop inputted is " + busstop);
                }
            }
        }
    }

    //EFFECTS: Asks user to save or not, saves if user inputs Y
    public void askSave() throws IOException {
        System.out.println("Save current settings? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        String bool = scanner.nextLine();
        if (isStringYN(bool)) {
            System.out.println("selected " + bool);
            if (Objects.equals(bool, "Y")) {
                saveAll("inputfile.txt", "outputfile.txt", time, busnum, busstop);
            }
        } else {
            System.out.println("Invalid Input");
            new SaveLoadPrev();
        }
    }

    //EFFECTS: Asks user to input destination, calculates destination time
    public void askDestination() {
        System.out.println("Input destination:");
        Scanner scanner = new Scanner(System.in);
        AskBusStop thisthing = new AskBusStop();
        String dest = scanner.nextLine();
        if (thisthing.doesBusGoToStop(dest, busnum)) {
            System.out.println("Bus Stop inputted is " + dest);
            new DestTimeCalculator(dest,
                    busnum,
                    ArriveTimeCalculator.getArriveTime(),
                    ArriveTimeCalculator.getWaitTime());
        } else {
            System.out.println("The " + busnum + " bus does not go to this stop");
            askDestination();
        }
    }

    //EFFECTS: Returns true if String is one of Y or N
    private boolean isStringYN(String bool) {
        return Objects.equals(bool, "Y") || Objects.equals(bool, "N");
    }

}
