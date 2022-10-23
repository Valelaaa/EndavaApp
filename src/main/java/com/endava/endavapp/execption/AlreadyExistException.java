package com.endava.endavapp.execption;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String department_already_exists) {
        super(department_already_exists);
    }
}
