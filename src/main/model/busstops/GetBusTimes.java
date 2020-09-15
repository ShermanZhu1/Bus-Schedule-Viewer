package model.busstops;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetBusTimes {

    private static List<String> nanaimobustimes25 = Arrays.asList(
            "1:00",
            "2:17",
            "3:56",
            "4:48",
            "5:12",
            "6:34",
            "7:35",
            "8:29",
            "9:59",
            "10:32",
            "11:31",
            "12:35",
            "13:34",
            "14:45",
            "15:57",
            "16:12",
            "17:24",
            "18:38",
            "19:49",
            "20:23",
            "21:27",
            "22:29",
            "23:41");

    private static List<String> granvillebustimes25 = Arrays.asList(
            "1:09",
            "2:15",
            "3:59",
            "4:48",
            "5:31",
            "6:39",
            "7:39",
            "8:46",
            "9:59",
            "10:34",
            "11:48",
            "12:59",
            "13:20",
            "14:45",
            "15:31",
            "16:19",
            "17:56",
            "18:42",
            "19:41",
            "20:59",
            "21:29",
            "22:14",
            "23:17");

    private static List<String> granvillebustimes49 = Arrays.asList(
            "1:48",
            "2:31",
            "3:16",
            "4:48",
            "5:29",
            "6:35",
            "7:43",
            "8:59",
            "9:01",
            "10:17",
            "11:21",
            "12:33",
            "13:41",
            "14:57",
            "15:01",
            "16:19",
            "17:22",
            "18:35",
            "19:42",
            "20:51",
            "21:05",
            "22:14",
            "23:21");

    private static List<String> ubcexchangebustimes25 = Arrays.asList(
            "1:12",
            "2:27",
            "3:48",
            "4:56",
            "5:39",
            "6:45",
            "7:13",
            "8:59",
            "9:31",
            "10:47",
            "11:41",
            "12:33",
            "13:21",
            "14:27",
            "15:31",
            "16:19",
            "17:52",
            "18:48",
            "19:22",
            "20:11",
            "21:05",
            "22:24",
            "23:11");

    private static List<String> ubcexchangebustimes49 = Arrays.asList(
            "1:45",
            "2:29",
            "3:06",
            "4:48",
            "5:09",
            "6:15",
            "7:53",
            "8:09",
            "9:21",
            "10:37",
            "11:41",
            "12:53",
            "13:01",
            "14:17",
            "15:21",
            "16:39",
            "17:42",
            "18:45",
            "19:02",
            "20:11",
            "21:25",
            "22:34",
            "23:41");

    private String bus;
    private String busstop;
    private Map<String, List<String>> busTimesMap = new HashMap<>();

    //REQUIRES: valid bus and bus stop
    //MODIFIES: this
    //EFFECTS:  creates new bus stops and maps their bus schedules
    public GetBusTimes(String bus, String busstop) {
        this.bus = bus;
        this.busstop = busstop;
        BusStop nanaimo = new BusStop("Nanaimo Station", nanaimobustimes25);
        BusStop granville = new BusStop("Granville", granvillebustimes25, granvillebustimes49);
        BusStop ubcexchange = new BusStop("UBC Exchange", ubcexchangebustimes25, ubcexchangebustimes49);
        busTimesMap.put("Nanaimo Station25", nanaimo.getBustimes25());
        busTimesMap.put("Granville25", granville.getBustimes25());
        busTimesMap.put("Granville49", granville.getBustimes49());
        busTimesMap.put("UBC Exchange25", ubcexchange.getBustimes25());
        busTimesMap.put("UBC Exchange49", ubcexchange.getBustimes49());
    }

    //EFFECTS:  Creates a "key" which is a bus and bus stop concatenated
    private String keyGenerator(String bus, String busstop) {
        return (busstop + bus);
    }

    //EFFECTS:  Gets a mapped value
    public List<String> getBusTimes() {
        List<String> bustimes = busTimesMap.get(keyGenerator(bus, busstop));
        return bustimes;
    }
}
