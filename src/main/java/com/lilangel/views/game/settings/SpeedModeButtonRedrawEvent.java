package com.lilangel.views.game.settings;

import com.lilangel.views.DrawEvent;
import com.lilangel.views.game.enums.GameSpeed;

public class SpeedModeButtonRedrawEvent extends DrawEvent {
    private GameSpeed mode;

    public GameSpeed getMode() {
        return mode;
    }

    public SpeedModeButtonRedrawEvent(Object source, int id, String command, GameSpeed mode) {
        super(source, id, command);
        this.mode = mode;
    }
}
