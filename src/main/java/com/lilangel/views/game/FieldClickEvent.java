package com.lilangel.views.game;

import com.lilangel.utils.Pair;

import java.awt.*;
import java.awt.event.ActionEvent;

public class FieldClickEvent extends ActionEvent {
    Point tile;

    public Point getTile() {
        return tile;
    }

    public FieldClickEvent(Object source, int id, String command, Point tile) {
        super(source, id, command);
        this.tile = tile;
    }
}
