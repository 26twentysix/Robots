package com.lilangel.models.robot;

public record Coordinates(int xPos, int yPos) {
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else{
            Coordinates another = (Coordinates) obj;
            return this.xPos == another.xPos && this.yPos == another.yPos;
        }
    }

    @Override
    public int hashCode() {
        return 17*17*xPos + 17*yPos;
    }
}
