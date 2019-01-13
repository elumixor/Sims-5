package devices;

import Animate.Human.Human;

public class Toy extends Device implements Satisfier {

    public Toy() {super(0.f, .8f);}

    @Override
    protected void consume() {}

    @Override
    public boolean satisfy(Human human) {
        System.out.println(human.name + " is playing with the toy");
        human.happiness.satisfyBy(.3f);
        return false;
    }
}
