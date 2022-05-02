package com.lilangel.views.monitor;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.game.GameVisualizer;

import javax.swing.*;
import java.awt.*;

public class MonitorWindow extends JInternalFrame {
    private final MonitorVisualizer visualizer;
    private final ViewListener presenter;

    public MonitorVisualizer getVisualizer(){
        return this.visualizer;
    }

    public MonitorWindow(ViewListener presenter){
        super("Состояние робота", false, false, false, true);
        this.presenter = presenter;
        this.visualizer = new MonitorVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
