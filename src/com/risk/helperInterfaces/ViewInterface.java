package com.risk.helperInterfaces;

import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * interface to build view class
 *
 * @author gursimransingh
 */
public interface ViewInterface extends Observer {
    /**
     * Sets Action Listener to listen to the event
     *
     * @param actionListener
     */
    void setActionListener(ActionListener actionListener);
}
