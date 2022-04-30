package com.lilangel.models;

import com.lilangel.models.enums.ObjectOnTile;

import java.awt.event.ActionEvent;

public class ModelUpdateEvent extends ActionEvent {

    ObjectOnTile[][] field;

    public ModelUpdateEvent(Object source, int id, String command, ObjectOnTile[][] field) {
        super(source, id, command);
        this.field = field;
    }

    public ObjectOnTile[][] getField(){
        return field;
    }
}
