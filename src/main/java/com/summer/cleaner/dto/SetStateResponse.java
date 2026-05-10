package com.summer.cleaner.dto;

public enum SetStateResponse implements ValidationMessage {
    OK("STATE_OK"),
    NO_WATER("OUT_OF_WATER"),
    NO_SOAP("OUT_OF_SOAP");

    private final String message;

    SetStateResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
