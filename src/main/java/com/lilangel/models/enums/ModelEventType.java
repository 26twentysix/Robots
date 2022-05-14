package com.lilangel.models.enums;

public enum ModelEventType {
    TACT_FINISHED("tact finished"),
    CYCLE_FINISHED("cycle finished"),
    ROBOT_SELECTED("robot selected");

    public final String value;

    private ModelEventType(String value){
        this.value = value;
    }
}
