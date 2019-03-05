package com.risk;

/** This file is main starting point of the program containing function
 * for main game
 *
 */

public class Start {
    public static void main (String [] args)
    {
        new MainGameController(new Environment(new AWTViewManager()));
    }
}
