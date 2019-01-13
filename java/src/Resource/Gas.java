package Resource;

public class Gas extends Resource {
    public static final Gas instance = new Gas();
    private Gas() { super("Gas", 1000, 0.075f); }
}
