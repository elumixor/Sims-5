package Resource;

public class Electricity extends Resource {
    public static final Electricity instance = new Electricity();
    private Electricity() { super("Electricity", 5000, 0.025f); }
}
