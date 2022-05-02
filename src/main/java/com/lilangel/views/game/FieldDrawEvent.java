package com.lilangel.views.game;

import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.views.DrawEvent;

public class FieldDrawEvent extends DrawEvent {
    ObjectOnTile[][] field;

    public ObjectOnTile[][] getField(){
        return field;
    }

    public FieldDrawEvent(Object source, int id, String command, ObjectOnTile[][] field) {
        super(source, id, command);
        this.field = field;
    }
}
