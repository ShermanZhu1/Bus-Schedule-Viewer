package askuser;

import model.busses.Bus;
import model.busstops.BusStop;

public class AskBusNumber {

    Bus bus25 = new Bus("25");
    Bus bus49 = new Bus("49");

    //EFFECTS:  Checks if string (bus number) is in list
    public Boolean isStringBusNumber(String busnum) {
        String search = busnum;
        initBusses();
        BusStop granville = new BusStop("Granville");
        granville.addBus(bus25);
        granville.addBus(bus49);
        for (Bus bus: granville.getBusses()) {
            String busnum2 = bus.getBusnum();
            if (busnum2.equals(search)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS:  Creates new bus stops and adds busses to bus stops
    public void initBusses() {
        BusStop nanaimo = new BusStop("Nanaimo Station");
        BusStop granville = new BusStop("Granville");
        BusStop ubcexchange = new BusStop("UBC Exchange");
        bus25.addBusStop(nanaimo);
        bus25.addBusStop(granville);
        bus25.addBusStop(ubcexchange);
        bus49.addBusStop(granville);
        bus49.addBusStop(ubcexchange);
    }
}
