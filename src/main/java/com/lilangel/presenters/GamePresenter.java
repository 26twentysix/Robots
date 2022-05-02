package com.lilangel.presenters;

import com.lilangel.models.FieldStateModel;
import com.lilangel.views.ReturnCode;
import com.lilangel.views.View;
import com.lilangel.views.game.FieldDrawEvent;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * MVP Presenter component, responsible for updating View and giving tasks for Model
 */
public class GamePresenter implements ModelListener, ViewListener {
    FieldStateModel model;

    public void setModel(FieldStateModel model) {
        this.model = model;
    }

    View view;

    public void setView(View view) {
        this.view = view;
        view.setListener(this);
    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {
        if (e == null || "no".equals(e.getActionCommand())) return;

        FieldDrawEvent event = new FieldDrawEvent(e.getSource(),0,"redraw idk", model.getField());
        ReturnCode code = view.addDrawEvent(event);

        while (code == ReturnCode.QUEUE_IS_FULL){
            try {
                Thread.sleep(10);
                code = view.addDrawEvent(event);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onViewEvent(ActionEvent e) {
        //TODO вот тут должен быть диспатчер
    }
}
