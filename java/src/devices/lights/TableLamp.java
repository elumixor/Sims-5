package devices.lights;

import Resource.Electricity;

public class TableLamp extends Light {
    public TableLamp() {
        super(0.1f);
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(0.75f);
    }
}
