package com.lmteixeira.personalfinances.usecases.interfaces;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
