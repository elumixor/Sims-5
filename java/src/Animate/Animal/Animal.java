package Animate.Animal;

import Animate.Animate;
import Animate.Human.Baby;
import Animate.Human.Human;
import exceptions.LifeIsHardException;
import house.Room;
import responsive.Random;
import responsive.notifications.AnimalNeedsFoodNotification;

public abstract class Animal extends Animate {
    Animal(Gender gender, String name) {super(gender, name);}

    @Override
    public void reactToNeeds() {
        super.reactToNeeds();

        if (food.get() < 0.3f) {

            Human[] recipients = getHome().people.stream()
                    .filter(human -> human.getLocation() instanceof Room && human.getClass() != Baby.class)
                    .toArray(Human[]::new);
            if (recipients.length < 1) throw new LifeIsHardException("Animals could not survive without humans...");

            recipients[Random.nextInt(recipients.length)].receiveNotification(new AnimalNeedsFoodNotification(this));
        }
    }
}
