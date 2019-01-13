package responsive.notifications;

import Animate.Human.Baby;

public class BabyIsHungryNotification extends Notification {
    public BabyIsHungryNotification(final Baby baby) {
        super(baby, String.format("%s needs food", baby.name));
    }
}
