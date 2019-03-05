package com.risk.view;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * interface to build view class
 *
 * @author gursimransingh
 */
public interface IView extends Observer {

    default void showView() {

    }

    default void hideView() {

    }

    default void showMessage(final String message) {

    }

    default void showMessage(final String title, final String message) {

    }

    /**
     * Sets Action Listener to listen to the event
     *
     * @param actionListener
     */
    default void setActionListener(ActionListener actionListener) {

    }

    /**
     * @param o   getting reference from model
     * @param arg getting argument from observable model
     */
    @Override
    default void update(Observable o, Object arg) {

    }


}
