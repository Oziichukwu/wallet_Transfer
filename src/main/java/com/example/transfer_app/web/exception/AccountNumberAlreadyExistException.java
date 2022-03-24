package com.example.transfer_app.web.exception;

public class AccountNumberAlreadyExistException extends TransferAppException {

    public AccountNumberAlreadyExistException() {
    }

    public AccountNumberAlreadyExistException(String message) {
        super(message);
    }

    public AccountNumberAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNumberAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
