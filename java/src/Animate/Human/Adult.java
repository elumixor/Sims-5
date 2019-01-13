package Animate.Human;

import Animate.Need;
import Resource.Money;
import devices.Bath;
import devices.Piano;
import devices.Vehicle.Car.Car;
import exceptions.LifeIsHardException;
import responsive.events.Event;
import responsive.notifications.BabyCryingNotification;
import responsive.notifications.BabyIsHungryNotification;
import responsive.notifications.BabyNeedsABathNotification;
import responsive.notifications.Notification;

import java.util.ArrayList;

/**
 * Adult (fully evolved {@link Child})
 * <p>
 * As {@link Child} evolves into an Adult it now can ride a car and have a job, that brings in money.
 */
public class Adult extends Child {
    /**
     * As children grow up they become evil and mercantile!
     * And also provide for the rest of the house.
     */
    public Need money = new Need((Money.instance.getQuantity() / 1000000), 0);

    /**
     * One more vehicle. Upgraded
     */
    public Car car;

    /**
     * Adult with some important parents
     *
     * @param father Important parent #1
     * @param mother Important parent #2
     */
    public Adult(Gender gender, String name, Adult father, Adult mother) {
        super(gender, name, father, mother);
        repairSkill = .7f;
    }

    /**
     * Lower quality adult
     */
    public Adult(Gender gender, String name) {
        this(gender, name, null, null);
    }

    @Override
    public void reactToNeeds() {
        super.reactToNeeds();

        if (happiness.get() < .3f) {
            satisfyWith(Piano.class);
            System.out.printf("Happiness: %.0f%%%n", happiness.get() * 100f);
        }
    }


    @Override
    public void receiveNotification(Notification notification) {
        super.receiveNotification(notification);

        if (notification instanceof BabyCryingNotification) {
            // Baby crying
            Event.immediate(event -> {
                happiness.satisfyBy(-0.75f);
                Baby baby = (Baby) notification.notifier;

                System.out.printf("%s tries to cheer %s up%n", name, baby.name);
                if ((baby).tryToCheerUp(.9f)) {
                    happiness.satisfyBy(0.25f);
                    event.remove();
                }
            }, 15);
        } else if (notification instanceof BabyNeedsABathNotification) {
            // Baby needs a bath
            ArrayList<String> roomsWithBath = getHome().findDeviceLocation(Bath.class);
            if (roomsWithBath.isEmpty()) throw new LifeIsHardException("No Bath in the house!");

            //noinspection OptionalGetWithoutIsPresent
            getHome().rooms.get(roomsWithBath.get(0)).getDevice(Bath.class).get()
                    .washBaby((Baby) notification.notifier, this);
        } else if (notification instanceof BabyIsHungryNotification) {
            // Baby needs food

            Baby baby = (Baby) notification.notifier;

            System.out.printf("%s feeds %s%n", name, baby.name);

            baby.food.satisfyBy(.85f);
            food.satisfyBy(-0.05f);

            baby.happiness.satisfyBy(0.1f);
            happiness.satisfyBy(0.05f);
        }
    }
}
