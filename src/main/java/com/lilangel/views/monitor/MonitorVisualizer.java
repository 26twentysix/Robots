package com.lilangel.views.monitor;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.DrawEvent;
import com.lilangel.views.View;
import com.lilangel.views.monitor.drawer.MonitorDrawer;
import com.lilangel.views.monitor.drawer.NoRobotDrawingScheme;
import com.lilangel.views.monitor.drawer.NormalDrawingScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MonitorVisualizer extends JPanel implements View {

    private ViewListener listener;

    private MonitorDrawer drawer;

    public MonitorVisualizer() {
        this.drawer = new MonitorDrawer();
        this.setDoubleBuffered(true);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawer.draw(g2d);
    }

    @Override
    public void notifyListeners(ActionEvent e) {
        listener.onViewEvent(e);
    }

    @Override
    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void update() {
        //ignored
    }

    @Override
    public void update(DrawEvent e) {
        if (e.getID() == -1)
            drawer.setScheme(new NoRobotDrawingScheme());
        else {
            if (e instanceof RobotMonitorDrawEvent event) {
                drawer.setScheme(new NormalDrawingScheme());
                drawer.setRobot(event.robot);
            }
        }
        onRedrawEvent();
    }
}
