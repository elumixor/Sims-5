package devices.Vehicle;

import Resource.Money;
import common.Outside;

public class PublicTransport extends Vehicle {
    private static PublicTransport ourInstance = new PublicTransport();

    public static PublicTransport getInstance() {
        return ourInstance;
    }

    private PublicTransport() {
        travelSpeed = 100;
        location = Outside.getInstance();
    }

    @Override
    protected void consume() {
        Money.instance.consume(100);
    }
}
