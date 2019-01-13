package exceptions;

public class DeviceInUseException extends DeviceException {
    public DeviceInUseException() {
        super("Device is in use");
    }
}
