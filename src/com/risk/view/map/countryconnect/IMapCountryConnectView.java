package com.risk.view.map.countryconnect;

import com.risk.model.CountryModel;
import com.risk.view.IView;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

public interface IMapCountryConnectView extends IView {

    void addAddNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener);

    void addRemoveNeighbouringListener(BiConsumer<CountryModel, CountryModel> listener);

    void addSaveListener(ActionListener listener);

    // TODO change it. Not sure how to change it now
    ListSelectionModel getLeftListSelectionModel();

    JList getLeftCountryParentList();

    JList getRightCountryParentList();

    void setListSelectionListener(ListSelectionListener actionListener);

}
