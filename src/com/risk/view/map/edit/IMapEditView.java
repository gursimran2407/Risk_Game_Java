package com.risk.view.map.edit;

import com.risk.model.ContinentModel;
import com.risk.view.IView;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface IMapEditView extends IView {

    void addContinentListener(final BiConsumer<ContinentModel, String> listener);

    void addSaveListener(final Consumer<String> listener);
}
