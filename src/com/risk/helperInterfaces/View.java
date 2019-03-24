package com.risk.helperInterfaces;

import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * Rule to build a view
 *
 * @author KaranPannu
 *
 */
public interface View extends Observer {

    /**
     * Sets Action Listener
     *
     * @param actionListener
     */
    public void setActionListener(ActionListener actionListener);
}
