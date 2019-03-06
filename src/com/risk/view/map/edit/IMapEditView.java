package com.risk.view.map.edit;

import com.risk.model.ContinentModel;
import com.risk.view.IView;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Interface to add listeners
 * @author Namita Faujdar
 */
public interface IMapEditView extends IView {

    void addContinentListener(final BiConsumer<ContinentModel, String> listener);

    void addSaveListener(final Consumer<String> listener);
}
