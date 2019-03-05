package com.risk.view.awt.map.createcontinent;

import com.risk.model.ContinentModel;
import com.risk.model.MapRiskModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.map.createcontinent.IMapCreateContinentView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.function.BiConsumer;

/**
 * "MapCreateContinentView" class in representing a view object for creating continent
 * These properties are labels, text fields, buttons, a pane, and a panel
 *
 * @author gursimransingh
 */
public class MapCreateContinentView extends AWTAbstractView implements IMapCreateContinentView {

    /**
     * View Properties
     */

    private JTextField tfContinentValue = null;
    private JTextField tfControlValue = null;

    private JButton btnNext = null;
    private JButton btnAdd = null;

    private JTextArea textArea = null;

    /**
     * Construction of CreateContinentView
     */
    public MapCreateContinentView() {
        createUI();
        updateUI(null);
    }

    private void createUI() {
        this.setTitle("Create Continent");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setVisible(false);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        this.add(mainPanel);

        textArea = new JTextArea("Default text", 5, 5);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "ADDED Continents"));
        textArea.setBounds(520, 15, 260, 450);
        textArea.setBackground(new Color(115, 255, 238));
        textArea.setForeground(new Color(0, 0, 26));
        mainPanel.add(textArea);

        final JLabel lblWelcome = new JLabel("Please name the Continents you want in the map and their control values");
        lblWelcome.setBounds(25, 0, 600, 100);
        mainPanel.add(lblWelcome);

        final JLabel continentListText = new JLabel("Continent Name: ");
        continentListText.setBounds(25, 75, 120, 40);
        mainPanel.add(continentListText);

        tfContinentValue = new JTextField();
        tfContinentValue.setBounds(145, 75, 200, 40);
        mainPanel.add(tfContinentValue);

        final JLabel controlValueText = new JLabel("Control Value: ");
        controlValueText.setBounds(25, 125, 120, 40);
        mainPanel.add(controlValueText);

        tfControlValue = new JTextField();
        tfControlValue.setBounds(145, 125, 200, 40);
        mainPanel.add(tfControlValue);

        final JLabel controlValueInfoText = new JLabel("(0 to 10)");
        controlValueInfoText.setBounds(350, 125, 100, 40);
        mainPanel.add(controlValueInfoText);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 175, 100, 40);
        mainPanel.add(btnAdd);

        btnNext = new JButton("Next");
        btnNext.setBounds(125, 175, 100, 40);
        mainPanel.add(btnNext);
    }

    @Override
    public void addContinentListener(BiConsumer<String, String> listener) {
        btnAdd.addActionListener(e -> listener.accept(tfControlValue.getText(), tfContinentValue.getText()));
    }

    @Override
    public void addShowMapCreateCountryListener(ActionListener listener) {
        btnNext.addActionListener(listener);
    }

    /**
     * This method updates view for which continents belong to
     *
     * @param listOfContinentModel
     */
    private void updateUI(List<ContinentModel> listOfContinentModel) {
        final StringBuilder textAreaText = new StringBuilder("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        if (listOfContinentModel != null) {
            textAreaText.setLength(0);
            for (final ContinentModel continentModel : listOfContinentModel) {
                textAreaText.append(
                        "Continent Name : " + continentModel.getContinentName()
                                + " || Control Value : " + continentModel.getControlValue() + "\n");
            }
        }

        textArea.setText(textAreaText.toString());
    }

    /**
     * Listens to notifyObservers of Observable classes
     */
    @Override
    public void update(Observable obs, Object arg) {
        final List<ContinentModel> listOfContinentModel = ((MapRiskModel) obs).getContinentModelList();

        this.updateUI(listOfContinentModel);
        this.revalidate();
        this.repaint();
    }
}
