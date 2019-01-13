package devices;

import Animate.Human.Human;
import Resource.Electricity;
import responsive.events.Event;

public class Refrigerator extends Device implements Satisfier {
    public Refrigerator() {super(.8f, .1f);}

    @Override
    protected void consume() {
        Electricity.instance.consume(7f);
    }

    @Override
    public boolean satisfy(Human human) {
        return use(1,
                () -> human.food.satisfyBy(.3f),
                () -> Event.delayed(event -> satisfy(human), 15, 0), human);
    }
}
