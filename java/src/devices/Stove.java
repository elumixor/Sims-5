package devices;

import Animate.Human.Human;
import Resource.Gas;
import responsive.events.Event;

public class Stove extends Device implements Satisfier {
    public Stove() {super(.7f, .1f);}

    @Override
    protected void consume() {
        Gas.instance.consume(8f);
    }

    @Override
    public boolean satisfy(Human human) {
        return use(2,
                () -> human.food.satisfyBy(.4f),
                () -> Event.delayed(event -> satisfy(human), 15, 0), human);
    }
}
