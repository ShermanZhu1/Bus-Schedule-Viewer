package timecalculator;

import exceptions.NotBusException;
import model.busstops.GetBusTimes;

import java.util.List;

public abstract class TimeCalculator {

    //MODIFIES: this
    //EFFECTS:  Given time, searches for the next closest time in the given list bustimes
    public String giveArriveTime(String time, String busnum, String busstop) throws NotBusException {
        String[] parts = time.split(":");
        String hour = parts[0]; // hour
        String minutes = parts[1]; // minutes
        int timeminutes = (Integer.parseInt(hour) * 60 + Integer.parseInt(minutes));
        for (String bustime : givebustimes(busnum, busstop)) {
            String[] bustimeparts = bustime.split(":");
            String bustimeHour = bustimeparts[0]; // hour
            String bustimeMinutes = bustimeparts[1]; // minutes
            int nexttimeminutes = (Integer.parseInt(bustimeHour) * 60 + Integer.parseInt(bustimeMinutes));
            if (timeminutes < nexttimeminutes) {
                return bustime;
            }
        }
        throw new NotBusException();
    }

    //MODIFIES: this
    //EFFECTS:  Calculates waittime given time and arrivetime
    public int giveWaitTime(String time, String arrivetime, int waittime) {
        String timeCurrent = time;
        String[] parts = timeCurrent.split(":");
        String hour = parts[0]; // hour
        String minutes = parts[1]; // minutes
        int timeminutes = (Integer.parseInt(hour) * 60 + Integer.parseInt(minutes));
        String[] arrivetimeparts = arrivetime.split(":");
        String arrivehour = arrivetimeparts[0]; // hour
        String arriveminutes = arrivetimeparts[1]; // minutes
        int arrivetimeminutes = (Integer.parseInt(arrivehour) * 60 + Integer.parseInt(arriveminutes));
        return (arrivetimeminutes - timeminutes + waittime);
    }

    //MODIFIES: this
    //EFFECTS:  Given busnum and busstop, makes bustimes the corresponding set of bus times
    private List<String> givebustimes(String busnum, String busstop) throws NotBusException {
        GetBusTimes gbt = new GetBusTimes(busnum, busstop);
        return gbt.getBusTimes();
    }
}
