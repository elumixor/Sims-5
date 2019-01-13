package Resource;

public class Internet extends Resource {
    public static final Internet instance = new Internet();
    private Internet() {
        super("Internet", 5000, 0.025f);
    }
}
