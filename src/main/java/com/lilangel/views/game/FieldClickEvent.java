package com.lilangel.views.game;

import com.lilangel.utils.Pair;

import java.awt.event.ActionEvent;

public class FieldClickEvent extends ActionEvent {
    Pair tile;

    public Pair getTile() {
        return tile;
    }

    public FieldClickEvent(Object source, int id, String command, Pair tile) {
        super(source, id, command);
        this.tile = tile;
    }
}
