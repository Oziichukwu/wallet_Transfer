package com.example.transfer_app.web.exception;

public class TransferAppException extends Exception{

    public TransferAppException() {
    }

    public TransferAppException(String message) {
        super(message);
    }

    public TransferAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferAppException(Throwable cause) {
        super(cause);
    }
}
