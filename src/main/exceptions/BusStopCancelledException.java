package exceptions;

public class BusStopCancelledException extends Throwable {
    public BusStopCancelledException() {
        System.out.println("Bus Stop Cancelled");
    }
}
