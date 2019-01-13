package responsive.notifications;

import devices.Stove;

public class FoodBurningNotification extends Notification {
    public FoodBurningNotification(final Stove stove) {
        super(stove, "Food is burning");
    }
}
