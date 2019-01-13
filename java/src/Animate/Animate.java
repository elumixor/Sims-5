package Animate;

import common.Locatable;
import devices.Device;
import devices.Satisfier;
import exceptions.LifeIsHardException;
import house.House;
import javafx.util.Pair;
import responsive.Random;
import responsive.events.Event;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Alive animate entity
 */
public abstract class Animate extends Locatable {
    /**
     * Level of happiness
     */
    public Need happiness = new Need(Random.nextFloat(4.f, 1.f), .025f);

    /**
     * Level of food requirement
     */
    public Need food = new Need(Random.nextFloat(4.f, 1.f), .05f);

    /**
     * Gender. Rather strict
     */
    public enum Gender {MALE, FEMALE}

    /**
     * Either it's {@link Gender#MALE} or {@link Gender#FEMALE}
     */
    public final Gender gender;

    /**
     * Animate's name
     */
    public final String name;

    /**
     * House where the animate lives. Not everyone is so lucky to have a home though :(
     */
    private House home = null;

    /**
     * Gift animate a home (or move it to another one)
     *
     * @param home New home
     */
    public void setHome(House home) { this.home = home; }

    /**
     * Where the animate lives
     */
    public House getHome() {return home;}

    /**
     * Represents if an animate is occupied with some activity
     */
    private boolean occupied = false;

    /**
     * Occupy with activity
     *
     * @param duration How long it will be occupied
     * @return False if animate was already occupied, else true
     */
    public boolean occupy(int duration) {
        if (occupied) return false;
        Event.delayed(event -> occupied = false, duration, 0);
        return occupied = true;
    }

    /**
     * Create a new Animate
     *
     * @param gender {@link Gender#MALE} or {@link Gender#FEMALE}
     */
    protected Animate(Gender gender, String name) {
        this.gender = gender;
        this.name = name;

        Event.delayed(event -> this.reactToNeeds(), 15, 15);
    }

    /**
     * Flag if already satisfied to needs
     */
    protected boolean satisfied = false;

    /**
     * Perform required actions based upon current need level
     */
    public void reactToNeeds() {
        satisfied = false;

        if (happiness.get() <= 0.f)
            throw new LifeIsHardException(String.format("%s has gotten really sad about everything and died.", name));
        if (food.get() <= 0.f) throw new LifeIsHardException(String.format("%s has starved to death.", name));

        float fd = food.get();
        if (fd < .3f) {
            System.out.printf("%s is hungry%n", name);
            if (fd < .15) {
                System.out.printf("%s is starving%n", name);
            }
        }
        happiness.satisfyBy(fd < 0.15f ? -0.1f :
                fd < 0.3f ? -0.05f : 0.f);

        float hp = happiness.get();
        if (hp < .3f) {
            System.out.printf("%s is sad%n", name);
            if (hp < .15) {
                System.out.printf("%s is depressed%n", name);
            }
        }
    }

    /**
     * Randomly decide the priority of satisfiers
     */
    protected static <T extends Device & Satisfier> ArrayList<Class<? extends T>> rollSatisfiers(
            List<Class<? extends T>> classes, List<Float> probabilities) {

        if (classes.size() != probabilities.size()) throw new IllegalArgumentException("List are not the same size");

        ArrayList<Pair<Integer, Float>> indices = new ArrayList<>();

        for (int i = 0; i < probabilities.size(); i++) {
            indices.add(new Pair<>(i, Random.nextFloat(probabilities.get(i))));
        }

        indices.sort(Comparator.comparing(Pair::getValue));

        ArrayList<Class<? extends T>> results = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            results.add(classes.get(indices.get(i).getKey()));
        }

        return results;
    }
}
