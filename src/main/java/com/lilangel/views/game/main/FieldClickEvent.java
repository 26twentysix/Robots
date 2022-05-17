package com.lilangel.views.game.main;

import com.lilangel.utils.Pair;
import com.lilangel.views.ViewEvent;
import com.lilangel.views.game.enums.ButtonClickEvents;

import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FieldClickEvent extends ActionEvent {
    Point tile;

    public FieldClickEvent(Object source, int id, String command, Point p) {
        super(source, id, command);
        this.tile = p;
    }


    public Point getTile() {
        return tile;
    }
}
