package com.risk.gameplayrequirements;

import java.awt.Color;
import javax.swing.JButton;

import com.risk.model.CountryModel;

/**
 * This gives the position of the country to the view to be
 * displayed as per on the UI
 *
 * @author Karandeep
 */

public class MapPosition extends JButton {

    private int xPos;
    private int yPos;
    private CountryModel country;
    private Color color;

    /**
     * gets position of x
     *
     * @return xPos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Sets position x
     *
     * @param xPos the position of x
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * gets the y position
     *
     * @return the y position
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Sets the position of y
     *
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets the country
     * @return country
     */
    public CountryModel getCountry() {
        return country;
    }

    /**
     * Sets the parameter to the country
     *
     * @param country
     */
    public void setCountry(CountryModel country) {
        this.country = country;
    }

    /**
     * gets the color
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to set the color
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
