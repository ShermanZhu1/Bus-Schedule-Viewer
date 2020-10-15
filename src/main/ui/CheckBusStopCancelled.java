package ui;

import exceptions.BusStopCancelledException;
import network.IsBusStopCancelled;

import java.io.IOException;

public class CheckBusStopCancelled implements AUIobserver {

    private boolean cancelled;

    //REQUIRES: valid busstop
    //EFFECTS: Checks if bus stop is cancelled, if not, prints message
    @Override
    public void update(String busstop) {
        checkBusStopCancelled(busstop);
        if (!cancelled) {
            System.out.println("Bus Stop is currently not cancelled.");
        }
    }

    //REQUIRES: valid busstop
    //MODIFIES: cancelled
    //EFFECTS: checks if bus stop is cancelled
    public void checkBusStopCancelled(String busstop) {
        IsBusStopCancelled ibc1 = new IsBusStopCancelled();
        try {
            if (ibc1.isBusStopCancelled(busstop)) {
                throw new BusStopCancelledException();
            }
        } catch (IOException | BusStopCancelledException e) {
            cancelled = true;
        }

    }
}
