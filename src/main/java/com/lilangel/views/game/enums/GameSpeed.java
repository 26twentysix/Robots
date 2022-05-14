package com.lilangel.views.game.enums;

public enum GameSpeed {
    VERY_SLOW(10),
    SLOW(2),
    NORMAL(1),
    FAST(0.5),
    VERY_FAST(0.1),
    PAUSE(Double.MAX_VALUE);

    public final double value;

    GameSpeed(double value) {
        this.value = value;
    }
}
