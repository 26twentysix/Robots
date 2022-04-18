package com.lilangel.model;

public enum ModelConstants {
    ROBOTS_COUNT(100),
    FIELD_WIDTH(300),
    FIELD_HEIGT(200);


    final int value;

    ModelConstants(int value) {
        this.value = value;
    }
}
