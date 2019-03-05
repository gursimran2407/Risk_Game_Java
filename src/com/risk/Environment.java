package com.risk;

import com.risk.view.ViewManager;

public class Environment {

    private final ViewManager viewManager;

    public Environment(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void exit() {
        System.exit(0);
    }

    public ViewManager getViewManager() {
        return viewManager;
    }
}
