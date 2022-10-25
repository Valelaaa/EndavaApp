package com.endava.endavapp.exeption;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(final String departmentAlreadyExists) {
        super(departmentAlreadyExists);
    }
}
