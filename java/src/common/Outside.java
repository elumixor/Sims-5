package common;

public class Outside extends Location {
    private static Outside ourInstance = new Outside();

    public static Outside getInstance() {
        return ourInstance;
    }

    Outside() {
    }
}
