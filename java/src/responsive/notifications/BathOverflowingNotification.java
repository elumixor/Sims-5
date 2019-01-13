package responsive.notifications;

import devices.Bath;

public class BathOverflowingNotification extends Notification {
    public BathOverflowingNotification(final Bath bath) {
        super(bath, "Bath is overflowing");
    }
}
