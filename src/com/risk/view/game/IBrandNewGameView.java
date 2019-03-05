package com.risk.view.game;

import com.risk.view.IView;

import java.awt.event.ActionListener;
import java.util.function.Consumer;

public interface IBrandNewGameView extends IView {

    void addBrowseMapListener(final ActionListener listener);
    void addPlayListener(final Consumer<Integer> listener);
    void addCancelListener(final ActionListener listener);

}