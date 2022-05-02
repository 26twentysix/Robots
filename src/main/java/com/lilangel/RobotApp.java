package com.lilangel;

import com.lilangel.models.RobotsModel;
import com.lilangel.presenters.ModelListener;
import com.lilangel.presenters.MonitorPresenter;
import com.lilangel.views.MainApplicationFrame;
import com.lilangel.presenters.GamePresenter;

import javax.swing.*;
import java.awt.*;

/**
 * Main app class, that initialize Model, View and Presenter and starts the things
 */
public class RobotApp {
    final RobotsModel model;
    final MainApplicationFrame frame;
    final GamePresenter gamePresenter;
    final MonitorPresenter monitorPresenter;

    public RobotApp() {
        this.frame = new MainApplicationFrame();

        this.gamePresenter = new GamePresenter(this.frame);
        this.monitorPresenter = new MonitorPresenter(this.frame);

        this.model = new RobotsModel();
        this.model.setPresenters(new ModelListener[]{gamePresenter, monitorPresenter});

        gamePresenter.setModel(model);
        monitorPresenter.setModel(model);
    }

    public void startApp() {
        var modelThread = new Thread(model);
        modelThread.setDaemon(true);
        modelThread.start();

        SwingUtilities.invokeLater(this::startFrame);
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
