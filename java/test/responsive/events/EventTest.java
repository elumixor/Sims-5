package responsive.events;

import org.junit.Test;

public class EventTest {
    @Test
    public void checkEvents() {
    }

    @Test
    public void planned() {
        Event.planned(event -> System.out.println("hello"), 30, 0);

    }

    @Test
    public void delayed() {
    }

    @Test
    public void immediate() {
    }

    @Test
    public void remove() {
    }
}
