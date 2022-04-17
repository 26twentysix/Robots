package com.lilangel.gui;

import com.lilangel.presenter.ViewListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.text.View;

public class GameWindow extends JInternalFrame {
    private final GameVisualizer visualizer;
    private final ViewListener presenter;

    public GameWindow(ViewListener presenter) {
        super("Игровое поле", false, true, false, true);
        this.presenter = presenter;
        this.visualizer = new GameVisualizer(this);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameVisualizer getVisualizer() {
        return visualizer;
    }
}
