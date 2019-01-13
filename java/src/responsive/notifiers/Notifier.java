package responsive.notifiers;

import Animate.Animate;
import responsive.notifications.Notification;

public interface Notifier {
    /**
     * Create a notification (named so due to clashing with {@link Object#notify()})
     */
    void createNotification(final Notification notification, final Animate... recipients);
}
