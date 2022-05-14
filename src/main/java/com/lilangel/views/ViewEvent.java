package com.lilangel.views;

import com.lilangel.views.game.enums.ButtonClickEvents;

import java.awt.event.ActionEvent;

public abstract class ViewEvent extends ActionEvent {
    Enum event;

    public Enum getEvent() {
        return event;
    }

    public ViewEvent(ActionEvent e, ButtonClickEvents event){
        super(e.getSource(),e.getID(),e.getActionCommand());
        this.event = event;
    }
}
