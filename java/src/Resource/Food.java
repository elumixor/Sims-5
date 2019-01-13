package Resource;

public class Food extends Resource {
    public static final Food instance = new Food();
    private Food() { super("Food", 2000, 10); }
}
