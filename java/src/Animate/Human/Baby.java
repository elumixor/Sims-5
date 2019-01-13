package Animate.Human;

import devices.Toy;
import responsive.Random;
import responsive.notifications.BabyCryingNotification;
import responsive.notifications.BabyIsHungryNotification;
import responsive.notifications.BabyNeedsABathNotification;

/**
 * Baby ({@link Human} starting stage)
 * <br>
 * Doesn't do much, has basic human needs.
 */
public class Baby extends Human {
    /**
     * Everyone was born by someone else.
     * Presumably...
     */
    final Adult father, mother;

    /**
     * New baby instance
     */
    public Baby(Gender gender, String name, Adult father, Adult mother) {
        super(gender, name);
        this.father = father;
        this.mother = mother;
    }

    /**
     * Baby humbly notifies everyone in this room about its needs
     */
    @Override
    public void reactToNeeds() {
        super.reactToNeeds();

        float cl = cleanliness.get();

        // Like to stay clean
        happiness.satisfyBy(cl < 0.1f ? -0.1f :
                cl < 0.25f ? -0.05f : 0.f);

        // Only for babies
        if (!(this instanceof Child)) {
            if (cl < .25f) {
                getHome().people.forEach(human -> {
                    if (human.isIn(location) && human.getClass() == Adult.class)
                        human.receiveNotification(new BabyNeedsABathNotification(this));
                });
            }

            if (happiness.get() < .4f) {
                if (!satisfyWith(Toy.class)) {
                    getHome().people.forEach(human -> {
                        if (human.isIn(location) && human.getClass() != Baby.class)
                            human.receiveNotification(new BabyCryingNotification(this));
                    });
                }
            }

            if (food.get() < .3f) {
                mother.receiveNotification(new BabyIsHungryNotification(this));
            }
        }
    }

    /**
     * Try to cheer child up
     *
     * @param successProbability Probability of success. From 0.0f to 1.0f
     */
    boolean tryToCheerUp(float successProbability) {
        boolean success = Random.nextFloat() <= successProbability;

        if (success) happiness.satisfyBy(1.f);

        return success;
    }
}
