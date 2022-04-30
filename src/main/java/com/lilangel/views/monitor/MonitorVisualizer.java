package com.lilangel.views.monitor;

import com.lilangel.views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MonitorVisualizer extends JPanel implements View {

    @Override
    public void notifyPresenter(ActionEvent e) {

    }

    @Override
    public int addDrawEvent(ActionEvent e) {
        return 0;
    }
}
