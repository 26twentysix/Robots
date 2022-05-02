package com.lilangel.models.enums;

/**
 * Enum for objects that can be placed on battleground
 */
public enum ObjectOnTile {
    EMPTY,
    ENERGY,
    ROBOT,
    WALL;

    @Override
    public String toString(){
        return switch (this) {
            case EMPTY -> ".";
            case ENERGY -> "$";
            case WALL -> "#";
            case ROBOT -> "*";
        };
    }
}
