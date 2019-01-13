package common;

public abstract class Locatable {
    protected Location location;

    public void changeLocation(final Location newLocation) {
        this.location = newLocation;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isIn(final Location location) {
        return this.location == location;
    }
}
