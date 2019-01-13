package exceptions;

public class DeviceMalfunctioningException extends DeviceException {
    public DeviceMalfunctioningException() {
        super("Device is malfunctioning");
    }
}
