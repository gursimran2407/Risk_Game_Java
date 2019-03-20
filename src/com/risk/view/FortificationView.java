
package com.risk.view;
import com.risk.helperInterfaces.ViewInterface;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.MapRiskModel;
import com.risk.model.PlayerModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;


/**
 * "FortificationView" class represents a view object of fortification phase.
 *
 * @author gursimran singh
 *
 */
public class FortificationView extends JFrame implements ViewInterface {

    public MapRiskModel gameMapModel;
    public PlayerModel playerModel;
    public GamePlayModel gamePlayModel;

    public JPanel welcomePanel;
    public JPanel graphicPanel;

    public JPanel consoleMainPanel;
    public JScrollPane consolePanel;
    public JTextArea consoleTextArea;

    public JLabel welcomeLabel;
    public JLabel noOfTroopsLabel;

    public JComboBox<Integer> numOfTroopsComboBox;
    public JButton moveButton;
    public JLabel listOfCountriesLabel;

    public JLabel fromCountryListLabel;
    public JLabel toCountryListLabel;
    public JComboBox<Object> fromCountryListComboBox;
    public JComboBox<Object> toCountryListComboBox;
    public Object[] fromCountryListArray;
    private CountryViewRenderer fromCountriesViewRenderer;
    public Object[] toCountryListArray;
    public JButton[] button;
    /**
     * The to countries view renderer.
     */
    private CountryViewRenderer toCountriesViewRenderer;
    /** The action listener. */
    private ActionListener actionListener;

    /**
     * Constructor of FortificationView
     *
     * @param gamePlayModel
     */
    public FortificationView(GamePlayModel gamePlayModel) {
        this.gameMapModel = gamePlayModel.getGameMap();
        this.setTitle("Fortification Phase");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setSize(1600, 840);
        this.setResizable(false);
        this.setVisible(false);

        welcomePanel = new JPanel();
        graphicPanel = new JPanel();
        this.add(graphicPanel);
        graphicPanel.setSize(1200, 650);
        graphicPanel.setBackground(Color.WHITE);
        graphicPanel.setLayout(null);

        this.consoleMainPanel = new JPanel();
        this.consoleMainPanel.setBorder(new BevelBorder(1));
        this.consoleTextArea = new JTextArea("Fortification Phase!!!\n", 10, 500);
        this.consoleTextArea.setEditable(false);
        this.consoleTextArea.setFocusable(false);
        this.consoleTextArea.setVisible(true);
        this.consoleTextArea.setForeground(Color.WHITE);
        this.consoleTextArea.setBackground(Color.BLACK);
        DefaultCaret caret = (DefaultCaret) this.consoleTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
        this.consolePanel = new JScrollPane(this.consoleTextArea);
        this.consolePanel.setPreferredSize(new Dimension(1580, 130));
        this.consoleMainPanel.add(this.consolePanel, BorderLayout.WEST);
        this.getContentPane().add(this.consoleMainPanel, BorderLayout.SOUTH);

        this.add(welcomePanel);
        this.playerModel = this.gameMapModel.getPlayerTurn();
        this.moveButton = new JButton("Move");
        updateWindow(gamePlayModel, this.playerModel);
        welcomePanel.setLayout(null);
        graphicPanel.setLayout(null);
    }

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            // get color by hex or octal value
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            // if we can't decode lets try to get it by name
            try {
                // try to get a color by name using reflection
                final Field f = Color.class.getField(value);

                return (Color) f.get(null);
            } catch (Exception ce) {
                // if we can't get any color return black
                return Color.black;
            }
        }
    }

    /**
     * Updates the window based on new data in fortification phase
     *
     * @param gamePlayModel
     * @param playerModel
     */
    public void updateWindow(GamePlayModel gamePlayModel, PlayerModel playerModel) {

        welcomePanel.removeAll();
        graphicPanel.removeAll();
        Font largeFont = new Font("Serif", Font.BOLD, 18);
        Font mediumFont = new Font("Serif", Font.BOLD, 14);
        Font smallFont = new Font("Serif", Font.BOLD, 12);

        this.gamePlayModel = gamePlayModel;
        this.gameMapModel = gamePlayModel.getGameMap();
        this.playerModel = playerModel;

        this.welcomeLabel = new JLabel("It's " + playerModel.getPlayerName() + "'s turn");
        welcomeLabel.setBounds(1300, 80, 300, 25);
        welcomeLabel.setFont(largeFont);
        welcomePanel.add(welcomeLabel);

        if (this.gamePlayModel.getConsoleText() != null) {
            this.consoleTextArea.setText(this.gamePlayModel.getConsoleText().toString());
        }

        this.fromCountryListLabel = new JLabel("From Country :");
        fromCountryListLabel.setBounds(1300, 120, 150, 25);
        welcomePanel.add(this.fromCountryListLabel);

        // from country comboBox
        ArrayList<CountryModel> fromListOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.gameMapModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName().equals(this.gameMapModel.getCountryModelList().get(i).getRulerName())
                    && this.gameMapModel.getCountryModelList().get(i).getNumberofArmies() >= 2) {
                fromListOfCountries.add(this.gameMapModel.getCountryModelList().get(i));
            }
        }
        fromCountriesViewRenderer = new CountryViewRenderer();
        fromCountryListArray = fromListOfCountries.toArray();

        fromCountryListComboBox = new JComboBox(fromCountryListArray);
        fromCountryListComboBox.setSelectedIndex(gamePlayModel.getSelectedComboBoxIndex());
        welcomePanel.add(this.fromCountryListComboBox);

        this.toCountryListLabel = new JLabel("To Country :");
        toCountryListLabel.setBounds(1300, 240, 150, 25);
        welcomePanel.add(this.toCountryListLabel);

        // to country comboBox
        ArrayList<CountryModel> toListOfCountries = new ArrayList<CountryModel>();
        for (int i = 0; i < this.gameMapModel.getCountryModelList().size(); i++) {
            if (playerModel.getPlayerName()
                    .equals(this.gameMapModel.getCountryModelList().get(i).getRulerName())) {
                toListOfCountries.add(this.gameMapModel.getCountryModelList().get(i));
            }
        }
        toCountriesViewRenderer = new CountryViewRenderer();
        toCountryListArray = toListOfCountries.toArray();
        toCountryListComboBox = new JComboBox(toCountryListArray);
        welcomePanel.add(this.toCountryListComboBox);

        if (toCountryListArray.length > 0) {
            toCountryListComboBox.setRenderer(toCountriesViewRenderer);
        }
        toCountryListComboBox.setBounds(1300, 270, 150, 25);
        welcomePanel.add(toCountryListComboBox);

        this.noOfTroopsLabel = new JLabel("Number of Troops :");
        noOfTroopsLabel.setBounds(1300, 180, 150, 25);
        welcomePanel.add(noOfTroopsLabel);

        CountryModel countryName = (CountryModel) this.fromCountryListComboBox.getSelectedItem();
        System.out.println("country name " + countryName.getNumberofArmies());
        Integer[] troops = new Integer[countryName.getNumberofArmies() - 1];
        for (int i = 0; i < (countryName.getNumberofArmies() - 1); i++) {
            troops[i] = i + 1;
        }

        numOfTroopsComboBox = new JComboBox(troops);
        numOfTroopsComboBox.setBounds(1300, 210, 150, 25);
        welcomePanel.add(numOfTroopsComboBox);

        if (fromCountryListArray.length > 0) {
            fromCountryListComboBox.setRenderer(fromCountriesViewRenderer);
        }
        fromCountryListComboBox.setBounds(1300, 150, 150, 25);
        welcomePanel.add(fromCountryListComboBox);

        this.moveButton.setBounds(1300, 330, 150, 25);
        welcomePanel.add(this.moveButton);

        int n = this.gameMapModel.getCountryModelList().size();
        button = new JButton[n];

        PlayerModel pm = new PlayerModel();
        CountryModel cm = new CountryModel();

        for (int i = 0; i < gameMapModel.getCountryModelList().size(); i++) {

            button[i] = new JButton();
            button[i].setText(gameMapModel.getCountryModelList().get(i).getCountryName().substring(0, 3));
            button[i].setBackground(gameMapModel.getCountryModelList().get(i).getBackgroundColor());
            button[i].setToolTipText("Troops: " + gameMapModel.getCountryModelList().get(i).getNumberofArmies());
            cm = gameMapModel.getCountryModelList().get(i);
            pm = gamePlayModel.getPlayer(cm);
            Border border = BorderFactory.createLineBorder(pm.getPlayerColor(), 3);

            button[i].setBorder(border);
            button[i].setOpaque(true);
            button[i].setBounds(gameMapModel.getCountryModelList().get(i).getXPosition() * 2,
                    gameMapModel.getCountryModelList().get(i).getYPosition() * 2, 50, 50);

            graphicPanel.add(button[i]);
        }
        if (null != this.actionListener) {
            this.setActionListener(this.actionListener);
        }

    }

    /**
     * Paint the button and links in the panel
     *
     * @see java.awt.Window#paint(java.awt.Graphics)
     */
    public void paint(final Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Point[] connectorPoints = new Point[this.gameMapModel.getCountryModelList().size()];

        for (int i = 0; i < this.gameMapModel.getCountryModelList().size(); i++) {
            connectorPoints[i] = SwingUtilities.convertPoint(this.gameMapModel.getCountryModelList().get(i), 0, 0, this);

        }

        for (int k = 0; k < this.gameMapModel.getCountryModelList().size(); k++) {
            if (this.gameMapModel.getCountryModelList().get(k).getConnectedCountryList() != null) {
                ArrayList<CountryModel> neighbourCountries = (ArrayList<CountryModel>) this.gameMapModel.getCountryModelList()
                        .get(k).getConnectedCountryList();

                for (int j = 0; j < neighbourCountries.size(); j++) {
                    for (int i = 0; i < this.gameMapModel.getCountryModelList().size(); i++)
                        if (neighbourCountries.get(j).equals(this.gameMapModel.getCountryModelList().get(i)))
                            g2.drawLine(connectorPoints[i].x + 25, connectorPoints[i].y + 25, connectorPoints[k].x + 25,
                                    connectorPoints[k].y + 25);

                }
            }
        }

    }

    /**
     *
     */
    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof GamePlayModel) {
            this.gamePlayModel = (GamePlayModel) obs;
        } else if (obs instanceof GamePlayModel) {
            this.gameMapModel = (MapRiskModel) obs;
        } else if (obs instanceof GamePlayModel) {
            this.playerModel = (PlayerModel) obs;
        }
        this.updateWindow(this.gamePlayModel, this.playerModel);
        this.revalidate();
        this.repaint();

    }

    /**
     * Sets "moveButton" action
     */
    @Override
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        this.moveButton.addActionListener(actionListener);
        this.fromCountryListComboBox.addActionListener(actionListener);
    }

    public void setItemListener(ItemListener itemListener) {
        this.fromCountryListComboBox.addItemListener(itemListener);

    }

    /**
     * Provides a dynamic comboBox of country names
     *
     * @author GROUPE-35
     */
    public class CountryViewRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            CountryModel countryModel = (CountryModel) value;
            if (countryModel != null)
                setText(countryModel.getCountryName());

            return this;
        }
    }

}