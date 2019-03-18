package com.risk.model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is a country's properties such as name, position,
 * continent name to which it belongs, connected countries, Name of owner
 * Number of armies, and color.
 *
 * @author Karandeep
 *
 */
public class CountryModel extends JButton {

    private String countryName;
    private PlayerModel countryOwner;
    private String continentName;
    private ArrayList<CountryModel> connectedCountryList;
    private int numberofArmies;
    private Color borderColor;
    private int xPosition;
    private int yPosition;
    private Color backgroundColor;
    private String rulerName;

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    /**
     * Constructor of CountryModel with parameters
     *
     * @param countryName
     * @param continentName
     * @param linkCountryModel
     * @param countryOwner
     * @param numberofArmies
     */

    public CountryModel(String countryName, int xPosition, int yPosition, PlayerModel countryOwner,
                        String continentName, ArrayList<CountryModel> linkCountryModel, int numberofArmies, String rulerName) {
        this.countryName = countryName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.countryOwner = countryOwner;
        this.continentName = continentName;
        this.connectedCountryList = linkCountryModel;
        this.numberofArmies = numberofArmies;
        this.borderColor = Color.BLACK;
        this.backgroundColor = Color.WHITE;
        this.rulerName = rulerName;
    }

    /**
     * Default constructor
     */
    public CountryModel() {
    }

    /**
     * @return the X Position Value.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Sets the X Position Value.
     *
     * @param xPosition
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * @return the Y Position Value.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Sets the Y Position Value.
     *
     * @param yPosition
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * @return the color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the color of the country
     *
     * @param color
     */
    public void setBackgroundColor(Color color) {
        this.setBackground(color);
        this.backgroundColor = color;
    }

    /**
     * gets the name of the country
     *
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }
    /**
     * gets the owner of the country
     *
     * @return countryOwner
     */
    public PlayerModel getCountryOwner() {
        return countryOwner;
    }
    /**
     * gets the name of the continent
     *
     * @return continentName
     */
    public String getContinentName() {
        return continentName;
    }

    /**
     * sets the owner of the country
     *
     * @return countryOwner
     */
    public void setCountryOwner(PlayerModel countryOwner) {
        this.countryOwner = countryOwner;
    }
    /**
     * gets the number of armies
     *
     * @return numberofArmies
     */
    public int getNumberofArmies() {
        return numberofArmies;
    }
    /**
     * sets the name of the country
     *
     * @return countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    /**
     * gets the name of the connected countries
     *
     * @return getConnectedCountryList
     */
    public ArrayList<CountryModel> getConnectedCountryList() {
        return connectedCountryList;
    }
    /**
     * sets the name of the continent
     *
     * @return continentName
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
    /**
     * sets the name of the connected countries
     *
     * @return setConnectedCountryList
     */
    public void setConnectedCountryList(ArrayList<CountryModel> linkCountryModel) {
        this.connectedCountryList = linkCountryModel;
    }
    /**
     * sets the number of armies
     *
     * @return numberofArmies
     */
    public void setNumberofArmies(int numberofArmies) {
        this.numberofArmies = numberofArmies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Continent Name:\n");
        sb.append(continentName + "\n");
        sb.append("Country Name " + countryName);
        return sb.toString();
    }

    /**
     * gets the color of the border of the countries
     *
     * @return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the borderColor
     *
     * @param borderColor
     */
    public void setBorderColor(Color borderColor) {
        this.setBorder(new LineBorder(borderColor));
        this.borderColor = borderColor;
    }

    /**
     * gets country owner name
     * @return name of the country owner
     */
    public String getRulerName() {
        return rulerName;
    }

    /**
     * sets country owner name
     * @param rulerName name of owner
     */
    public void setRulerName(String rulerName) {
        this.rulerName = rulerName;
    }
}


