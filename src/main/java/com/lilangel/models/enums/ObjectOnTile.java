package com.lilangel.models.enums;

/**
 * Enum for objects that can be placed on battleground
 */
public enum ObjectOnTile {
    EMPTY,
    ENERGY,
    ROBOT,
    WALL,
    ROBOT_HP_DEAD,
    ROBOT_ENERGY_DEAD,
    ROBOT_VERTICAL_TRAIL,
    ROBOT_HORIZONTAL_TRAIL;


    @Override
    public String toString(){
        return switch (this) {
            case EMPTY -> ".";
            case ENERGY -> "$";
            case WALL -> "#";
            case ROBOT -> "*";
            case ROBOT_ENERGY_DEAD -> "o";
            case ROBOT_HP_DEAD -> "x";
            case ROBOT_VERTICAL_TRAIL -> "|";
            case ROBOT_HORIZONTAL_TRAIL -> "-";
        };
    }
}
