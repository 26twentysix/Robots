package com.lilangel.model;

import com.lilangel.presenter.ModelListener;

/**
 * Inteface for the model
 */
public interface Model {
    void notifyPresenter();
    void setPresenter(ModelListener presenter);
}
