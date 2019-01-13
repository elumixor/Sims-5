package responsive.notifications;

public abstract class Notification {
    public final String message;
    public final Object notifier;
    public Notification(final Object notifier, final String message) {
        this.message = message;
        this.notifier = notifier;
    }
}
