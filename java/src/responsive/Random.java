package responsive;

/**
 * Random wrapper to prevent multiple initialization and seeding
 */
public class Random {
    private static java.util.Random random = new java.util.Random();
    private Random() {}
    /** {@link java.util.Random#nextInt()} */
    public static int nextInt() { return random.nextInt(); }
    public static int nextInt(int highBound) { return random.nextInt(highBound); }
    public static int nextInt(int min, int max) { return random.nextInt(max - min) + min; }
    /** {@link java.util.Random#nextFloat()} */
    public static float nextFloat() { return random.nextFloat(); }
    public static float nextFloat(float max) { return random.nextFloat() * max; }
    public static float nextFloat(float min, float max) { return random.nextFloat() * (max - min) + min; }
}
