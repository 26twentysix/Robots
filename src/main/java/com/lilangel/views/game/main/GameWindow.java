package com.lilangel.views.game.main;

import com.lilangel.presenters.ViewListener;

import java.awt.BorderLayout;

import javax.swing.*;

public class GameWindow extends JInternalFrame {
    private final GameVisualizer visualizer;

    public GameWindow() {
        super("Игровое поле", false, false, false, true);
        this.visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public GameVisualizer getVisualizer() {
        return visualizer;
    }
}
