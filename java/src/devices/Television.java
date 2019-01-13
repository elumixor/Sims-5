package devices;

import Animate.Human.Human;
import Resource.Electricity;
import Resource.Internet;

public class Television extends Device implements Satisfier {
    public Television() {super(.9f, .05f, true);}

    @Override
    protected void consume() {
        Internet.instance.consume(5.f);
        Electricity.instance.consume(5.f);
    }

    @Override
    public boolean satisfy(Human watcher) {
        System.out.println("Using TV");
        return use(2, 4, () -> watcher.happiness.satisfyBy(.25f),
                () -> {}, watcher);
    }
}
