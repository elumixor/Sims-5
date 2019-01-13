package devices;

import Animate.Human.Human;
import Resource.Electricity;

public class AudioSystem extends Device implements Satisfier {
    public AudioSystem() {super(.4f, .1f);}

    @Override
    protected void consume() {
        Electricity.instance.consume(10.f);
    }

    @Override
    public boolean satisfy(Human player) {
        return false;
    }
}
