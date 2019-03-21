package com.risk.controller;

import com.risk.gameplayrequirements.MapValidation;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.view.AttackPhaseView;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * The player class in the controller package will facilitate the data flow into the
 * model object and update the view whenever the data is changed.
 *
 * @author Karan
 */

/* testing the change*/
public class PlayerController implements ActionListener, ItemListener {

    private GamePlayModel gamePlayModel;
    private ReinforcementView reinforcementViewObj;
    private FortificationView forticationviewObj;
    private ArrayList<PlayerModel> listOfPlayers = new ArrayList<>();
    private int noOfPlayers;
    private MapValidation val = new MapValidation();
    private AttackPhaseView attackPhaseView;
    private FortificationView fortificationView;

    /**
     * Constructor for initializing values and setting the screen visibility
     * @param gamePlayModel Object of model class
     */
    public PlayerController(GamePlayModel gamePlayModel)
    {
        this.gamePlayModel = gamePlayModel;
        this.gamePlayModel.getConsoleText()
                .append("Initiating reinforcement for" + gamePlayModel.getGameMap().getPlayerTurn().getPlayerName());
        reinforcement();
    }

    /**
     * This is the method that is required if we implement the Action Listener. This method will perform the action
     * after listening to the action event set in the view.
     *
     * @param actionEvent listens to event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.theReinforcementView.addButton)) {
            int selectedArmies = 0;
            if (theReinforcementView.numOfTroopsComboBox.getSelectedItem() != null) {
                selectedArmies = (int) theReinforcementView.numOfTroopsComboBox.getSelectedItem();
                CountryModel countryName = (CountryModel) theReinforcementView.countryListComboBox.getSelectedItem();
                System.out.println("countryName" + selectedArmies + countryName);
                this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, countryName);
            } else {
                this.theReinforcementView.dispose();
                attack();

            }
        } else if (actionEvent.getSource().equals(this.theReinforcementView.addMoreButton)) {
            int cardID = Integer.parseInt(this.theReinforcementView.cardIdField.getText());
            int cardValue = 0;
            CardModel card = new CardModel();
            for (int i = 0; i < this.gamePlayModel.getCards().size(); i++) {
                if (cardID == this.gamePlayModel.getCards().get(i).getCardId()) {
                    cardValue = this.gamePlayModel.getCards().get(i).getCardValue();
                }
            }
            card.setCardId(cardID);
            card.setCardValue(cardValue);
            this.gamePlayModel.getGameMap().getPlayerTurn()
                    .setremainTroop(this.gamePlayModel.getGameMap().getPlayerTurn().getremainTroop() + cardValue);
            for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
                if (gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                        .equals(gamePlayModel.getPlayers().get(i).getNamePlayer())) {
                    this.gamePlayModel.getPlayers().get(i).removeCard(card);
                }
            }
            this.gamePlayModel.getCards().add(card);
            this.gamePlayModel.callObservers();
        } else if (actionEvent.getSource().equals(this.theReinforcementView.exitCardButton)) {
            for (int i = 0; i < this.gamePlayModel.getPlayers().size(); i++) {
                if (gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                        .equals(gamePlayModel.getPlayers().get(i).getNamePlayer())) {
                    if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size() >= 5) {
                        this.gamePlayModel.getGameMap().getPlayerTurn().setShowReinforcementCard(true);
                        gamePlayModel.getPlayers().get(i).setShowReinforcementCard(true);
                        JOptionPane.showOptionDialog(null,
                                "Maximum 5 card is allowed. Please select card id to reimburse", "Reimburse card",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {},
                                null);
                    } else {
                        this.gamePlayModel.getGameMap().getPlayerTurn().setShowReinforcementCard(false);
                        gamePlayModel.getPlayers().get(i).setShowReinforcementCard(false);
                        this.gamePlayModel.callObservers();
                    }
                }
            }
        } else if (actionEvent.getSource().equals(this.theAttackView.nextButton)) {
            this.theAttackView.dispose();
            fortification();
        } else if (actionEvent.getSource().equals(this.theAttackView.attackCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedAttackComboBoxIndex(this.theAttackView.attackCountryListComboBox.getSelectedIndex());
        } else if (actionEvent.getSource().equals(this.theAttackView.defendCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedDefendComboBoxIndex(this.theAttackView.defendCountryListComboBox.getSelectedIndex());
        } else if (actionEvent.getSource().equals(this.theAttackView.SingleButton)) {

            int attackDice = (int) theAttackView.numOfDiceAttackComboBox.getSelectedItem();
            int defendDice = (int) theAttackView.numOfDiceDefendComboBox.getSelectedItem();
            CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
            CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.singleStrike(attackDice, attackCountry, defendDice, defendCountry);
            if (val.endOfGame(this.gamePlayModel) == true) {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theAttackView.dispose();
            }

        } else if (actionEvent.getSource().equals(this.theAttackView.alloutButton)) {

            CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
            CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.alloutStrike(attackCountry, defendCountry);
            if (val.endOfGame(this.gamePlayModel) == true) {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theAttackView.dispose();
            }

        } else if (actionEvent.getSource().equals(this.theAttackView.moveButton)) {

            CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
            int noOfArmiesToBeMoved = (int) theAttackView.numOfArmiesToBeMovedComboBox.getSelectedItem();
            CountryModel defendCountry = this.gamePlayModel.getDefeatedCountry();
            this.gamePlayModel.moveArmies(attackCountry, defendCountry, noOfArmiesToBeMoved);

        } else if (actionEvent.getSource().equals(this.theFortificationView.moveButton)) {
            // BFS

            if (val.checkIfValidMove(this.gamePlayModel.getGameMap(),
                    (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                    (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem())) {
                this.gamePlayModel.getGameMap().setMovingArmies(
                        (Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem());
            }

            int index = this.gamePlayModel.getGameMap().getPlayerIndex();

            this.gamePlayModel.moveDeck();

            index++;
            if (this.gamePlayModel.getPlayers().size() > index) {
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            } else {
                index = 0;
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            }
            if (val.endOfGame(this.gamePlayModel) == false) {
                new GamePlayController(this.gamePlayModel);
                this.theFortificationView.dispose();
            } else {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theFortificationView.dispose();
            }
        } else if (actionEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        }

    }
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.fortificationView.fromCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.fortificationView.fromCountryListComboBox.getSelectedIndex());
        }
    }

    /**
     * This method will call the reinforcement phase
     */
    private void reinforcement()
    {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.callObservers();
        this.gamePlayModel.getConsoleText().
        append("Initiating reinforcement for" + gamePlayModel.getGameMap().getPlayerTurn().getPlayerName());

        this.gamePlayModel.getGameMap().getPlayerTurn().setRemainingNumberOfArmies(this.gamePlayModel.numberOfCountries()
        + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));

        if(gamePlayModel.getGameMap().getPlayerTurn().getPlayerCards().size() > 0)
        {
            this.gamePlayModel.getConsoleText().append("Please fond the list of Cards: Reinforcement View \n");
            for(int i = 0; i< gamePlayModel.getGameMap().getPlayerTurn().getPlayerCards().size();i++)
            {
               this.gamePlayModel.getConsoleText().append(gamePlayModel.getGameMap().getPlayerTurn().getPlayerCards().get(i).getCardId()+ "\n");
            }
            this.gamePlayModel.getGameMap().getPlayerTurn().setReinforcementCard(true);
        }
        reinforcementViewObj = new ReinforcementView(this.gamePlayModel);
        reinforcementViewObj.setActionListener(this);
        reinforcementViewObj.setVisible(true);

        this.gamePlayModel.getGameMap().addObserver(reinforcementViewObj);
        this.gamePlayModel.addObserver(reinforcementViewObj);
        for(int i = 0; i< noOfPlayers; i++)
        {
            this.listOfPlayers.get(i).addObserver(this.reinforcementViewObj);
        }
    }

    /**
     * This method is to call fortification phase
     */
    public void fortification()
    {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText().append("Fortification phase initializing" + gamePlayModel.getGameMap().getPlayerTurn().getPlayerName());

        forticationviewObj = new FortificationView(this.gamePlayModel);
        forticationviewObj.setActionListener(this);
        forticationviewObj.setItemListener(this);
        forticationviewObj.setVisible(true);
        this.gamePlayModel.addObserver(this.forticationviewObj);


    }

    /**
     * This method is to call attack phase
     */
    public void attack() {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getPlayerName() + "'s attack");

        attackPhaseView = new AttackPhaseView(this.gamePlayModel);
        this.gamePlayModel.setArmyToMoveText(false);
        this.gamePlayModel.setCardToBeAssigned(false);
        attackPhaseView.setActionListener(this);
        attackPhaseView.setVisible(true);
        this.gamePlayModel.deleteObservers();
        this.gamePlayModel.addObserver(this.attackPhaseView);
    }


    /**
     * Item Listener.
     *
     * @param itemEvent the item event
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        }

    }
}
