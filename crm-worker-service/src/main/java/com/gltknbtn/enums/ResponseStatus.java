package com.gltknbtn.enums;

public enum ResponseStatus {
    SUCCESS(1), FAIL(0);

    private int status;
    ResponseStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
