package Resource;

public class Water extends Resource {
    public static final Water instance = new Water();
    private Water() {
        super("Water", 2000, 0.05f);
    }
}
