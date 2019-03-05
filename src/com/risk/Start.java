package com.risk;

import com.risk.controller.main.MainGameController;
import com.risk.view.awt.AWTViewManager;

/** This file is the main starting point of the program containing function
 * for main game
 *
 * @author gursimransingh
 * @version 1.0
 */

public class Start {

    public static void main(String[] args) {
        new MainGameController(new Environment(new AWTViewManager()));
    }

}
