package devices;

import Resource.Electricity;
import Resource.Water;

public class Dishwasher extends Device {
    public Dishwasher() {super(.85f, .01f);}

    @Override
    protected void consume() {
        Water.instance.consume(1.f);
        Electricity.instance.consume(5.f);
    }
}
