package com.lilangel.views.game;

import com.lilangel.presenters.ViewListener;

import javax.swing.*;
import java.awt.*;

public class GameSettingsWindow extends JInternalFrame {
    private final ViewListener presenter;
    private final GameSettingsVisualizer visualizer;

    public GameSettingsWindow(ViewListener presenter) {
        super("Настройки", false, false, false, true);
        this.presenter = presenter;
        this.visualizer = new GameSettingsVisualizer();
        JPanel panel = new JPanel(null);
        JButton jButton = new JButton();
        jButton.setIcon(new ImageIcon("src/resources/mc_button_speeddown.png"));
        jButton.setBounds(5,5,200,40);
        JButton jButton1 = new JButton();
        jButton1.setIcon(new ImageIcon("src/resources/mc_button_speedup.png"));
        jButton1.setBounds(210, 5,200, 40);
        panel.add(jButton);
        panel.add(jButton1);
        panel.add(visualizer);
        getContentPane().add(panel);
        pack();
    }

    public GameSettingsVisualizer getVisualizer() {
        return visualizer;
    }
}
