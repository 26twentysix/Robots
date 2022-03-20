package com.lilangel.gui;

import com.lilangel.log.Logger;
import com.lilangel.presenter.Presenter;
import com.lilangel.presenter.ViewListener;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * MVP View component, responsible for drawing and telling Presenter about User interaction with app
 */
public class View {

    ViewListener presenter;

    GameVisualizer gameVisualizer;
    GameWindow gameWindow;
    LogWindow logWindow;

    /**
     * Constructor initialize {@link #gameVisualizer} with a {@link #presenter}, so MVP can do things
     *
     * @param presenter {@link ViewListener} instance
     */
    public View(ViewListener presenter) {
        this.gameVisualizer = new GameVisualizer(presenter);
        this.gameWindow = createGameWindow();
        this.logWindow = createLogWindow();
    }

    /**
     * GameWindow fabric method
     *
     * @return GameWindow instance initialized with {@link #gameVisualizer}
     */
    protected GameWindow createGameWindow() {
        GameWindow gameWindow = new GameWindow(gameVisualizer);
        gameWindow.setLocation(310, 10);
        gameWindow.setSize(400, 400);
        return gameWindow;
    }

    /**
     * LogWindow fabric method
     *
     * @return LogWindow instance
     */
    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    /**
     * Method that starts the main app window {@link MainApplicationFrame}
     */
    public void start() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainApplicationFrame frame = new MainApplicationFrame(this.logWindow, this.gameWindow);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
