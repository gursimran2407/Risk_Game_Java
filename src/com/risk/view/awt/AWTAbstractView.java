package com.risk.view.awt;

import com.risk.view.IView;

import javax.swing.*;

public abstract class AWTAbstractView extends JFrame implements IView {

    @Override
    public void showView() {
        setVisible(true);
    }

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
