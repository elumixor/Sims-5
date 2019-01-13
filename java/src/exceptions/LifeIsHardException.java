package exceptions;

public class LifeIsHardException extends RuntimeException {
    public LifeIsHardException(String errorMessage) {
        super("Life is hard. It's gotten to a point when it could not continue anymore.\n"
              + "Some say it was due to: " + errorMessage);
    }
}
