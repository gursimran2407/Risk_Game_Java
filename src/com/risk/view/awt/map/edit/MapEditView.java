package com.risk.view.awt.map.edit;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.map.edit.IMapEditView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author gursimransingh
 * View class for the Mapedit View Interface
 */

public class MapEditView extends AWTAbstractView implements IMapEditView {

    private static final Font FONT_LARGE = new Font("Serif", Font.BOLD, 18);
    private static final Font FONT_MEDIUM = new Font("Serif", Font.BOLD, 14);
    private static final Font FONT_SMALL = new Font("Serif", Font.BOLD, 12);

    private JComboBox<ContinentModel> cbxContinentList;
    private JTextField tfControlValue;
    private JButton btnSave;
    private JButton btnAdd;

    public MapEditView(List<ContinentModel> continentModelList) {
        createUI();
        this.updateUI(continentModelList);
    }

    private void createUI() {
        this.setName("RISK GAME");
        this.setResizable(false);
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 700);

        final JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        this.add(welcomePanel);

        final JLabel welcomeLabel = new JLabel("Please select the Continents you want in the map and the control value");
        welcomeLabel.setFont(FONT_LARGE);
        welcomeLabel.setBounds(100, 0, 600, 200);
        welcomePanel.add(welcomeLabel);

        final JLabel continentListText = new JLabel("Continent");
        continentListText.setFont(FONT_MEDIUM);
        continentListText.setBounds(100, 50, 500, 200);
        welcomePanel.add(continentListText);

        final JLabel controlValueText = new JLabel("Control Value");
        controlValueText.setFont(FONT_MEDIUM);
        controlValueText.setBounds(100, 150, 200, 100);
        welcomePanel.add(controlValueText);

        cbxContinentList = new JComboBox<>();
        cbxContinentList.setRenderer(new ContinentViewRenderer());
        cbxContinentList.setBounds(200, 140, 100, 20);
        welcomePanel.add(cbxContinentList);

        tfControlValue = new JTextField();
        tfControlValue.setFont(FONT_MEDIUM);
        tfControlValue.setBounds(200, 195, 100, 20);
        welcomePanel.add(tfControlValue);

        btnAdd = new JButton("Add");
        btnAdd.setFont(FONT_SMALL);
        btnAdd.setBounds(100, 250, 100, 20);
        welcomePanel.add(btnAdd);

        btnSave = new JButton("Save");
        btnSave.setFont(FONT_SMALL);
        btnSave.setBounds(200, 250, 100, 20);
        welcomePanel.add(btnSave);
    }

    @Override
    public void addSaveListener(Consumer<String> listener) {
        btnSave.addActionListener(e -> listener.accept(tfControlValue.getText()));
    }

    @Override
    public void addContinentListener(BiConsumer<ContinentModel, String> listener) {
        btnAdd.addActionListener(
                e -> listener.accept((ContinentModel) cbxContinentList.getSelectedItem(), tfControlValue.getText()));
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUI(((MapRiskModel) o).getContinentModelList());

        // validating the root
        this.revalidate();
        this.repaint();
    }

    private void updateUI(List<ContinentModel> continentModelList) {
        cbxContinentList.removeAllItems();
        continentModelList.forEach(cbxContinentList::addItem);
    }

    // Updating combobox as per change in continents
    public class ContinentViewRenderer extends BasicComboBoxRenderer {

        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            ContinentModel map_model = (ContinentModel) value;
            if (map_model != null)
                setText(map_model.getContinentName());

            return this;
        }
    }
}
