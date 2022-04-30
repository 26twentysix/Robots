package com.lilangel.views.game;

import com.lilangel.models.enums.ObjectOnTile;

import java.awt.event.ActionEvent;

public class FieldDrawEvent extends ActionEvent {
    ObjectOnTile[][] field;

    public ObjectOnTile[][] getField(){
        return field;
    }

    public FieldDrawEvent(Object source, int id, String command, ObjectOnTile[][] field) {
        super(source, id, command);
        this.field = field;
    }


}
