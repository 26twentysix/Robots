package com.lilangel.models.enums;

public enum ModelConstants {
    ROBOTS_COUNT(100),
    FIELD_WIDTH(100),
    FIELD_HEIGT(40);


    public final int value;

    ModelConstants(int value) {
        this.value = value;
    }
}
