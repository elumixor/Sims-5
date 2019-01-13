package Resource;

public class Money extends Resource {
    public static final Money instance = new Money();
    private Money() {
        super("Money", 10000, 1);
    }
}
