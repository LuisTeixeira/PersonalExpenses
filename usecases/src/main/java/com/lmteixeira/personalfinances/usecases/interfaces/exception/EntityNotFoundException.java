package com.lmteixeira.personalfinances.usecases.interfaces.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
