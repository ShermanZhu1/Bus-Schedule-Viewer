package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class IsBusStopCancelled {

    public IsBusStopCancelled() {
    }

    //REQUIRES: Valid bus stop
    //EFFECTS:  Returns if bus stop is cancelled or not via Translink API
    public boolean isBusStopCancelled(String busstop) throws IOException {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            String theURL = constructURL(busstop);
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return parseXML(sb);
    }

    //REQUIRES: Valid stringbuilder
    //EFFECTS:  Searches for <CancelledStop> tag, and returns if is cancelled or not
    private Boolean parseXML(StringBuilder sb) {
        int isCancelledindex = sb.indexOf("<CancelledStop>") + 15;
        String cancelledbool = Character.toString(sb.charAt(isCancelledindex));
        if (Objects.equals(cancelledbool, "f")) {
            return false;
        } else {
            return true;
        }
    }

    //REQUIRES: Valid bus stop
    //EFFECTS:  Returns correct url for corresponding bus stop
    private String constructURL(String busstop) {
        if (Objects.equals(busstop, "Nanaimo Station")) {
            return "https://api.translink.ca/rttiapi/v1/stops/51561/estimates?apikey=LPrkW1dM2FRzG6NXhFpe&Count(1)";
        } else if (Objects.equals(busstop, "Granville")) {
            return "https://api.translink.ca/rttiapi/v1/stops/58136/estimates?apikey=LPrkW1dM2FRzG6NXhFpe&Count(1)";
        } else {
            return "https://api.translink.ca/rttiapi/v1/stops/61979/estimates?apikey=LPrkW1dM2FRzG6NXhFpe&Count(1)";
        }
    }
}
