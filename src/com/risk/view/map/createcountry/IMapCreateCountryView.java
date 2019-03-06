package com.risk.view.map.createcountry;

import com.risk.model.ContinentModel;
import com.risk.view.IView;

import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

/**
 * Interface to add listeners
 * @author Namita Faujdar
 */
public interface IMapCreateCountryView extends IView {

    void addCountryListener(final BiConsumer<String, ContinentModel> listener);

    void addShowMapCountryConnectListener(final ActionListener listener);

}
