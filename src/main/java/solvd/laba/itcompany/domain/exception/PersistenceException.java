package solvd.laba.itcompany.domain.exception;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, Exception cause) {
        super(message, cause);
    }
}
