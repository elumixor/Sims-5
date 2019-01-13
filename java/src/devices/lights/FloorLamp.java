package devices.lights;

import Resource.Electricity;

public class FloorLamp extends Light {
    public FloorLamp() {
        super(0.15f);
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(1.f    );
    }
}
