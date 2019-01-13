package house;

import Animate.Animal.Animal;
import Animate.Animate;
import Animate.Human.Human;
import devices.Device;
import devices.alarms.Sensor;
import exceptions.DuplicateElementException;
import exceptions.HouseConfigurationException;
import responsive.Random;
import responsive.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/**
 * House class
 */
public class House {
    /**
     * Occupied addresses
     */
    private static final HashSet<String> houses = new HashSet<>();

    /**
     * House's address
     */
    public final String address;

    /**
     * House's floors
     */
    public final int floors;

    /**
     * Constructor
     *
     * @param floors  Number of floors. Note that floors start from 0 (base floor)
     * @param address House address
     * @throws HouseConfigurationException Throws exception if address is already occupied by another house
     *                                     or if floor count is less than 1
     */
    public House(final String address, final int floors) throws HouseConfigurationException {
        if (!houses.add(this.address = address))
            throw new DuplicateElementException(String.format("There is already a house at %s", address));
        if (floors < 1) throw new HouseConfigurationException("House has to contain at least one floor");
        this.floors = floors;

        // Check sensors
        Event.immediate(event -> sensors.forEach(Sensor::check), 15);
    }

    /**
     * Constructor
     *
     * @param address House address
     * @throws HouseConfigurationException Throws exception if address is already occupied by another house
     */
    public House(final String address) throws HouseConfigurationException {this(address, 0);}

    // House knows what rooms does it have
    public final HashMap<String, Room> rooms = new HashMap<>();

    // House knows what people live in it
    public final ArrayList<Human> people = new ArrayList<>();

    // House knows what animals live in it
    public final ArrayList<Animal> animals = new ArrayList<>();

    // Sensors
    private final ArrayList<Sensor> sensors = new ArrayList<>();

    /**
     * Adds a room to house at a floor. Note that floors start from 0 (base floor)
     *
     * @param name Room name
     * @return Created room
     * @throws HouseConfigurationException Throws exception if room already exists
     */
    public Room addRoom(final String name, int floor) throws HouseConfigurationException {
        if (rooms.containsKey(name))
            throw new DuplicateElementException(String.format("%s already exists in the house", name));
        if (floor < 0 || floor >= floors)
            throw new HouseConfigurationException(String.format("No such floor in the house: %d", floor));

        Room room = new Room(this, floor);
        rooms.put(name, room);
        return room;
    }

    /**
     * Add residents to a house
     */
    public void addResidents(Animate... animates) {
        for (Animate person : animates) {
            person.setHome(this);

            if (person instanceof Animal) {
                Animal animal = (Animal) person;
                animals.add(animal);
            } else if (person instanceof Human) {
                Human human = (Human) person;
                people.add(human);
            }
        }
    }

    /**
     * Register a sensor
     */
    public void addSensor(final Sensor sensor) { sensors.add(sensor); }


    /**
     * Looks for a device in the house
     *
     * @param deviceClass Device class
     */
    public <T extends Device> Optional<T> findDevice(final Class<T> deviceClass) {
        ArrayList<String> rooms = findDeviceLocation(deviceClass);

        if (rooms.size() < 1) return Optional.empty();

        return this.rooms.get(rooms.get(Random.nextInt(rooms.size())))
                .getDevice(deviceClass);
    }

    /**
     * Find where is device located (rooms)
     *
     * @param device Device class
     */
    public ArrayList<String> findDeviceLocation(final Class<? extends Device> device) {
        final ArrayList<String> results = new ArrayList<>();

        rooms.forEach((key, value) -> {
            if (value.containsDevice(device)) {
                results.add(key);
            }
        });

        return results;
    }
}
