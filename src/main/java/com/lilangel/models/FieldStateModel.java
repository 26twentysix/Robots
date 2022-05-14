package com.lilangel.models;

import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.views.game.enums.GameSpeed;

public interface FieldStateModel extends Model{
    ObjectOnTile[][] getField();
    void selectRobot(int x, int y);
    void setTactSpeed(GameSpeed speed);
}
