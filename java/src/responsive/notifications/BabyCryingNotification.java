package responsive.notifications;

import Animate.Human.Baby;

public class BabyCryingNotification extends Notification {
    public BabyCryingNotification(final Baby baby) {
        super(baby, String.format("%s is crying!", baby.name));
    }
}
