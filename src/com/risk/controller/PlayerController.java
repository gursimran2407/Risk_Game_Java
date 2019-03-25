package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.risk.model.CardModel;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.PlayerModel;
import com.risk.utilities.Validation;
import com.risk.view.AttackView;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author KaranPannu
 * @version 1.0.0
 *
 */
public class PlayerController implements ActionListener, ItemListener {

    /** The game play model. */
    private GamePlayModel gamePlayModel;

    /** The reinforcement view. */
    private ReinforcementView theReinforcementView;

    /** The fortification view. */
    private FortificationView theFortificationView;

    /** The attack view. */
    private AttackView theAttackView;

    /** The list of players. */
    private List<PlayerModel> listOfPlayers = new ArrayList<>();

    /** The no of players. */
    private int noOfPlayers;

    /** The val. */
    private Validation val = new Validation();

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayModel the game play model
     */
    public PlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        this.gamePlayModel.getConsoleText()
                .append("Initiating reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());
        reinforcement();
    }

    /**
     * This method is called in reinforcement phase.
     *
     */
    private void reinforcement() {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.callObservers();
        this.gamePlayModel.getConsoleText()
                .append("Initiating Reinforcement for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        this.gamePlayModel.getGameMap().getPlayerTurn().setremainTroop(this.gamePlayModel.numberOfCountries()
                + this.gamePlayModel.continentCovered(gamePlayModel.getGameMap().getPlayerTurn()));
        if (gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size() > 0) {
            this.gamePlayModel.getConsoleText().append("\n Reinforcement View - Please find the list of Cards: \n");
            for (int i = 0; i < gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().size(); i++) {
                this.gamePlayModel.getConsoleText()
                        .append(gamePlayModel.getGameMap().getPlayerTurn().getOwnedCards().get(i).getCardId() + "\n ");
            }
            this.gamePlayModel.getGameMap().getPlayerTurn().setShowReinforcementCard(true);
        }
        theReinforcementView = new ReinforcementView(this.gamePlayModel);
        theReinforcementView.setActionListener(this);
        theReinforcementView.setVisible(true);

        this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
        this.gamePlayModel.addObserver(theReinforcementView);
        for (int i = 0; i < noOfPlayers; i++) {
            this.listOfPlayers.get(i).addObserver(this.theReinforcementView);
        }

    }

    /**
     * This method is called in fortification phase.
     */
    private void fortification() {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating Fortification for " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

        theFortificationView = new FortificationView(this.gamePlayModel);
        theFortificationView.setActionListener(this);
        theFortificationView.setItemListener(this);
        theFortificationView.setVisible(true);
        this.gamePlayModel.addObserver(this.theFortificationView);
    }

    /**
     * This method is called in attack phase.
     */
    private void attack() {
        this.gamePlayModel.getConsoleText().setLength(0);
        this.gamePlayModel.getConsoleText()
                .append("Initiating " + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + "'s attack");

        theAttackView = new AttackView(this.gamePlayModel);
        this.gamePlayModel.setArmyToMoveText(false);
        this.gamePlayModel.setCardToBeAssigned(false);
        theAttackView.setActionListener(this);
        theAttackView.setVisible(true);
        this.gamePlayModel.deleteObservers();
        this.gamePlayModel.addObserver(this.theAttackView);
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent the action event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.theReinforcementView.addButton)) {
            if (theReinforcementView.numOfTroopsComboBox.getSelectedItem() != null) {
                int selectedArmies = (int) theReinforcementView.numOfTroopsComboBox.getSelectedItem();
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

            this.gamePlayModel.getConsoleText().append(
                    "\nPlayer '" + gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() +
                            "' will get the card '" + card.getCardId() + "' on the end of the turn\n");
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
            if (val.endOfGame(this.gamePlayModel)) {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theAttackView.dispose();
            }

        } else if (actionEvent.getSource().equals(this.theAttackView.alloutButton)) {

            CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
            CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.alloutStrike(attackCountry, defendCountry);
            if (val.endOfGame(this.gamePlayModel)) {
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
                final CountryModel fromCountry = (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem();
                final CountryModel toCountry = (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem();
                final Integer noOfTroops = (Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem();

                this.gamePlayModel.getGameMap().setMovingArmies(noOfTroops, fromCountry, toCountry);

                this.gamePlayModel.getConsoleText().append(
                        "\n'" + noOfTroops + "' armies moved from country '" + fromCountry + "' to country '" + toCountry + "' \n");
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
            if (!val.endOfGame(this.gamePlayModel)) {
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
