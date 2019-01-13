package devices;


import Animate.Human.Human;
import Resource.Electricity;
import Resource.Internet;
import responsive.Random;
import responsive.events.Event;

public class PersonalComputer extends Device implements Satisfier {
    public PersonalComputer() { super(.75f, .1f); }

    @Override
    protected void consume() {
        Electricity.instance.consume(15f);
        Internet.instance.consume(10f);
    }

    @Override
    public boolean satisfy(Human player) {
        System.out.println("Using PC");
        int tries = 0;
        int currentTry = 0;
        for (; currentTry < 4; currentTry++, tries++)
            if (Random.nextInt(2) != 1)
                currentTry++;

        int finalCurrentTry = currentTry;
        int finalTries = tries;
        return use(tries,
                () -> player.happiness.satisfyBy((4 - (finalCurrentTry - finalTries)) / 8.f),
                () -> Event.delayed(event -> satisfy(player), 15, 0), player);

    }
}
