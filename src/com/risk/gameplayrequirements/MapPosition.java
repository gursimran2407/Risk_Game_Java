package com.risk.gameplayrequirements;

import java.awt.Color;
import javax.swing.JButton;

import com.risk.model.CountryModel;

/**
 * "MapButton" class gives the positioning of the country
 *
 * @author Karandeep
 */

public class MapPosition extends JButton {

    private int xPos;
    private int yPos;
    private CountryModel country;
    private Color color;

    /**
     * @return the xPos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Sets x
     *
     * @param xPos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Sets y
     *
     * @param yPos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the country
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
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
