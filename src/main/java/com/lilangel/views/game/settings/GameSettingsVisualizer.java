package com.lilangel.views.game.settings;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.DrawEvent;
import com.lilangel.views.Source;
import com.lilangel.views.View;
import com.lilangel.views.game.enums.ButtonClickEvents;
import com.lilangel.views.game.enums.GameSpeed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static com.lilangel.views.game.enums.ButtonClickEvents.*;

public class GameSettingsVisualizer extends JPanel implements View {

    private ViewListener listener;

    public GameSettingsVisualizer() {
        this.setLayout(null);
        JButton speedUpButton = createButton(new ImageIcon("src/resources/mc_button_speedup.png"),
                new ImageIcon("src/resources/mc_button_speedup_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speedup_click.png"),
                210, 5, 200, 40, ButtonClickEvents.CLICK_ON_GAME_SPEED_UP.command, new ButtonClickListener());
        JButton speedDownButton = createButton(new ImageIcon("src/resources/mc_button_speeddown.png"),
                new ImageIcon("src/resources/mc_button_speeddown_mouseover.png"),
                new ImageIcon("src/resources/mc_button_speeddown_click.png"),
                5, 5, 200, 40, CLICK_ON_GAME_SPEED_DOWN.command, new ButtonClickListener());

        JButton pauseButton = createButton(new ImageIcon("src/resources/mc_button_pause.png"),
                new ImageIcon("src/resources/mc_button_pause_mouseover.png"),
                new ImageIcon("src/resources/mc_button_pause_click.png"),
                5, 50, 32, 32, ButtonClickEvents.CLICK_ON_PAUSE_BUTTON.command, new ButtonClickListener());
        pauseButton.setEnabled(false);
        JButton playButton = createButton(new ImageIcon("src/resources/mc_button_play.png"),
                new ImageIcon("src/resources/mc_button_play_mouseover.png"),
                new ImageIcon("src/resources/mc_button_play_click.png"),
                42, 50, 32, 32, ButtonClickEvents.CLICK_ON_PLAY_BUTTON.command, new ButtonClickListener());
        playButton.setEnabled(true);
        JButton speedInfoButton = createSimpleButton(new ImageIcon("src/resources/game_speed_pause.png"),
                110, 50, 300, 40);
        JButton evolutionTriggerButton = createButton(new ImageIcon("src/resources/start_evolution.png"),
                new ImageIcon("src/resources/start_evolution_mouseover.png"),
                new ImageIcon("src/resources/start_evolution_pressed.png"),
                5,95,300,40, CLICK_ON_EVOLUTION_TRIGGER.command,new ButtonClickListener());
        evolutionTriggerButton.setEnabled(false);

        add(speedInfoButton);
        add(speedUpButton);
        add(speedDownButton);
        add(pauseButton);
        add(playButton);
        add(evolutionTriggerButton);
        this.setBounds(0, 0, 425, 177);
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

    class ButtonClickListener implements ActionListener {
        Map<String, ButtonClickEvents> eventsMap = new HashMap<>() {{
            put(ButtonClickEvents.CLICK_ON_GAME_SPEED_UP.command, ButtonClickEvents.CLICK_ON_GAME_SPEED_UP);
            put(ButtonClickEvents.CLICK_ON_GAME_SPEED_DOWN.command, CLICK_ON_GAME_SPEED_DOWN);
            put(ButtonClickEvents.CLICK_ON_PAUSE_BUTTON.command, ButtonClickEvents.CLICK_ON_PAUSE_BUTTON);
            put(ButtonClickEvents.CLICK_ON_PLAY_BUTTON.command, ButtonClickEvents.CLICK_ON_PLAY_BUTTON);
            put(ButtonClickEvents.CLICK_ON_EVOLUTION_TRIGGER.command, ButtonClickEvents.CLICK_ON_EVOLUTION_TRIGGER);
        }};

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            notifyListeners(new ButtonClickEvent(Source.GAME_SETTINGS_VISUALIZER, e.getID(), e.getActionCommand(), eventsMap.get(e.getActionCommand())));
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
    public void update() {
        update(new DrawEvent(new Object(), 0, ""));
    }

    @Override
    public void update(DrawEvent e) {
        if (e instanceof SpeedModeButtonRedrawEvent speedEvent)
            changeSpeedInfoButtonIcon(speedEvent.getMode());
        else if(e instanceof EvolutionTriggerButtonRedrawEvent evoEvent)
            flipEvolutionButton(evoEvent.evolutionActive);
        onRedrawEvent();
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

    Map<GameSpeed, String> iconsMap = new HashMap<>() {{
        put(GameSpeed.NORMAL, "src/resources/game_speed_normal.png");
        put(GameSpeed.FAST, "src/resources/game_speed_fast.png");
        put(GameSpeed.VERY_FAST, "src/resources/game_speed_veryfast.png");
        put(GameSpeed.SLOW, "src/resources/game_speed_slow.png");
        put(GameSpeed.VERY_SLOW, "src/resources/game_speed_veryslow.png");
        put(GameSpeed.PAUSE, "src/resources/game_speed_pause.png");
    }};

    private void changeSpeedInfoButtonIcon(GameSpeed mode) {
        JButton speedModeButton = (JButton) this.getComponentAt(110, 50);
        speedModeButton.setIcon(new ImageIcon(iconsMap.get(mode)));
        JButton pauseButton = (JButton) this.getComponentAt(5, 50);
        JButton playButton = (JButton) this.getComponentAt(42, 50);
        if (mode.equals(GameSpeed.PAUSE)) {
            pauseButton.setEnabled(false);
            playButton.setEnabled(true);
        } else {
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
        }
        JButton evolutionTriggerButton = (JButton) this.getComponentAt(5,100);
        evolutionTriggerButton.setEnabled(true);
    }

    private void flipEvolutionButton(boolean evolutionActive){
        JButton evolutionTriggerButton = (JButton) this.getComponentAt(5,100);
        if(evolutionActive){
            evolutionTriggerButton.setIcon(new ImageIcon("src/resources/evolution_in_process.png"));
            evolutionTriggerButton.setRolloverIcon(new ImageIcon("src/resources/evolution_in_process_mouseover.png"));
            evolutionTriggerButton.setPressedIcon(new ImageIcon("src/resources/evolution_in_process_pressed.png"));
            setEnabledAllButtons(false);
        }
        else {
            evolutionTriggerButton.setIcon(new ImageIcon("src/resources/start_evolution.png"));
            evolutionTriggerButton.setRolloverIcon(new ImageIcon("src/resources/start_evolution_mouseover.png"));
            evolutionTriggerButton.setPressedIcon(new ImageIcon("src/resources/start_evolution_pressed.png"));
            setEnabledAllButtons(true);
        }
        evolutionTriggerButton.setEnabled(true);
        evolutionTriggerButton.setVisible(true);
    }

    private void setEnabledAllButtons(boolean value){
        JButton pauseButton = (JButton) this.getComponentAt(5, 50);
        JButton playButton = (JButton) this.getComponentAt(42, 50);
        JButton speedUp = (JButton) this.getComponentAt(210, 5);
        JButton speedDown = (JButton) this.getComponentAt(5, 5);

        pauseButton.setEnabled(value);
        playButton.setEnabled(value);
        speedUp.setEnabled(value);
        speedDown.setEnabled(value);
    }
}
