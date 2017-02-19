package com.petrol.station.exception;

public class ResolveTypeException extends RuntimeException {
    public static final String message = "Can't convert to known type value:";

    public ResolveTypeException(String value) {
        super(message + value);
    }
}
