package devices.alarms;

import Animate.Animate;
import Animate.Human.Human;
import devices.Device;
import responsive.notifications.Notification;
import responsive.notifiers.Notifier;

import java.util.ArrayList;

/**
 * Alarm that remotely notifies via internet those, who are subscribed, but does not ring any house alarms
 */
public abstract class RemoteAlarm extends Device implements Notifier {
    private final ArrayList<Human> subscribers = new ArrayList<>();

    public RemoteAlarm() {super(0.4f, .3f);}

    @Override
    public void createNotification(final Notification notification, final Animate... recipients) {
        subscribers.forEach(human -> human.receiveNotification(notification));
    }

    void createNotification(final Notification notification) {
        createNotification(notification, subscribers.toArray(new Human[0]));
    }

    public void subscribe(final Human subscriptor) {

    }
}
