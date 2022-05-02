package com.lilangel.views.game;

import com.lilangel.presenters.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsWindow extends JInternalFrame {
    private final ViewListener presenter;
    private final GameSettingsVisualizer visualizer;

    private JButton createButton(ImageIcon defaultIcon, ImageIcon hoverIcon, ImageIcon pressedIcon,
                                 int x, int y, int width, int height, String actionCommand,
                                 ActionListener actionListener) {
        JButton jButton = new JButton();
        jButton.addActionListener(actionListener);
        jButton.setActionCommand(actionCommand);
        jButton.setIcon(defaultIcon);
        jButton.setRolloverIcon(hoverIcon);
        jButton.setPressedIcon(pressedIcon);
        jButton.setBounds(x, y, width, height);
        jButton.setBorderPainted(false);
        return jButton;
    }

    private JButton createSimpleButton(ImageIcon defaultIcon, int x, int y, int width, int height) {
        JButton jButton = new JButton();
        jButton.setIcon(defaultIcon);
        jButton.setBounds(x, y, width, height);
        jButton.setBorderPainted(false);
        return jButton;
    }

    public GameSettingsWindow(ViewListener presenter) {
        super("Настройки", false, false, false, true);
        this.presenter = presenter;
        this.visualizer = new GameSettingsVisualizer();
        JPanel panel = new JPanel(null);
        JButton speedUpButton = createButton(new ImageIcon("src/resources/mc_button_speedup.png"),
                new ImageIcon("src/resources/mc_button_speedup_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speedup_click.png"),
                210, 5, 200, 40, "Speed up", new ButtonClickListener());
        JButton speedDownButton = createButton(new ImageIcon("src/resources/mc_button_speeddown.png"),
                new ImageIcon("src/resources/mc_button_speeddown_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speeddown_click.png"),
                5, 5, 200, 40, "Speed down", new ButtonClickListener());
        JButton pauseButton = createButton(new ImageIcon("src/resources/mc_button_pause.png"),
                new ImageIcon("src/resources/mc_button_pause_mouseover.png"),
                new ImageIcon("src/resources/mc_button_pause_click.png"),
                5, 50, 32, 32, "Pause", new ButtonClickListener());
        JButton playButton = createButton(new ImageIcon("src/resources/mc_button_play.png"),
                new ImageIcon("src/resources/mc_button_play_mouseover.png"),
                new ImageIcon("src/resources/mc_button_play_click.png"),
                5, 50, 32, 32, "Play", new ButtonClickListener());
        playButton.setVisible(false);
        JButton speedInfoButton = createSimpleButton(new ImageIcon("src/resources/game_speed_normal.png"),
                110, 50, 300, 40);
        panel.add(speedInfoButton);
        panel.add(speedUpButton);
        panel.add(speedDownButton);
        panel.add(pauseButton);
        panel.add(playButton);
        panel.add(visualizer);
        getContentPane().add(panel);
        pack();
    }

    public GameSettingsVisualizer getVisualizer() {
        return visualizer;
    }

    class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Speed down" -> System.out.println(1);
                case "Speed up" -> System.out.println(2);
                case "Pause" -> System.out.println(3);
                case "Play" -> System.out.println(4);
            }
        }
    }
}
