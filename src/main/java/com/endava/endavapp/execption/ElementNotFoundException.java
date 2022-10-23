package com.endava.endavapp.execption;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String department_not_found) {
        super(department_not_found);
    }
}
