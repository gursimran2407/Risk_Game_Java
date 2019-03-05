package com.risk.view.awt;

import com.risk.view.IView;

import javax.swing.*;

/**
 * Implementation of IView interface
 *
 * @author Namita Faujdar
 */
public abstract class AWTAbstractView extends JFrame implements IView {

    /**
     * this method sets JFrame to visible
     */
    @Override
    public void showView() {
        setVisible(true);
    }

    /**
     * this method close the previous JFrame
     */
    @Override
    public void hideView() {
        dispose();
    }

    @Override
    public void showMessage(final String message) {
        showMessage("Error", message);
    }

    @Override
    public void showMessage(final String title, final String message) {
        JOptionPane.showOptionDialog(
                null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new Object[] {}, null);
    }
}
