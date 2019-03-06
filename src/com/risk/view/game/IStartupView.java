package com.risk.view.game;

import com.risk.model.CountryModel;
import com.risk.view.IView;

import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

/**
 * Interface to add listeners
 * @author Namita Faujdar
 */
public interface IStartupView extends IView {

    void setWelcomeMessage(String message);

    void addTroopsListener(BiConsumer<Object, CountryModel> listener);

    void addNextListener(ActionListener listener);
}
