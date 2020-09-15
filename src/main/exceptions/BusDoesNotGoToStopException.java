package exceptions;

public class BusDoesNotGoToStopException extends Exception {
    public BusDoesNotGoToStopException() {
        System.out.println("Bus does not go to this stop");
    }
}
