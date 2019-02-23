package com.risk.model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class CountryModel extends JButton {

    private String countryName;
    private PlayerModel countryOwner;
    private String continentName;
    private ArrayList<CountryModel> connectedCountryList;
    private int numberofArmies;
    private Color borderColor;
    private int xPosition;

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    private int yPosition;
    private Color backgroundColor;


    public CountryModel(String countryName, PlayerModel countryOwner, String continentName, ArrayList<CountryModel> linkCountryModel, int numberofArmies) {
        this.countryName = countryName;
        this.countryOwner = countryOwner;
        this.continentName = continentName;
        this.connectedCountryList = linkCountryModel;
        this.numberofArmies = numberofArmies;
        this.borderColor = Color.BLACK;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.backgroundColor = Color.WHITE;
    }

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


    public String getCountryName() {
        return countryName;
    }

    public PlayerModel getCountryOwner() {
        return countryOwner;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setCountryOwner(PlayerModel countryOwner) {
        this.countryOwner = countryOwner;
    }

    public int getNumberofArmies() {
        return numberofArmies;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ArrayList<CountryModel> getConnectedCountryList() {
        return connectedCountryList;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public void setConnectedCountryList(ArrayList<CountryModel> linkCountryModel) {
        this.connectedCountryList = linkCountryModel;
    }

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
}


