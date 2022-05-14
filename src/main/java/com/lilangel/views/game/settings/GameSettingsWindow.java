package com.lilangel.views.game.settings;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.game.enums.ButtonClickEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GameSettingsWindow extends JInternalFrame {
    private final GameSettingsVisualizer visualizer;

    public GameSettingsWindow() {
        super("Настройки", false, false, false, true);
        this.visualizer = new GameSettingsVisualizer();
        JPanel panel = new JPanel(null);
        panel.add(visualizer);
        getContentPane().add(panel);
        pack();
    }

    public GameSettingsVisualizer getVisualizer() {
        return visualizer;
    }
}
