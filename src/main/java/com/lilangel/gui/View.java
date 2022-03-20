package com.lilangel.gui;

import com.lilangel.log.Logger;
import com.lilangel.presenter.Presenter;
import com.lilangel.presenter.ViewListener;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class View {
    ViewListener presenter;

    MainApplicationFrame mainApplicationFrame;
    GameVisualizer gameVisualizer;
    GameWindow gameWindow;
    LogWindow logWindow;

    public View(Presenter presenter){
        this.gameVisualizer = new GameVisualizer(presenter);
        this.gameWindow = createGameWindow();
        this.logWindow = createLogWindow();
    }

    protected GameWindow createGameWindow(){
        GameWindow gameWindow = new GameWindow(gameVisualizer);
        gameWindow.setLocation(310,10);
        gameWindow.setSize(400,  400);
        return gameWindow;
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    public void start(){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainApplicationFrame frame = new MainApplicationFrame(this.logWindow,this.gameWindow);
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
