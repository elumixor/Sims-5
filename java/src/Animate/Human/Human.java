package Animate.Human;

import Animate.Animate;
import Animate.Need;
import devices.Device;
import devices.Satisfier;
import responsive.notifications.Notification;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Sane human creature
 */
public abstract class Human extends Animate {
    /**
     * Humans want to stay clean
     */
    public Need cleanliness = new Need(.5f, 0.05f);

    public Human(Gender gender, String name) {
        super(gender, name);
    }

    /**
     * Humans can get notified about different things and react to those notifications
     *
     * @param notification Notification
     */
    public void receiveNotification(final Notification notification) {
        System.out.printf("%s receives notification: %s\n", name, notification.message);
    }

    /**
     * Satisfy basic need with a satisfier
     * @param deviceClass
     * @param <T>
     * @return
     */
    public <T extends Device & Satisfier> boolean satisfyWith(Class<T> deviceClass) {
        AtomicBoolean satisfied = new AtomicBoolean(false);

        getHome().findDevice(deviceClass).ifPresent(device -> satisfied.set(device.satisfy(this)));

        return satisfied.get();
    }
}
