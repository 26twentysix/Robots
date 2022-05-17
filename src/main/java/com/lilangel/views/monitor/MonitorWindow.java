package com.lilangel.views.monitor;

import javax.swing.*;
import java.awt.*;

public class MonitorWindow extends JInternalFrame {
    private final MonitorVisualizer visualizer;

    public MonitorVisualizer getVisualizer() {
        return this.visualizer;
    }

    public MonitorWindow() {
        super("Состояние робота", false, false, false, true);
        this.visualizer = new MonitorVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
