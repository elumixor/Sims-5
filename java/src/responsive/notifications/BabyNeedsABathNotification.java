package responsive.notifications;

import Animate.Human.Baby;

public class BabyNeedsABathNotification extends Notification {
    public BabyNeedsABathNotification(final Baby baby) {
        super(baby, String.format("%s needs a bath!", baby.name));
    }
}
