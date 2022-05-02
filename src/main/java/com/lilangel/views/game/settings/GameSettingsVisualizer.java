package com.lilangel.views.game.settings;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.DrawEvent;
import com.lilangel.views.View;
import com.lilangel.views.game.enums.ButtonClickEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GameSettingsVisualizer extends JPanel implements View {

    private ViewListener listener;

    public GameSettingsVisualizer(){
        this.setLayout(null);
        JButton speedUpButton = createButton(new ImageIcon("src/resources/mc_button_speedup.png"),
                new ImageIcon("src/resources/mc_button_speedup_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speedup_click.png"),
                210, 5, 200, 40, ButtonClickEvents.CLICK_ON_GAME_SPEED_UP.command, new ButtonClickListener());
        JButton speedDownButton = createButton(new ImageIcon("src/resources/mc_button_speeddown.png"),
                new ImageIcon("src/resources/mc_button_speeddown_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speeddown_click.png"),
                5, 5, 200, 40, ButtonClickEvents.CLICK_ON_GAME_SPEED_DOWN.command, new ButtonClickListener());

        JButton pauseButton = createButton(new ImageIcon("src/resources/mc_button_pause.png"),
                new ImageIcon("src/resources/mc_button_pause_mouseover.png"),
                new ImageIcon("src/resources/mc_button_pause_click.png"),
                5, 50, 32, 32, ButtonClickEvents.CLICK_ON_PAUSE_BUTTON.command, new ButtonClickListener());
        JButton playButton = createButton(new ImageIcon("src/resources/mc_button_play.png"),
                new ImageIcon("src/resources/mc_button_play_mouseover.png"),
                new ImageIcon("src/resources/mc_button_play_click.png"),
                5, 50, 32, 32, ButtonClickEvents.CLICK_ON_PLAY_BUTTON.command, new ButtonClickListener());
        playButton.setVisible(false);
        JButton speedInfoButton = createSimpleButton(new ImageIcon("src/resources/game_speed_normal.png"),
                110, 50, 300, 40);

        add(speedInfoButton);
        add(speedUpButton);
        add(speedDownButton);
        add(pauseButton);
        add(playButton);
        this.setBounds(0,0,425,127);
    }

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

    Map<String,ButtonClickEvents> eventsMap = new HashMap<>() {{
        put(ButtonClickEvents.CLICK_ON_GAME_SPEED_UP.command, ButtonClickEvents.CLICK_ON_GAME_SPEED_UP);
        put(ButtonClickEvents.CLICK_ON_GAME_SPEED_DOWN.command, ButtonClickEvents.CLICK_ON_GAME_SPEED_DOWN);
        put(ButtonClickEvents.CLICK_ON_PAUSE_BUTTON.command, ButtonClickEvents.CLICK_ON_PAUSE_BUTTON);
        put(ButtonClickEvents.CLICK_ON_PLAY_BUTTON.command, ButtonClickEvents.CLICK_ON_PLAY_BUTTON);
    }};

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (eventsMap.get(e.getActionCommand())) {
                case CLICK_ON_GAME_SPEED_DOWN -> System.out.println(1);
                case CLICK_ON_GAME_SPEED_UP -> System.out.println(2);
                case CLICK_ON_PAUSE_BUTTON -> System.out.println(3);
                case CLICK_ON_PLAY_BUTTON -> System.out.println(4);
            }
        }
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
    public void update(DrawEvent event) {
        onRedrawEvent();
        this.setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        for (var component : this.getComponents())
            component.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.paintComponents(g);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }
}
