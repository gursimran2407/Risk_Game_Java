package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

public class MapCreateCountryView extends JFrame implements ViewInterface {

    private JLabel welcomeLabel;
    private JTextField countryValue;

    private JLabel countryListText;
    private JLabel continentNameLabel;
    private JButton nextButton;
    private JButton addButton;
    private JPanel mainPanel;
    private JTextArea textArea;

    /**
     * It creates country map
     *
     * @param listOfContinents continents list
     */
    public MapCreateCountryView(List<ContinentModel> listOfContinents) {
        this.setTitle("Create Country");
        JButton saveButton;
        welcomeLabel = new JLabel("Please add the Countries in the Continents you created:");

        countryListText = new JLabel("Country");
        addButton = new JButton("Add");
        saveButton = new JButton("Save");
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
     * Updates the screen after creating a country
     *
     * @param listOfContinentModel continents list
     * @param listOfCountryModel countries list
     */
    public void updateScreen(List<ContinentModel> listOfContinentModel, List<CountryModel> listOfCountryModel) {
        mainPanel.removeAll();

        JComboBox continentListCombobox;
        Object[] continentListArray;
        CountryViewRenderer continentViewRenderer;
        StringBuilder textAreaText = new StringBuilder("------------------------------------------------");

        if (listOfCountryModel == null) {
            textArea.setText(textAreaText.toString());
        } else {
            textAreaText.setLength(0);
            for (int i = 0; i < listOfCountryModel.size(); i++) {
                textAreaText.append("Country: " + listOfCountryModel.get(i).getCountryName() + " ,Continent: "
                        + listOfCountryModel.get(i).getContinentName() + "\n");
            }
        }
        textArea.setText(textAreaText.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        mainPanel.add(textArea);
        textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Countries added list:"));
        textArea.setBounds(520, 0, 260, 650);

        Color main = new Color(230, 230, 255);
        Color secondary = new Color(0, 0, 26);
        textArea.setBackground(main); // sets the background color
        textArea.setForeground(secondary);

        continentViewRenderer = new CountryViewRenderer();
        continentListArray = listOfContinentModel.toArray();
        continentListCombobox = new JComboBox(continentListArray);

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
     * sets actions to JButton variables
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);
    }

    /**
     * Listens to notifyObservers of Observable classes
     */
    @Override
    public void update(Observable obs, Object arg) {
        List<CountryModel> listOfCountryModel = ((MapRiskModel) obs).getCountryModelList();
        List<ContinentModel> listOfContinentModel = ((MapRiskModel) obs).getContinentModelList();
        this.updateScreen(listOfContinentModel, listOfCountryModel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Inside, getter method that provides us a map model corresponding to a map
     * name
     */
    public class CountryViewRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            ContinentModel map_model = (ContinentModel) value;
            if (map_model != null)
                setText(map_model.getContinentName());

            return this;
        }
    }
}