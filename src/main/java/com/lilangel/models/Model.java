package com.lilangel.models;

import com.lilangel.presenters.ModelListener;

/**
 * Inteface for the model
 */
public interface Model extends Runnable {
    void notifyPresenters(String message);

    void setPresenters(ModelListener[] presenters);
}
