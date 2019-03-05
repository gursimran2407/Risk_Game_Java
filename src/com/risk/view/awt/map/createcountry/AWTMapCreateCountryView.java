package com.risk.view.awt.map.createcountry;

import com.risk.model.ContinentModel;
import com.risk.model.CountryModel;
import com.risk.model.MapRiskModel;
import com.risk.view.awt.AWTAbstractView;
import com.risk.view.map.createcountry.IMapCreateCountryView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.function.BiConsumer;

/**
 * AWTMapCreateCountryView class is representing a view object for creating a country
 * view Properties are labels, text fields, buttons, combo-boxes, a  pane and a panel
 *
 * @author Namita Faujdar
 */

public class AWTMapCreateCountryView extends AWTAbstractView implements IMapCreateCountryView {

    private JTextField tfCountryValue = null;
    private JComboBox<ContinentModel> cbxContinentList = null;
    private JButton btnNext = null;
    private JButton btnAdd = null;
    private JTextArea textArea = null;

    /**
     * It creates country map
     *
     * @param listOfContinents continents list
     */
    public AWTMapCreateCountryView(final List<ContinentModel> listOfContinents) {
        createUI(listOfContinents);
        updateScreen(null);
    }

    private void createUI(final List<ContinentModel> listOfContinents) {
        this.setTitle("Create Country");
        this.setName("RISK GAME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setVisible(false);

        final JPanel pnlRoot = new JPanel();
        pnlRoot.setLayout(null);
        this.add(pnlRoot);

        textArea = new JTextArea("Default text", 5, 5);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Countries added list:"));
        textArea.setBounds(520, 15, 260, 450);
        textArea.setBackground(new Color(230, 230, 255)); // sets the background color
        textArea.setForeground(new Color(0, 0, 26));
        pnlRoot.add(textArea);

        final JLabel welcomeLabel = new JLabel("Please add the Countries in the Continents you have created:");
        welcomeLabel.setBounds(25, 0, 600, 100);
        pnlRoot.add(welcomeLabel);

        final JLabel countryListText = new JLabel("Country Name: ");
        countryListText.setBounds(25, 75, 120, 40);
        pnlRoot.add(countryListText);

        tfCountryValue = new JTextField();
        tfCountryValue.setBounds(145, 75, 200, 40);
        pnlRoot.add(tfCountryValue);

        final JLabel continentNameLabel = new JLabel("Continent Name: ");
        continentNameLabel.setBounds(25, 125, 120, 40);
        pnlRoot.add(continentNameLabel);

        final ContinentModel[] continentListArray = listOfContinents.toArray(new ContinentModel[0]);
        cbxContinentList = new JComboBox<>(continentListArray);
        if (continentListArray.length > 0) {
            cbxContinentList.setRenderer(new CountryViewRenderer());
        }
        cbxContinentList.setBounds(145, 125, 200, 40);
        pnlRoot.add(cbxContinentList);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 175, 100, 40);
        pnlRoot.add(btnAdd);

        btnNext = new JButton("Next");
        btnNext.setBounds(125, 175, 100, 40);
        pnlRoot.add(btnNext);

    }

    /**
     * UpdateScreen pushes screen update after creating a country
     *
     * @param listOfCountryModel countries list
     */
    private void updateScreen(List<CountryModel> listOfCountryModel) {
        StringBuilder textAreaText = new StringBuilder("------------------------------------------------");

        if (listOfCountryModel != null) {
            textAreaText.setLength(0);
            for(CountryModel obj : listOfCountryModel){
                textAreaText.append("Country: " + obj.getCountryName() + " ,Continent: "
                        + obj.getContinentName() + "\n");
            }
        }

        textArea.setText(textAreaText.toString());
    }

    @Override
    public void addCountryListener(BiConsumer<String, ContinentModel> listener) {
        btnAdd.addActionListener(e -> listener.accept(tfCountryValue.getText(), (ContinentModel) cbxContinentList.getSelectedItem()));
    }

    @Override
    public void addShowMapCountryConnectListener(ActionListener listener) {
        btnNext.addActionListener(listener);
    }

    /**
     * Listens to notifyObservers of Observable classes
     */
    @Override
    public void update(Observable obs, Object arg) {
        final List<CountryModel> listOfCountryModel = ((MapRiskModel) obs).getCountryModelList();

        this.updateScreen(listOfCountryModel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Inside, getter method that provides us a map model corresponding to a map name
     */
    public class CountryViewRenderer extends BasicComboBoxRenderer {

        public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            final ContinentModel map_model = (ContinentModel) value;
            if (map_model != null) {
                setText(map_model.getContinentName());
            }

            return this;
        }
    }
}
