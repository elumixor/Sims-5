package devices.Vehicle.Car;

import Resource.Gas;
import devices.Vehicle.Vehicle;

public class Car extends Vehicle {
    public Car() {
        travelSpeed = 200;
    }

    @Override
    protected void consume() {
        Gas.instance.consume(50.f);
    }
}
