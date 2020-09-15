package model.busses;

import model.busstops.BusStop;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Bus {

    private String busnum;
    List<BusStop> busstops = new LinkedList<>();

    public Bus(String busnum) {
        this.busnum = busnum;
    }

    public List<BusStop> getBusstops() {
        return busstops;
    }

    public String getBusnum() {
        return busnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bus bus = (Bus) o;
        return busnum.equals(bus.busnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busnum);
    }

    //MODIFIES: this, busstop
    //EFFECTS:  If bus stop is not already added, adds busstop
    public void addBusStop(BusStop busstop) {
        if (busstops == null || !busstops.contains(busstop)) {
            busstops.add(busstop);
            busstop.addBus(this);
        }
    }
}
