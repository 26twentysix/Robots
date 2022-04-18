package com.lilangel;

import com.lilangel.gui.MainApplicationFrame;
import com.lilangel.model.Model;
import com.lilangel.model.RobotsModel;
import com.lilangel.presenter.Presenter;

import javax.swing.*;
import java.awt.*;

/**
 * Main app class, that initialize Model, View and Presenter and starts the things
 */
public class RobotApp {
    final Model model;
    final MainApplicationFrame frame;
    final Presenter presenter;

    public RobotApp() {
        this.presenter = new Presenter();
        this.model = new RobotsModel();
        this.model.setPresenter(presenter);
        this.frame = new MainApplicationFrame(presenter);
        presenter.setModel(model);
        presenter.setView(this.frame.getGameVisualizer());
    }

    public void startApp() {
        var frameThread = new Thread(this::startFrame);
        var modelThread = new Thread(model);
        modelThread.setDaemon(true);
        modelThread.start();

        frameThread.start();
    }

    public void startFrame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            MainApplicationFrame frame = this.frame;
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}
