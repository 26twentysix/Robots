package com.lilangel.views.game;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.ReturnCode;
import com.lilangel.views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameSettingsVisualizer extends JPanel implements View {

    private ViewListener listener;

    @Override
    public void notifyListeners(ActionEvent e) {
        listener.onViewEvent(e);
    }

    @Override
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    @Override
    public ReturnCode addDrawEvent(ActionEvent e) {
        return ReturnCode.OK;
    }

}
