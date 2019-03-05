package com.risk.view.awt.map.edit;

import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

/**
 * @author gursimransingh
 * View class for the Mapedit View Interface
 */
public class MapEditView extends JFrame implements ViewInterface {

    public JLabel welcomeLabel;
    public JLabel continentListText;
    public JComboBox continentListCombobox;
    public Object[] continentListArray;
    public JLabel controlValueText;
    public JTextField controlValue;
    public JButton saveButton;
    public JButton addButton;
    public JPanel welcomePanel;
    private ContinentViewRenderer continentViewRenderer;

    public MapEditView(List<ContinentModel> continentModelList) {

        welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");

        continentListText = new JLabel("Continent");
        controlValueText = new JLabel("Control Value");
        controlValue = new JTextField();

        saveButton = new JButton("Save");
        addButton = new JButton("Add");

        welcomePanel = new JPanel();

        this.setName("RISK GAME");
        this.setResizable(false);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocation(300, 200);
        this.setSize(800, 700);
        welcomePanel.setLayout(null);
        this.add(welcomePanel);

        this.updateUI(continentModelList);

    }

    /**
     * @param actionListener Setting ActionListener on the button
     */

    @Override
    public void setActionListener(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
        saveButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        List<ContinentModel> continentModelList = ((MapRiskModel) o).getContinentModelList();
        updateUI(continentModelList);
        //validating the root
        this.revalidate();
        this.repaint();
    }

    private void updateUI(List<ContinentModel> continentModelList) {
        welcomePanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);
        welcomeLabel.setBounds(100, 0, 600, 200);

        continentListText.setFont(mediumFont);
        welcomePanel.add(continentListText);
        continentListText.setBounds(100, 50, 500, 200);

        controlValueText.setFont(mediumFont);
        welcomePanel.add(controlValueText);
        controlValueText.setBounds(100, 150, 200, 100);

        continentViewRenderer = new ContinentViewRenderer();
        continentListArray = continentModelList.toArray();
        continentListCombobox = new JComboBox(continentListArray);

        if (continentListArray.length > 0) {
            continentListCombobox.setRenderer(continentViewRenderer);
        }
        continentListCombobox.setBounds(200, 140, 100, 20);
        welcomePanel.add(continentListCombobox);

        controlValue.setFont(mediumFont);
        welcomePanel.add(controlValue);
        controlValue.setBounds(200, 195, 100, 20);

        addButton.setFont(smallFont);
        welcomePanel.add(addButton);
        addButton.setBounds(100, 250, 100, 20);

        saveButton.setFont(smallFont);
        welcomePanel.add(saveButton);
        saveButton.setBounds(200, 250, 100, 20);
    }

    //Updating combobox as per change in continents
    public class ContinentViewRenderer extends BasicComboBoxRenderer {

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
