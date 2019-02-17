package com.risk.view;

import com.risk.helperInterfaces.ViewInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

/**
 * @author gursimransingh
 */
public class MapCreateContinentView extends JFrame implements ViewInterface {

    public JLabel welcomeLabel;
    public JTextField continentValue;
    public JTextField controlValue;
    public JLabel continentListText;
    public JLabel controlValueText;
    public JLabel controlValueInfoText;
    public JTextArea observerList;
    public JButton nextButton;
    public JButton addButton;
    public JTextArea consoleTextArea;
    public JTextArea consoleMainPanel;
    public JScrollPane consolePanel;
    public JPanel mainPanel;
    JTextArea textArea;

    /**
     * Construction of CreateContinentView
     */
    public MapCreateContinentView() {
        this.setTitle("Create Continent");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 700);
        this.setResizable(false);
        this.setVisible(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        textArea = new JTextArea("Default text", 5, 5);

        welcomeLabel = new JLabel("Please name the Continents you want in the map and their control values");
        welcomeLabel.setBounds(100, 0, 600, 100);

        continentListText = new JLabel("Continent Name: ");
        continentListText.setBounds(100, 100, 200, 40);

        continentValue = new JTextField();
        continentValue.setBounds(200, 100, 200, 40);

        controlValueText = new JLabel("Control Value: ");
        controlValueText.setBounds(100, 200, 200, 40);

        controlValue = new JTextField();
        controlValue.setBounds(200, 200, 200, 40);

        controlValueInfoText = new JLabel("(0 to 10)");
        controlValueInfoText.setBounds(411, 200, 100, 40);

        addButton = new JButton("Add");
        addButton.setBounds(100, 300, 100, 40);

        nextButton = new JButton("Next");
        nextButton.setBounds(200, 300, 100, 40);

        updateScreen(null);
    }

    /**
     * Updates view regarding continents belong to
     *
     * @param listOfContinentModel
     */
    public void updateScreen(List<ContinentsModel> listOfContinentModel) {

        StringBuilder textAreaText = new StringBuilder("------------------------------------------------");

        if (listOfContinentModel == null) {
            textArea.setText(textAreaText.toString());
        } else {
            textAreaText.setLength(0);
            for (int i = 0; i < listOfContinentModel.size(); i++) {
                textAreaText.append("Continent name : " + listOfContinentModel.get(i).getContinentName()
                        + " ,Control Value : " + listOfContinentModel.get(i).getValueControl() + "\n");
            }
        }

        textArea.setText(textAreaText.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        mainPanel.add(textArea);
        textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Continents added list:"));
        textArea.setBounds(520, 0, 260, 650);

        Color main = new Color(230, 230, 255);
        Color secondary = new Color(0, 0, 26);
        textArea.setBackground(main);
        textArea.setForeground(secondary);

        this.add(mainPanel);
        mainPanel.add(welcomeLabel);
        mainPanel.add(addButton);
        mainPanel.add(nextButton);
        mainPanel.add(continentListText);
        mainPanel.add(continentValue);
        mainPanel.add(controlValue);
        mainPanel.add(controlValueText);
        mainPanel.add(controlValueInfoText);

    }

    /**
     * Sets actions to "addButton" and "nextButton"
     *
     * @see app.helper.View#setActionListener(java.awt.event.ActionListener)
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.addButton.addActionListener(actionListener);
        this.nextButton.addActionListener(actionListener);
    }

    /**
     * Update the view based on observer
     *
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        List<ContinentsModel> listOfContinentModel = ((GameMapModel) obs).getContinents();
        this.updateScreen(listOfContinentModel);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
