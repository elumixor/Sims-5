package Animate.Human;

import Animate.Animal.Animal;
import Animate.Need;
import Resource.Food;
import devices.*;
import devices.Vehicle.Bicycle.Bicycle;
import responsive.events.Event;
import responsive.notifications.AnimalNeedsFoodNotification;
import responsive.notifications.BabyCryingNotification;
import responsive.notifications.Notification;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Child (evolved {@link Baby})
 * <p>
 * Child now can ride a bicycle and visit school. Still cries sometimes, but not that frequently.
 */
public class Child extends Baby {
    /**
     * Children now need socialization
     */
    public Need socialization = new Need(.5f, 0.05f);

    public float repairSkill;

    public Bicycle bicycle;

    public Child(Gender gender, String name, Adult father, Adult mother) {
        super(gender, name, father, mother);
        repairSkill = .3f;
    }

    @Override
    public void reactToNeeds() {
        super.reactToNeeds();

        if (happiness.get() < 0.3f) {
            // Roll probabilities
            satisfied = false;

            rollSatisfiers(
                    List.of(PersonalComputer.class, Television.class, AudioSystem.class),
                    List.of(.7f, .5f, .3f)).forEach(
                    d -> {
                        if (d != PersonalComputer.class || !(this instanceof Adult)) // PC only for children
                            if (!satisfied) satisfied = satisfyWith(d);
                    });
        }

        if (food.get() < 0.5f) {
            satisfied = false;
            rollSatisfiers(List.of(Stove.class, Refrigerator.class), List.of(.4f, .8f)).forEach(
                    d -> {
                        if (!satisfied) satisfied = satisfyWith(d);
                    }
            );
        }
    }

    @Override
    public void receiveNotification(Notification notification) {
        super.receiveNotification(notification);

        if (notification instanceof BabyCryingNotification && !(this instanceof Adult)) {
            happiness.satisfyBy(-0.1f);

            AtomicBoolean success = new AtomicBoolean(false);

            // Mom 404
            if (!mother.isIn(location)) {
                Event.immediate(event -> {
                    // If in mood, try to do it myself
                    if ((happiness.get() + (1.f - socialization.get())) > 1.f && !success.get()) {
                        success.set(((Baby) notification.notifier).tryToCheerUp(.7f));
                        if (!success.get()) {
                            // Din't work
                            happiness.satisfyBy(-0.1f);
                            socialization.satisfyBy(0.025f);
                        } else {
                            // Succeeded
                            happiness.satisfyBy(0.025f);
                            socialization.satisfyBy(0.025f);
                            event.remove();
                        }
                    } else {
                        // Gave up, calling mom
                        mother.changeLocation(location);
                        // and leaving somewhere safe
                        getHome().rooms.forEach((name, room) -> {if (!isIn(room)) changeLocation(room);});
                        event.remove();
                    }
                }, 15);
            }
        } else if (notification instanceof AnimalNeedsFoodNotification) {
            // Feed animal
            Animal animal = (Animal) notification.notifier;
            System.out.printf("%s feeds %s%n", name, animal.name);

            animal.food.satisfyBy(1.f);
            Food.instance.consume(2.f);

            animal.happiness.satisfyBy(.5f);
            happiness.satisfyBy(-.01f);
        }
    }


}
