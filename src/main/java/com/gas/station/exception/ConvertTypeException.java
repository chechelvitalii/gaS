package com.gas.station.exception;

public class ConvertTypeException extends RuntimeException {
    private static final String message = "Can't convert to known type value:";
    public ConvertTypeException(String value) {
        super(message+value);
    }
}
