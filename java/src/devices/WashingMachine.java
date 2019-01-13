package devices;

import Animate.Human.Human;
import Resource.Electricity;
import Resource.Water;
import exceptions.DeviceException;

public class WashingMachine extends Device {


    public WashingMachine() {super(.8f, .05f);}

    @Override
    protected void consume() {

    }

    enum WashingProgram {
        Fast(30), Medium(60), Slow(90);

        private int duration;

        public int getDuration() {
            return duration;
        }

        WashingProgram(int duration) {
            this.duration = duration;
        }
    }

    /**
     * Washes clothes, consumes electricity and water
     *
     * @param program
     *         Washing program
     */
    void Wash(WashingProgram program, Human user) {
        try {
            use(program.duration);
        } catch (DeviceException e) {
            e.printStackTrace();
        }

        // While working, consume resources
        Water.instance.consume(program.duration * 0.5f);
        Electricity.instance.consume(program.duration * 0.3f);
    }
}
