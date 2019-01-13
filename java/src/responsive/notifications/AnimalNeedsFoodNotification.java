package responsive.notifications;

import Animate.Animal.Animal;

public class AnimalNeedsFoodNotification extends Notification {
    public AnimalNeedsFoodNotification(Animal animal) {
        super(animal, String.format("%s needs food!", animal.name));
    }
}
