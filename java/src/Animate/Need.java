package Animate;

import responsive.Time;
import responsive.events.Event;

/**
 * Alive animate creatures all have their needs. Lack of satisfaction leads to action.
 */
public class Need {
    private static float D_SATISFACTION = 0.5f;
    private static float D_DECAY_RATE = 0.05f;

    /**
     * Satisfaction level. Ranges from 0 to 1. The higher - the better.
     */
    private float satisfaction;

    /**
     * Represents how much does the {@link Need#satisfaction} fall in {@link Time#tick()}.
     */
    private final float decayRate;

    public Need(float initialSatisfaction, float decayRate) {
        satisfaction = initialSatisfaction;
        this.decayRate = decayRate;

        Event.immediate(event -> satisfyBy(-decayRate), 15);
    }

    public float get() {
        return satisfaction;
    }

    /**
     * Add a value to a {@link #satisfaction}. Use negative value to dissatisfy.
     */
    public void satisfyBy(float level) {
        satisfaction += level;
        satisfaction = Math.max(0.f, Math.min(satisfaction, 1.0f)); // clamp
    }

    /**
     * Satisfaction decays with timeAsString automatically
     */
    public void decay() {
        satisfyBy(-decayRate);
    }
}
