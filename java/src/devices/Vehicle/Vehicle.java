package devices.Vehicle;

import Animate.Human.Human;
import common.Location;
import common.Outside;
import devices.Device;
import exceptions.DeviceException;
import responsive.events.Event;

public abstract class Vehicle extends Device {
    protected float travelSpeed;

    public Vehicle() {super(.8f, 0.01f);}

    public void travelTo(final Location to, int travelTime, final Human driver, final Human... passengers) {
        System.out.println("Starting a trip");
        driver.changeLocation(Outside.getInstance());
        for (Human passenger : passengers) {
            passenger.changeLocation(Outside.getInstance());
        }
        try {
            use(travelTime);
        } catch (DeviceException e) {
            e.printStackTrace();
        }

        System.out.println("Destination reached");
        Event.delayed(event -> {
            driver.changeLocation(to);
            for (Human passenger : passengers) {
                passenger.changeLocation(to);
            }
        }, travelTime * (int) Math.floor(100 / travelSpeed), 0);
    }

}
