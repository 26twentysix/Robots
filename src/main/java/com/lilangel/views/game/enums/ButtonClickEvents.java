package com.lilangel.views.game.enums;

public enum ButtonClickEvents {
    CLICK_ON_PAUSE_BUTTON("Pause"),
    CLICK_ON_PLAY_BUTTON("Play"),
    CLICK_ON_GAME_SPEED_UP("Speed up"),
    CLICK_ON_GAME_SPEED_DOWN("Speed down"),
    CLICK_ON_EVOLUTION_TRIGGER("TriggerEvolution");

    public final String command;

    ButtonClickEvents(String command){
        this.command = command;
    }
}
