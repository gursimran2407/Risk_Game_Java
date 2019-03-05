package com.risk.view.main;

import com.risk.view.IView;

import java.awt.event.ActionListener;

public interface IMainGameView extends IView {

    void addCreateMapListener(final ActionListener listener);

    void addEditMapListener(final ActionListener listener);

    void addPlayMapListener(final ActionListener listener);

    void addExitListener(final ActionListener listener);
}
