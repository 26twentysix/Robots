package com.lilangel.models;

import com.lilangel.presenter.ModelListener;

/**
 * Inteface for the model
 */
public interface Model extends Runnable{
    void notifyPresenter(String message);

    void setPresenter(ModelListener presenter);
}
