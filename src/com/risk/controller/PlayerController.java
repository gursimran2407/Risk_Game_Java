package com.risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import com.risk.model.CardModel;
import com.risk.model.CountryModel;
import com.risk.utilities.SaveGame;
import com.risk.model.GamePlayModel;
import com.risk.utilities.Validation;
import com.risk.view.AttackView;
import com.risk.view.FortificationView;
import com.risk.view.ReinforcementView;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @author Hamid
 * @version 1.0.0
 *
 */
public class PlayerController implements ActionListener, ItemListener {

    private GamePlayModel gamePlayModel;
    private Validation val = new Validation();
    private ReinforcementView theReinforcementView;
    private FortificationView theFortificationView;
    private AttackView theAttackView;
    private String filename = null;

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayModel
     *            the game play model
     */
    public PlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        if (this.gamePlayModel.getGamePhase() == null) {
            if (val.endOfGame(this.gamePlayModel) == false) {
                String PlayerType = this.gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer();
                if ("Human".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new HumanPlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    theReinforcementView = new ReinforcementView(this.gamePlayModel);
                    theReinforcementView.setVisible(true);
                    theReinforcementView.setActionListener(this);
                    this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
                    this.gamePlayModel.addObserver(theReinforcementView);
                } else if ("Aggressive".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new AgressivePlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Benevolent".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new BenevolentPlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Random".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new RandomPlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Cheater".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new CheaterPlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                }
                if (!"Human".equals(PlayerType)) {
                    int index = this.gamePlayModel.getGameMap().getPlayerIndex();

                    index++;
                    if (this.gamePlayModel.getPlayers().size() > index) {
                        this.gamePlayModel.getGameMap().setPlayerIndex(index);
                        this.gamePlayModel.getPlayers().get(index).callObservers();
                    } else {
                        index = 0;
                        this.gamePlayModel.getGameMap().setPlayerIndex(index);
                        this.gamePlayModel.getPlayers().get(index).callObservers();
                    }
                    new GamePlayController(this.gamePlayModel);
                }
            } else {
                String nameOfWinner = val.determineWinner(this.gamePlayModel);
                if ("draw".equals(nameOfWinner)) {
                    System.out.println(" Game is draw ");
                    JOptionPane.showOptionDialog(null, "The game is draw", "Valid", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                } else {
                    System.out.println(nameOfWinner + " is winner ");
                    JOptionPane.showOptionDialog(null,
                            "Bravo! You have won! Game is over!" + nameOfWinner + "is the winner", "Valid",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                }
            }
        } else {
            String PlayerType = this.gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer();
            if ("Human".equals(PlayerType)) {
                this.gamePlayModel.getGameMap().getPlayerTurn()
                        .setStrategy(new HumanPlayerController(this.gamePlayModel));
                String Phase = this.gamePlayModel.getGamePhase();
                if ("Reinforcement".equals(Phase)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new HumanPlayerController(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    theReinforcementView = new ReinforcementView(this.gamePlayModel);
                    theReinforcementView.setVisible(true);
                    theReinforcementView.setActionListener(this);
                    this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
                    this.gamePlayModel.addObserver(theReinforcementView);
                } else if ("Attack".equals(Phase)) {
                    theReinforcementView = new ReinforcementView(this.gamePlayModel);
                    theReinforcementView.dispose();
                    theAttackView = new AttackView(this.gamePlayModel);
                    theAttackView.setActionListener(this);
                    theAttackView.setVisible(true);
                    this.gamePlayModel.deleteObservers();
                    this.gamePlayModel.addObserver(this.theAttackView);
                    this.gamePlayModel.setArmyToMoveText(false);
                    this.gamePlayModel.setCardToBeAssigned(false);
                } else if ("Fortification".equals(Phase)) {
                    theReinforcementView = new ReinforcementView(this.gamePlayModel);
                    theReinforcementView.dispose();
                    theAttackView = new AttackView(this.gamePlayModel);
                    theAttackView.dispose();
                    theFortificationView = new FortificationView(this.gamePlayModel);
                    theFortificationView.setActionListener(this);
                    theFortificationView.setItemListener(this);
                    theFortificationView.setVisible(true);
                    this.gamePlayModel.addObserver(this.theFortificationView);
                }
            }
            this.gamePlayModel.setGamePhase(null);
        }
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param actionEvent
     *            the action event
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
                this.gamePlayModel.getConsole().append(selectedArmies + " armies added to "
                        + countryName.getCountryName());

            } else {
                this.theReinforcementView.dispose();
                theAttackView = new AttackView(this.gamePlayModel);
                theAttackView.setActionListener(this);
                theAttackView.setVisible(true);
                this.gamePlayModel.deleteObservers();
                this.gamePlayModel.addObserver(this.theAttackView);
                this.gamePlayModel.setArmyToMoveText(false);
                this.gamePlayModel.setCardToBeAssigned(false);
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
                    this.gamePlayModel.getConsole()
                            .append(gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer() + " is reimbursing card"
                                    + card.getCardId() + " and gets " + card.getCardValue() + " armies ");
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
        } else if (actionEvent.getSource().equals(this.theReinforcementView.saveButton)) {
            this.gamePlayModel.setGamePhase("Reinforcement");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename == "") {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theReinforcementView.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource().equals(this.theAttackView.nextButton)) {
            this.theAttackView.dispose();
            theFortificationView = new FortificationView(this.gamePlayModel);
            theFortificationView.setActionListener(this);
            theFortificationView.setItemListener(this);
            theFortificationView.setVisible(true);
            this.gamePlayModel.addObserver(this.theFortificationView);

        } else if (actionEvent.getSource().equals(this.theAttackView.attackCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedAttackComboBoxIndex(this.theAttackView.attackCountryListComboBox.getSelectedIndex());
        } else if (actionEvent.getSource().equals(this.theAttackView.defendCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedDefendComboBoxIndex(this.theAttackView.defendCountryListComboBox.getSelectedIndex());
        } else if (actionEvent.getSource().equals(this.theAttackView.SingleButton)) {
            this.gamePlayModel.getConsole().append(
                    "This is a Single attack from " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

            this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();

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
            this.gamePlayModel.getConsole().append(
                    "This is a Allout attack from " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

            this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
            CountryModel attackCountry = (CountryModel) theAttackView.attackCountryListComboBox.getSelectedItem();
            CountryModel defendCountry = (CountryModel) theAttackView.defendCountryListComboBox.getSelectedItem();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.getConsole()
                    .append("The attacker is " + attackCountry.getCountryName());
            this.gamePlayModel.getConsole()
                    .append("The defender is " + defendCountry.getCountryName());

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
            this.gamePlayModel.getConsole()
                    .append("The player " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                            + " is moving " + noOfArmiesToBeMoved + " from " + attackCountry.getName() + " to "
                            + defendCountry.getName());

        } else if (actionEvent.getSource().equals(this.theAttackView.saveButton)) {
            this.gamePlayModel.setGamePhase("Attack");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename == "") {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theAttackView.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource().equals(this.theFortificationView.moveButton)) {
            // BFS
            this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();

            if (val.checkIfValidMove(this.gamePlayModel.getGameMap(),
                    (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                    (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem())) {

                this.gamePlayModel.getGameMap().setMovingArmies(
                        (Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.fromCountryListComboBox.getSelectedItem(),
                        (CountryModel) this.theFortificationView.toCountryListComboBox.getSelectedItem());
                this.gamePlayModel.getConsole()
                        .append("The player " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                                + " is moving "
                                + (Integer) this.theFortificationView.numOfTroopsComboBox.getSelectedItem() + " from "
                                + this.theFortificationView.fromCountryListComboBox.getSelectedItem() + " to "
                                + this.theFortificationView.toCountryListComboBox.getSelectedItem());

            }

            this.gamePlayModel.moveDeck();
            int index = this.gamePlayModel.getGameMap().getPlayerIndex();

            index++;
            if (this.gamePlayModel.getPlayers().size() > index) {
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            } else {
                index = 0;
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            }
            this.theFortificationView.dispose();
            new GamePlayController(this.gamePlayModel);

        } else if (actionEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        } else if (actionEvent.getSource().equals(this.theFortificationView.saveButton)) {
            this.gamePlayModel.setGamePhase("Fortification");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename == "") {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theFortificationView.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource().equals(this.theFortificationView.nextButton)) {
            int index = this.gamePlayModel.getGameMap().getPlayerIndex();

            index++;
            if (this.gamePlayModel.getPlayers().size() > index) {
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            } else {
                index = 0;
                this.gamePlayModel.getGameMap().setPlayerIndex(index);
                this.gamePlayModel.getPlayers().get(index).callObservers();
            }
            this.theFortificationView.dispose();
            new GamePlayController(this.gamePlayModel);
        }

    }

    /**
     * Item Listener.
     *
     * @param itemEvent
     *            the item event
     * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource().equals(this.theFortificationView.fromCountryListComboBox)) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.fromCountryListComboBox.getSelectedIndex());
        }

    }
}
