package com.gltknbtn.enums;

public enum EventType {
    INOUTLOG(1), MAIL(2), SMS(3);

    private Integer id;
    EventType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
