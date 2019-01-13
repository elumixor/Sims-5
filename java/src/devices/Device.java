package devices;

import Animate.Human.Child;
import Animate.Human.Human;
import common.Locatable;
import exceptions.*;
import responsive.Random;
import responsive.events.Event;

/**
 * Devices, inanimate objects, that can be interacted with, might break or malfunction.
 */
public abstract class Device extends Locatable {
    public enum State {
        /**
         * Device is not in use
         */
        IDLE,
        /**
         * Device is in use
         */
        IN_USE,
        /**
         * Device is malfunctioning
         */
        MALFUNCTIONING,
        /**
         * Device is broken and cannot be used until repaired
         */
        BROKEN
    }

    State state = State.IDLE;

    /**
     * Get device {@link Device.State}
     */
    public State getState() {
        return state;
    }

    /**
     * Device repair complexity
     */
    private final float complexity;

    /**
     * Likelihood of breaking
     */
    private final float fragility;

    private final boolean multipleUse;

    /**
     * Constructor
     */
    protected Device(float complexity, float fragility, boolean multipleUse) {
        this.complexity = complexity;
        this.fragility = fragility;
        this.multipleUse = multipleUse;
    }

    protected Device(float complexity, float fragility) {
        this(complexity, fragility, false);
    }

    /**
     * Consume resources
     */
    protected abstract void consume();

    /**
     * Use device
     *
     * @param duration specify usage duration
     */
    protected boolean use(int duration) throws DeviceException {
        if (!multipleUse && state == State.IN_USE) throw new DeviceInUseException();
        if (state == State.MALFUNCTIONING) throw new DeviceMalfunctioningException();
        if (state == State.BROKEN) throw new DeviceBrokenException();
        state = State.IN_USE;
        for (int i = 0; i < duration / 15; i++) consume();

        Event.delayed(e -> this.stopUsing(), 15, 0);
        return true;
    }

    protected boolean use(int ticks, Runnable onSuccess, Runnable onUse,
                          Runnable onDeviceException) {
        try {
            use(ticks * 15);
            onSuccess.run();
            return true;
        } catch (DeviceInUseException e) {
            onUse.run();
        } catch (DeviceException e) {
            onDeviceException.run();
        }
        return false;
    }

    /**
     * Simplified use
     */
    protected boolean use(int ticks, Runnable onSuccess, Runnable onUse,
                          Human user) {
        try {
            use(ticks * 15);
            onSuccess.run();
            for (int i = 1; i < ticks; i++) {
                Event.delayed(event -> onSuccess.run(), i * 15, 0);
            }
            return true;
        } catch (DeviceInUseException e) {
            user.happiness.satisfyBy(-.01f);
            onUse.run();
        } catch (DeviceException e) {
            user.happiness.satisfyBy(-.05f);

            if (user instanceof Child) {
                try {
                    fix(((Child) user).repairSkill);
                } catch (RiskyOperationException ignored) {}
            }
        }
        return false;
    }

    /**
     * Simplified use
     */
    protected boolean use(int minTicks, int maxTicks, Runnable onSuccess, Runnable onUse,
                          Human user) {
        int ticks = Random.nextInt(minTicks, maxTicks);
        return use(ticks, onSuccess, onUse, user);
    }

    /**
     * Stop using device. After it has fulfilled it's function it might just go and break. Just like that.
     */
    private void stopUsing() {
        if (Random.nextFloat() < .05f) {
            System.out.println(getClass().getName() + " has started malfunctioning");
            state = State.MALFUNCTIONING;
        } else if (Random.nextFloat() < .005f) {
            state = State.BROKEN;
            System.out.println(getClass().getName() + " has broken");
        } else state = State.IDLE;
    }

    /**
     * Try to fix the device
     *
     * @param repairSkill Repair skill (from 0 to 1)
     * @return True if repair succeeded, else False
     */
    boolean fix(float repairSkill) throws RiskyOperationException {
        if (state != State.MALFUNCTIONING && state != State.BROKEN) {
            if (Random.nextFloat() < 0.1f) state = State.MALFUNCTIONING;
            else if (Random.nextFloat() < 0.01f) state = State.BROKEN;
            throw new RiskyOperationException("Device was already working fine.");
        }
        if (Random.nextFloat() < (repairSkill + (1.f - complexity * (state == State.MALFUNCTIONING ? 1.3f : 1.6f))) / 2.f) {
            state = State.IDLE;
            return true;
        }
        return false;
    }
}
