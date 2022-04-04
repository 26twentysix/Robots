package com.lilangel;

import com.lilangel.gui.MainApplicationFrame;
import com.lilangel.model.Model;
import com.lilangel.model.Battleground;
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
        this.model = new Battleground();
        this.model.setPresenter(presenter);
        this.frame = new MainApplicationFrame(presenter);
        presenter.setModel(model);
        presenter.setView(this.frame.getView());
    }

    public void startApp() {
        startFrame();
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
