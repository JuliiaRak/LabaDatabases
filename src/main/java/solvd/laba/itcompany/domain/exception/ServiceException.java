package solvd.laba.itcompany.domain.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
