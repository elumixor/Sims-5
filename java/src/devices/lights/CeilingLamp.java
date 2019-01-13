package devices.lights;

import Resource.Electricity;

public class CeilingLamp extends Light {

    public CeilingLamp() {
        super(0.2f);
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(1.f);
    }
}
