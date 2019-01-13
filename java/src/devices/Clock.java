package devices;

import Resource.Electricity;

public class Clock extends Device {
    public Clock() {super(.05f, 0.f);}

    @Override
    protected void consume() {
        Electricity.instance.consume(0.5f);
    }
}
