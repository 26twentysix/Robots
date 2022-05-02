package com.lilangel.views.game;

import com.lilangel.views.game.enums.ButtonClickEvents;

import java.awt.event.ActionEvent;

public class ButtonClickEvent extends ActionEvent {
    ButtonClickEvents event;

    public ButtonClickEvents getEvent() {
        return event;
    }

    public ButtonClickEvent(Object source, int id, String command, ButtonClickEvents event) {
        super(source, id, command);
        this.event = event;
    }
}
