package askuser;

import exceptions.NotBusException;
import model.busses.Bus;
import model.busstops.BusStop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AskBusStop {

    private Map<Bus, List<BusStop>> busStopsMap = new HashMap<>();
    public Bus bus25 = new Bus("25");
    public Bus bus49 = new Bus("49");

    //EFFECTS:  If busstop is in listbusstops, return true; otherwise, return false
    public Boolean doesBusGoToStop(String busstop, String busnum) {
        initMap();
        List<BusStop> listbusstops = busStopsMap.get(getBus(busnum));
        for (BusStop bstop : listbusstops) {
            String name = bstop.getName();
            if (name.equals(busstop)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: Retrieves corresponding bus of given bus number
    private Bus getBus(String busnum) throws NotBusException {
        if (Objects.equals(busnum, "25")) {
            return bus25;
        } else if (Objects.equals(busnum, "49")) {
            return bus49;
        } else {
            throw new NotBusException();
        }
    }

    //EFFECTS:  Creates new bus stops, adds busses, and maps bus stops
    public void initMap() {
        BusStop nanaimo = new BusStop("Nanaimo Station");
        BusStop granville = new BusStop("Granville");
        BusStop ubcexchange = new BusStop("UBC Exchange");
        bus25.addBusStop(nanaimo);
        bus25.addBusStop(granville);
        bus25.addBusStop(ubcexchange);
        bus49.addBusStop(granville);
        bus49.addBusStop(ubcexchange);
        busStopsMap.put(bus25, bus25.getBusstops());
        busStopsMap.put(bus49, bus49.getBusstops());
    }
}

