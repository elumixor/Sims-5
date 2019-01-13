package common;

public class Work extends Outside {
    private static Work ourInstance = new Work();

    public static Work getInstance() {
        return ourInstance;
    }

    private Work() {
    }
}
