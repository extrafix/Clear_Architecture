package com.summer.cleaner.dto;

public enum MoveResponse implements ValidationMessage {
    OK("MOVE_OK"),
    BARRIER("HIT_BARRIER");

    private final String message;

    MoveResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
