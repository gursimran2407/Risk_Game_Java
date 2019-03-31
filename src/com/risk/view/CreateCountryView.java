package com.risk.view;

import com.risk.helperInterfaces.View;
import com.risk.model.ContinentsModel;
import com.risk.model.CountryModel;
import com.risk.model.GameMapModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
/**
 * CreateCountryView class is representing a view object for creating a country
 * view Properties are labels, text fields, buttons, combo-boxes, a  pane and a panel
 *
 * @author Namita Faujdar
 */

public class CreateCountryView extends JFrame implements View {

    /** The welcome label. */
    public JLabel welcomeLabel;

    /** The country value. */
    public JTextField countryValue;

    /** The continent list combobox. */
    public JComboBox continentListCombobox;

    /** The country list text. */
    private JLabel countryListText;

    /** The continent name label. */
    private JLabel continentNameLabel;

    /** The next button. */
    public JButton nextButton;

    /** The add button. */
    public JButton addButton;

    /** The main panel. */
    private JPanel mainPanel;

    /** The text area. */
    private JTextArea textArea;

    /**
     * Construction of "CreateCountryView".
     *
     * @param listOfContinents the list of continents
     */
    public CreateCountryView(List<ContinentsModel> listOfContinents) {
        this.setTitle("Create Country");

        welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");

        countryListText = new JLabel("Country");
        addButton = new JButton("Add");
        mainPanel = new JPanel();

        this.setName("RISK GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setVisible(false);
        mainPanel.setLayout(null);
        this.add(mainPanel);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        textArea = new JTextArea("Default text", 5, 5);

        welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");
        welcomeLabel.setBounds(100, 0, 600, 100);

        countryListText = new JLabel("Country Name: ");
        countryListText.setBounds(100, 100, 200, 40);

        countryValue = new JTextField();
        countryValue.setBounds(200, 100, 200, 40);

        continentNameLabel = new JLabel("Continent Name: ");
        continentNameLabel.setBounds(100, 200, 200, 40);

        addButton = new JButton("Add");
        addButton.setBounds(100, 400, 100, 40);

        nextButton = new JButton("Next");
        nextButton.setBounds(200, 400, 100, 40);
        updateScreen(listOfContinents, null);
    }

    /**
     * Updates the screen after creating a country.
     *
     * @param listOfContinentModel the list of continent model
     * @param listOfCountryModel the list of country model
     */
    private void updateScreen(List<ContinentsModel> listOfContinentModel, List<CountryModel> listOfCountryModel) {
        mainPanel.removeAll();

        StringBuilder textAreaText = new StringBuilder("------------------------------------------------");

        if (listOfCountryModel == null) {
            textArea.setText(textAreaText.toString());
        } else {
            textAreaText.setLength(0);
            for (CountryModel countryModel : listOfCountryModel) {
                textAreaText.append("Country: " + countryModel.getCountryName() + " ,Continent: "
                        + countryModel.getcontinentName() + "\n");
            }
        }
        textArea.setText(textAreaText.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        mainPanel.add(textArea);
        textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Countries added list:"));
        textArea.setBounds(520, 0, 260, 650);

        Color main = new Color(229, 255, 27);
        Color secondary = new Color(0, 0, 26);
        textArea.setBackground(main); // sets the background color
        textArea.setForeground(secondary);

        /* The continent view renderer. */
        CountryViewRenderer continentViewRenderer = new CountryViewRenderer();
        /* The continent list array. */
        Object[] continentListArray = listOfContinentModel.toArray();
        continentListCombobox = new JComboBox<>(continentListArray);

        if (continentListArray.length > 0) {
            continentListCombobox.setRenderer(continentViewRenderer);
        }
        continentListCombobox.setBounds(200, 200, 100, 40);
        mainPanel.add(continentListCombobox);

        this.add(mainPanel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(continentNameLabel);
        mainPanel.add(addButton);
        mainPanel.add(nextButton);
        mainPanel.add(countryListText);
        mainPanel.add(countryValue);
        mainPanel.add(countryListText);

    }

    /**
     * Sets actions to "addButton" and "nextButton".
     *
     * @param actionListener the new action listener
     * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);
    }

    /**
     * Update the view based on observer.
     *
     * @param obs the obs
     * @param arg the arg
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        List<CountryModel> listOfCountryModel = ((GameMapModel) obs).getCountries();
        List<ContinentsModel> listOfContinentModel = ((GameMapModel) obs).getContinents();
        this.updateScreen(listOfContinentModel, listOfCountryModel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Inside, getter method that provides us a map model corresponding to a map
     * name.
     */
    public class CountryViewRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            ContinentsModel map_model = (ContinentsModel) value;
            if (map_model != null)
                setText(map_model.getContinentName());

            return this;
        }
    }
}
