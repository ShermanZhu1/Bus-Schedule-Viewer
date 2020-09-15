package timecalculator;

public class ArriveTimeCalculator extends TimeCalculator {
    private static String arrivetime;
    private static int waittime;

    //EFFECTS:  Calls the other methods in order
    public ArriveTimeCalculator(String busstop, String busnum, String time) {
        arrivetime = giveArriveTime(time, busnum, busstop);
        waittime = giveWaitTime(time, giveArriveTime(time, busnum, busstop), 0);
        System.out.println(timePrinter(busnum,
                busstop,
                giveWaitTime(time, giveArriveTime(time, busnum, busstop), 0),
                giveArriveTime(time, busnum, busstop)));
    }


    //EFFECTS:  Prints the final message
    private static String timePrinter(String busnum, String busstop, int waittime, String arrivetime) {
        String message = "The next " + busnum + " bus at " + busstop + " arrives in " + waittime
                + " minutes at " + arrivetime;
        return message;

    }

    //EFFECTS:  Returns timeprinter message
    public String giveMessage(String busstop, String busnum, String time) {
        return timePrinter(busnum,
                busstop,
                giveWaitTime(time, giveArriveTime(time, busnum, busstop), 0),
                giveArriveTime(time, busnum, busstop));
    }

    public static String getArriveTime() {
        return arrivetime;
    }

    public static int getWaitTime() {
        return waittime;
    }
}
