package exceptions;

public class DeviceBrokenException extends DeviceException {
    public DeviceBrokenException() {
        super("Device is broken");
    }
}
