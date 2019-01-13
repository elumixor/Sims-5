package devices;

import Animate.Human.Adult;
import Animate.Human.Baby;
import Animate.Human.Human;
import Resource.Electricity;
import Resource.Water;
import exceptions.RiskyOperationException;
import responsive.events.Event;

public class Bath extends Device implements Satisfier {
    public Bath() {
        super(.35f, .05f);
    }

    /**
     * @param bathTaker
     */
    @Override
    public boolean satisfy(Human bathTaker) {
        return false;
    }

    /**
     * Wash baby
     */
    public void washBaby(Baby baby, Adult washer) {
        use(3, () -> {
            baby.cleanliness.satisfyBy(1.f);
            washer.happiness.satisfyBy(0.025f);
        }, () -> {
            washer.happiness.satisfyBy(-.01f);
            Event.delayed(event -> washBaby(baby, washer), 15, 0);
        }, () -> {
            washer.happiness.satisfyBy(-0.075f);
            baby.happiness.satisfyBy(-0.05f);
            try {
                fix(washer.repairSkill);
                Event.immediate(event -> washBaby(baby, washer), 0);
            } catch (RiskyOperationException ignored) {}
        });
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(10.f);
        Water.instance.consume(5.f);
    }
}
