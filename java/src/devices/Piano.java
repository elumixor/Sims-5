package devices;

import Animate.Human.Human;
import responsive.events.Event;

public class Piano extends Device implements Satisfier {
    public Piano() {super(1.f, .005f);}

    @Override
    protected void consume() {}

    @Override
    public boolean satisfy(Human player) {
        System.out.printf("%s tries to plays the piano, Happiness: %.0f%%%n", player.name, player.happiness.get() * 100f);
        return use(2, 5,
                () -> player.happiness.satisfyBy(.3f),
                () -> Event.delayed(event -> satisfy(player), 15, 0), player);
    }
}
