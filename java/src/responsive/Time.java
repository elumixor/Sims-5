package responsive;

import exceptions.LifeIsHardException;
import responsive.events.Event;

import java.util.Calendar;

/**
 * Class managing running the timeAsString
 */
public final class Time {
    /**
     * Inner calendar
     */
    private static final Calendar calendar;

    /**
     * True if timer runs, false if paused/stopped
     */
    private static boolean runs;

    /**
     * Number of tics per second
     */
    public static int speed;

    /**
     * Thread, responsible for timeAsString incrementation
     */
    private static Thread runThread;

    // Do not allow instantiation
    private Time() {}

    // Init static fields
    static {
        calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JULY, 17, 0, 0, 0);

        runs = false;
        speed = 4;
        runThread = new Thread(() -> {
            while (true) {
                if (runs) {
                    try {
                        tick();
                        Thread.sleep(1000 / speed);
                    } catch (InterruptedException e) {
                        break;
                    } catch (LifeIsHardException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                }
            }
        });
    }

    /**
     * Increment time and process events
     */
    private static void tick() {
        calendar.add(Calendar.MINUTE, 15);
        System.out.println(calendar.getTime()); // VERBOSE
        Event.checkEvents(Time.now());
    }

    /**
     * Start world's time
     */
    public static void startOrResume() {
        if (!runThread.isAlive()) runThread.start();
        runs = true;
    }

    /**
     * Pause the world's time
     */
    public static void pause() {
        runs = false;
    }

    /**
     * Reset time to default
     */
    public static void stop() {
        runThread.interrupt();
    }

    /**
     * Get current day time in minutes
     */
    public static int now() {
        return calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
    }

    /**
     * Get the time of next tick in minutes
     */
    public static int next() {
        return (calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE) + 15) % (24 * 60);
    }
}
