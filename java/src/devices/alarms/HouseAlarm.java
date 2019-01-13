package devices.alarms;

import Animate.Animate;
import devices.Device;
import house.House;
import house.Room;
import responsive.notifications.Notification;
import responsive.notifiers.Notifier;

public abstract class HouseAlarm extends Device implements Notifier {
    private final House house;

    protected HouseAlarm(House house) {
        super(0.5f, .3f);
        this.house = house;}

    @Override
    public void createNotification(final Notification notification, final Animate... recipients) {
        house.people.forEach(human -> {
            if (human.getLocation() instanceof Room) human.receiveNotification(notification);
        });
    }
}
