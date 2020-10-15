package timecalculator;

public class DestTimeCalculator extends TimeCalculator {

    private static String arrivetime;
    private static int waitTime;

    //EFFECTS:  Calls the other methods in order
    public DestTimeCalculator(String busstop, String busnum, String time, int waittime) {
        arrivetime = giveArriveTime(time, busnum, busstop);
        waitTime = giveWaitTime(time, giveArriveTime(time, busnum, busstop), 0);
        System.out.println(timePrinter(busstop,
                giveWaitTime(time, giveArriveTime(time, busnum, busstop), waittime),
                giveArriveTime(time, busnum, busstop)));
    }


    //EFFECTS:  Prints the final message
    private String timePrinter(String busstop, int waittime, String arrivetime) {
        String message = "Will arrive at " + busstop + " in " + waittime + " minutes at " + arrivetime;
        return message;
    }

    //EFFECTS:  Returns timeprinter message
    public String giveMessage(String busstop, String busnum, String time, int waittime) {
        return timePrinter(busstop,
                giveWaitTime(time, giveArriveTime(time, busnum, busstop), waittime),
                giveArriveTime(time, busnum, busstop));
    }
}
