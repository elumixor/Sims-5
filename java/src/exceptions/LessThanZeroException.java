package exceptions;

public class LessThanZeroException extends BusinessException {
    public LessThanZeroException(String name) {
        super(String.format("%s cannot be less than zero", name));
    }
    public LessThanZeroException() {
        this("Value");
    }
    public LessThanZeroException(String name, int value) {
        super(String.format("%s cannot be less than zero. %d was provided", name, value));
    }
    public LessThanZeroException(String name, float value) {
        super(String.format("%s cannot be less than zero. %f was provided", name, value));
    }
    public LessThanZeroException(String name, double value) {
        super(String.format("%s cannot be less than zero. %f was provided", name, value));
    }
}