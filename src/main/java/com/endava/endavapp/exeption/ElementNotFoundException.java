package com.endava.endavapp.exeption;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String departmentNotFound) {
        super(departmentNotFound);
    }
}
