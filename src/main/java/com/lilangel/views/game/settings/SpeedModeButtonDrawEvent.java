package com.lilangel.views.game.settings;

import com.lilangel.views.DrawEvent;
import com.lilangel.views.game.enums.GameSpeed;

public class SpeedModeButtonDrawEvent extends DrawEvent {
    private GameSpeed mode;

    public GameSpeed getMode() {
        return mode;
    }

    public SpeedModeButtonDrawEvent(Object source, int id, String command, GameSpeed mode) {
        super(source, id, command);
        this.mode = mode;
    }
}
