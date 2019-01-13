package responsive.events;


import responsive.Time;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Event class. As timeAsString flows, events happen. Event class manages events: plan events, trigger callbacks.
 * Every {@link Time#tick()} runs a check on all events if they should occur or not.
 * <p>
 * With regards to current {@link Time} system, events may repeat. Such events require repetition interval.
 * If repetition interval equals 0, then event does not repeat.
 */
public class Event {
    /**
     * Time when event should first occur in minutes from 00:00.
     */
    private int time;

    /**
     * Interval in which does the event recur. 0 means no repetition.
     */
    private final int repetitionInterval;

    /**
     * Action that runs when event is triggered.
     */
    private final Consumer<Event> callback;

    /**
     * Planned events (these are processed every tick)
     */
    private static final ArrayList<Event> events = new ArrayList<>();

    /**
     * New events to be added in this tick and not yet processed
     * (done so to avoid {@link ConcurrentModificationException}
     */
    private static ArrayList<Event> nextEvents = new ArrayList<>();

    /**
     * Events to be removed
     */
    private static ArrayList<Event> nextRemove = new ArrayList<>();

    /**
     * Check all events on this timeAsString. Triggers all events, happening in interval [now, now + 15 min)
     *
     * @param time Time in minutes from 00:00
     */
    public static void checkEvents(final int time) {
        Iterator<Event> it = events.iterator();

        while (it.hasNext()) {
            Event event = it.next();

            if (event.time >= time - 15 && event.time < time) {
                event.callback.accept(event);

                if (event.repetitionInterval == 0)
                    it.remove();
                else {
                    event.time += event.repetitionInterval;
                    event.time %= (24 * 60);
                }
            }
        }

        events.addAll(nextEvents);
        nextEvents = new ArrayList<>();
        nextRemove.forEach(events::remove);
    }

    /**
     * Event constructor
     */
    private Event(Consumer<Event> callback, int time, int repetitionInterval) {
        if (repetitionInterval != 0 && repetitionInterval < 15)
            throw new IllegalAccessError("Repetition interval has to be at least 15 minutes");
        if (callback == null) throw new IllegalArgumentException("Callback is required for event");

        this.time = time;
        this.repetitionInterval = repetitionInterval;
        this.callback = callback;

        nextEvents.add(this);
    }

    /**
     * Create new {@link Event}, occurring ASAP
     *
     * @param callback           Action that runs when event is triggered
     * @param minutes            Delay from current timeAsString
     * @param repetitionInterval Interval in which does the event recur. 0 means no repetition.
     * @return Created event
     */
    public static Event planned(Consumer<Event> callback, int minutes, int repetitionInterval) {
        if (minutes >= 24 * 60) return null;
        if (minutes >= Time.now() && (minutes <= Time.next() || Time.next() < Time.now())) minutes = Time.next();
        return new Event(callback, minutes, repetitionInterval);
    }

    /**
     * Create new {@link Event}, delayed from current timeAsString.
     *
     * @param callback           Action that runs when event is triggered
     * @param minutes            Delay from current timeAsString
     * @param repetitionInterval Interval in which does the event recur. 0 means no repetition.
     * @return Created event
     */
    public static Event delayed(Consumer<Event> callback, int minutes, int repetitionInterval) {
        if (minutes < 0) return null;
        return planned(callback, (Time.now() + minutes) % (24 * 60), repetitionInterval);
    }

    /**
     * Create new {@link Event}, occurring ASAP
     *
     * @param callback           Action that runs when event is triggered.
     * @param repetitionInterval Interval in which does the event recur. 0 means no repetition.
     * @return Created event
     */
    public static Event immediate(Consumer<Event> callback, int repetitionInterval) {
        Event event = new Event(callback, Time.next(), repetitionInterval);
        event.callback.accept(event);
        if (repetitionInterval < 15) nextEvents.remove(event);
        return event;
    }

    /**
     * Remove the event
     */
    public void remove() {
        nextRemove.add(this);
    }
}
