package com.endava.endavapp.exeption;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(final String departmentNotFound) {
        super(departmentNotFound);
    }
}
