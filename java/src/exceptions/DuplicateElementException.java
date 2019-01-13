package exceptions;

public class DuplicateElementException extends HouseConfigurationException {
    public DuplicateElementException(String errorMessage) {
        super(errorMessage);
    }
}
