package house;

import common.Location;
import devices.Device;
import exceptions.DuplicateElementException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Room class
 */
public class Room extends Location {
    /**
     * The house, the room is located in
     */
    public final House house;

    /**
     * The floor, the room is located at
     */
    public final int floor;

    private final HashMap<String, Device> devices = new HashMap<>();

    /**
     * Constructor
     */
    Room(House house, int floor) {
        this.house = house;
        this.floor = floor;
    }

    /**
     * Check if room contains device
     *
     * @param name Device name
     * @return true if room contains device, else false
     */
    public boolean containsDevice(final String name) {
        return devices.containsKey(name);
    }

    /**
     * Check if room contains device
     *
     * @param device Device
     * @return true if room contains device, else false
     */
    public boolean containsDevice(final Device device) {
        return devices.containsValue(device);
    }

    /**
     * Check if room contains device
     *
     * @param device Device
     * @return true if room contains device, else false
     */
    public boolean containsDevice(final Class<? extends Device> device) {
        for (Map.Entry<String, Device> entry : devices.entrySet()) {
            Device value = entry.getValue();
            if (value.getClass() == device) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get device from it's name
     *
     * @param name Device name
     */
    public Optional<Device> getDevice(final String name) {
        return Optional.ofNullable(devices.get(name));
    }

    /**
     * Check if room contains device
     *
     * @param device Device
     * @return true if room contains device, else false
     */
    public <T extends Device> Optional<T> getDevice(final Class<T> device) {
        for (Map.Entry<String, Device> entry : devices.entrySet()) {
            Device value = entry.getValue();
            if (value.getClass() == device) {
                return Optional.of((T) value);
            }
        }
        return Optional.empty();
    }

    /**
     * Add device to a room
     *
     * @param name   Identifier
     * @param device Device
     * @throws DuplicateElementException Throws exception if device with such name is already in the room
     */
    public void addDevice(final String name, final Device device) throws DuplicateElementException {
        if (containsDevice(name)) throw new DuplicateElementException(String.format("%s is already in the room", name));
        devices.put(name, device);
    }
}
