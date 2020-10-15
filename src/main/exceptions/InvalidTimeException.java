package exceptions;

public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        System.out.println("Invalid Time (Must be HH:MM, 24 Hour Time");
    }
}

