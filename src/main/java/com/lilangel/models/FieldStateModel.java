package com.lilangel.models;

import com.lilangel.models.enums.ObjectOnTile;

public interface FieldStateModel extends Model{
    ObjectOnTile[][] getField();
}
