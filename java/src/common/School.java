package common;

public class School extends Outside {
    private static School ourInstance = new School();

    public static School getInstance() {
        return ourInstance;
    }

    private School() {
    }
}
