package com.risk.helperInterfaces;

import java.awt.event.ActionListener;
import java.util.Observer;

/**
 * @author gursimransingh
 */
public interface ViewInterface extends Observer {

    void setActionListener(ActionListener actionListener);
}
