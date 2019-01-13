import Animate.Animal.Cat;
import Animate.Animal.Dog;
import Animate.Human.Adult;
import Animate.Human.Baby;
import Animate.Human.Child;
import devices.*;
import devices.Vehicle.Bicycle.Bicycle;
import devices.Vehicle.Car.Car;
import devices.alarms.BathSensor;
import exceptions.HouseConfigurationException;
import house.House;
import house.Room;
import responsive.Time;
import responsive.events.Event;

import static Animate.Animate.Gender.FEMALE;
import static Animate.Animate.Gender.MALE;

public class Main {
    public static void main(String[] args) throws HouseConfigurationException, InterruptedException {
        House house = new House("City, Address st. 4", 3);

        // Base floor
        Room entrance = house.addRoom("entrance", 0);
        Room bathroom = house.addRoom("bathroom", 0);

        Room livingRoom = house.addRoom("livingRoom", 0);
        Room kitchen = house.addRoom("kitchen", 0);
        Room garage = house.addRoom("garage", 0);

        // First floor
        Room marks = house.addRoom("mark's", 1);
        Room helens = house.addRoom("helen's", 1);

        // Second floor
        Room bedroom = house.addRoom("bedroom", 2);
        Room tommys = house.addRoom("tommy's", 2);

        // Animals
        Cat tiger = new Cat(MALE, "Tiger");
        Cat misty = new Cat(FEMALE, "Misty");
        Dog lucy = new Dog(FEMALE, "lucy");

        // People
        Adult john = new Adult(MALE, "John");
        Adult julien = new Adult(FEMALE, "Julien");
        Child helen = new Child(FEMALE, "Helen", john, julien);
        Child mark = new Child(MALE, "Mark", john, julien);
        Baby tommy = new Baby(MALE, "Tommy", john, julien);

        // Add residents
        house.addResidents(john, julien, helen, mark, tommy, tiger, misty, lucy);

        // Set locations
        john.changeLocation(bedroom);
        julien.changeLocation(tommys);

        helen.changeLocation(helens);
        mark.changeLocation(marks);

        tommy.changeLocation(tommys);

        tiger.changeLocation(livingRoom);
        misty.changeLocation(kitchen);
        lucy.changeLocation(livingRoom);


        // Living room
        {
            livingRoom.addDevice("Piano", new Piano());
            livingRoom.addDevice("TV", new Television());
            livingRoom.addDevice("PC", new PersonalComputer());
        }

        // Kitchen
        {
            kitchen.addDevice("Refrigerator", new Refrigerator());
            kitchen.addDevice("Stove", new Stove());
        }

        // Garage
        {
            Car car;
            garage.addDevice("car", car = new Car());
            john.car = car;

            Bicycle helensBike;
            Bicycle marksBike;

            garage.addDevice("helen's bike", helensBike = new Bicycle());
            garage.addDevice("mark's bike", marksBike = new Bicycle());
            helen.bicycle = helensBike;
            mark.bicycle = marksBike;
        }

        // Bathroom
        {
            Bath bath;
            bathroom.addDevice("bath", bath = new Bath());
            BathSensor bathSensor;
            bathroom.addDevice("bathSensor", bathSensor = new BathSensor(bath));
            house.addSensor(bathSensor);
            bathSensor.subscribe(john);
        }

        // Helen's room
        {

        }

        // Start the universe and see who dies first
        Time.speed = 8;
        Time.startOrResume();
        Event.planned(e -> Time.stop(),23 * 60 + 30, 0);
    }
}

