package com.lilangel.presenters;

import com.lilangel.models.MonitorModel;
import com.lilangel.models.robot.Robot;
import com.lilangel.views.MainApplicationFrame;
import com.lilangel.views.View;
import com.lilangel.views.monitor.RobotMonitorDrawEvent;

import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class MonitorPresenter implements ViewListener, ModelListener {

    MonitorModel model;

    public void setModel(MonitorModel model) {
        this.model = model;
    }

    View view;

    public MonitorPresenter(MainApplicationFrame frame) {
        this.view = frame.getMonitorVisualizer();
        this.view.setListener(this);
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                view.update();
            }
        }, 0, 16);
    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {
        Robot selectedRobot = model.getObservableRobot();
        if (selectedRobot == null)
            view.update(new RobotMonitorDrawEvent(e.getSource(), -1, "null robot", null));
        else
            view.update(new RobotMonitorDrawEvent(e, selectedRobot));
    }

    @Override
    public void onViewEvent(ActionEvent e) {

    }
}
