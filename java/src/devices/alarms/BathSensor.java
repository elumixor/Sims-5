package devices.alarms;

import Resource.Electricity;
import devices.Bath;
import responsive.notifications.BathOverflowingNotification;

public class BathSensor extends RemoteAlarm implements Sensor {
    private final Bath bath;

    public BathSensor(final Bath bath) {this.bath = bath;}


    @Override
    public void check() {
        if (bath.getState() == State.MALFUNCTIONING) createNotification(new BathOverflowingNotification(bath));
    }

    @Override
    protected void consume() {
        Electricity.instance.consume(10f);
    }
}
