package model.busstops;

import model.busses.Bus;

import java.util.LinkedList;
import java.util.List;

public class BusStop {

    private List<String> bustimes25;
    private List<String> bustimes49;
    private String name;

    public BusStop(String name, List<String> bustimes25, List<String> bustimes49) {
        this.name = name;
        this.bustimes25 = bustimes25;
        this.bustimes49 = bustimes49;
    }

    public BusStop(String name, List<String> bustimes25) {
        this.name = name;
        this.bustimes25 = bustimes25;
    }

    public BusStop(String name) {
        this.name = name;
    }

    private static List<Bus> busses = new LinkedList<>();

    public List<String> getBustimes25() {
        return bustimes25;
    }

    public List<String> getBustimes49() {
        return bustimes49;
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this, bus
    //EFFECTS:  If bus is not already added, adds bus
    public void addBus(Bus bus) {
        if (!busses.contains(bus)) {
            busses.add(bus);
            bus.addBusStop(this);
        }
    }

    public List<Bus> getBusses() {
        return busses;
    }
}
