package com.lilangel.views.game;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.game.GameVisualizer;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame {
    private final GameVisualizer visualizer;
    private final ViewListener presenter;

    public GameWindow(ViewListener presenter) {
        super("Игровое поле", false, false, false, true);
        this.presenter = presenter;
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
