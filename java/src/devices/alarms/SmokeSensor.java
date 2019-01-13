package devices.alarms;

import Resource.Electricity;
import devices.Stove;
import house.House;
import responsive.notifications.FoodBurningNotification;

public class SmokeSensor extends HouseAlarm implements Sensor {
    private final Stove stove;

    protected SmokeSensor(House house, Stove stove) {
        super(house);
        this.stove = stove;
    }

    @Override
    public void check() {
        if (stove.getState() == State.MALFUNCTIONING) createNotification(new FoodBurningNotification(stove));
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(5f);
    }
}
