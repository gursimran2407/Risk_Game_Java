package com.risk.controller;

import com.risk.model.CardModel;
import com.risk.model.CountryModel;
import com.risk.model.GamePlayModel;
import com.risk.model.strategy.BenevolentPlayerStrategy;
import com.risk.model.strategy.CheaterPlayerStrategy;
import com.risk.model.strategy.RandomPlayerStrategy;
import com.risk.utilities.SaveGame;
import com.risk.utilities.Validation;
import com.risk.view.IAttackView;
import com.risk.view.IFortificationView;
import com.risk.view.IReinforcementView;
import com.risk.view.events.ViewActionEvent;
import com.risk.view.events.ViewActionListener;

import javax.swing.*;

/**
 * In PlayerController, the data flow into model object and updates the view
 * whenever data changes.
 *
 * @version 1.0.0
 *
 */
public class PlayerController implements ViewActionListener {

    /** The game play model. */
    private GamePlayModel gamePlayModel;

    /** The val. */
    private Validation val = new Validation();

    /** The reinforcement view. */
    private IReinforcementView theReinforcementView;

    /** The fortification view. */
    private IFortificationView theFortificationView;

    /** The attack view. */
    private IAttackView theAttackView;

    /**
     * Constructor initializes values and sets the screen too visible.
     *
     * @param gamePlayModel
     *            the game play model
     */
    public PlayerController(GamePlayModel gamePlayModel) {

        this.gamePlayModel = gamePlayModel;
        if (this.gamePlayModel.getGamePhase() == null) {
            if (!val.endOfGame(this.gamePlayModel)) {
                String PlayerType = this.gamePlayModel.getGameMap().getPlayerTurn().getTypePlayer();
                if ("Human".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new HumanPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    theReinforcementView =
                            Environment.getInstance().getViewManager().newReinforcementView(this.gamePlayModel);
                    theReinforcementView.addActionListener(this);
                    theReinforcementView.showView();
                    this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
                    this.gamePlayModel.addObserver(theReinforcementView);
                } else if ("Aggressive".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new AgressivePlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Benevolent".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new BenevolentPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Random".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new RandomPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();
                } else if ("Cheater".equals(PlayerType)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new CheaterPlayerStrategy(this.gamePlayModel));
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
                        .setStrategy(new HumanPlayerStrategy(this.gamePlayModel));
                String Phase = this.gamePlayModel.getGamePhase();
                if ("Reinforcement".equals(Phase)) {
                    this.gamePlayModel.getGameMap().getPlayerTurn()
                            .setStrategy(new HumanPlayerStrategy(this.gamePlayModel));
                    this.gamePlayModel.getGameMap().getPlayerTurn().executeReinforcement();
                    theReinforcementView =
                            Environment.getInstance().getViewManager().newReinforcementView(this.gamePlayModel);
                    theReinforcementView.addActionListener(this);
                    theReinforcementView.showView();
                    this.gamePlayModel.getGameMap().addObserver(theReinforcementView);
                    this.gamePlayModel.addObserver(theReinforcementView);
                } else if ("Attack".equals(Phase)) {
                    theReinforcementView =
                            Environment.getInstance().getViewManager().newReinforcementView(this.gamePlayModel);
                    theReinforcementView.hideView();

                    theAttackView =
                            Environment.getInstance().getViewManager().newAttackView(this.gamePlayModel);
                    theAttackView.addActionListener(this);
                    theAttackView.showView();

                    this.gamePlayModel.deleteObservers();
                    this.gamePlayModel.addObserver(this.theAttackView);
                    this.gamePlayModel.setArmyToMoveText(false);
                    this.gamePlayModel.setCardToBeAssigned(false);
                } else if ("Fortification".equals(Phase)) {
                    theReinforcementView =
                            Environment.getInstance().getViewManager().newReinforcementView(this.gamePlayModel);
                    theReinforcementView.hideView();
                    theAttackView =
                            Environment.getInstance().getViewManager().newAttackView(this.gamePlayModel);
                    theAttackView.hideView();
                    theFortificationView =
                            Environment.getInstance().getViewManager().newFortificationView(this.gamePlayModel);
                    theFortificationView.addActionListener(this);
                    theFortificationView.showView();
                    this.gamePlayModel.addObserver(this.theFortificationView);
                }
            }
            this.gamePlayModel.setGamePhase(null);
        }
    }

    /**
     * This method performs action, by Listening the action event set in view.
     *
     * @param event
     *            the action event
     */

    @Override
    public void actionPerformed(ViewActionEvent event) {
        /* The filename. */
        String filename = null;
        if (IReinforcementView.ACTION_ADD.equals(event.getSource())) {
            if (theReinforcementView.getNumOfTroops() >= 0) {
                int selectedArmies = theReinforcementView.getNumOfTroops();
                CountryModel countryName = theReinforcementView.getCountryModel();
                System.out.println("countryName" + selectedArmies + countryName);
                this.gamePlayModel.setSelectedArmiesToCountries(selectedArmies, countryName);
                this.gamePlayModel.getConsole().append(selectedArmies + " armies added to "
                        + countryName.getCountryName());

            } else {
                this.theReinforcementView.hideView();
                theAttackView =
                        Environment.getInstance().getViewManager().newAttackView(this.gamePlayModel);
                theAttackView.addActionListener(this);
                theAttackView.showView();

                this.gamePlayModel.deleteObservers();
                this.gamePlayModel.addObserver(this.theAttackView);
                this.gamePlayModel.setArmyToMoveText(false);
                this.gamePlayModel.setCardToBeAssigned(false);
            }
        } else if (IReinforcementView.ACTION_ADD_MORE.equals(event.getSource())) {
            int cardID = this.theReinforcementView.getCardId();
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
        } else if (IReinforcementView.ACTION_EXIT_CARD.equals(event.getSource())) {
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
        } else if (IReinforcementView.ACTION_SAVE.equals(event.getSource())) {
            this.gamePlayModel.setGamePhase("Reinforcement");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename.equals("")) {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theReinforcementView.hideView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (IAttackView.ACTION_NEXT.equals(event.getSource())) {
            this.theAttackView.hideView();
            theFortificationView =
                    Environment.getInstance().getViewManager().newFortificationView(this.gamePlayModel);
            theFortificationView.addActionListener(this);
            theFortificationView.showView();
            this.gamePlayModel.addObserver(this.theFortificationView);

        } else if (IAttackView.ACTION_ATTACK_COUNTRY_CHANGED.equals(event.getSource())) {
            this.gamePlayModel
                    .setSelectedAttackComboBoxIndex(this.theAttackView.getAttackCountryIndex());
        } else if (IAttackView.ACTION_DEFEND_COUNTRY_CHANGED.equals(event.getSource())) {
            this.gamePlayModel
                    .setSelectedDefendComboBoxIndex(this.theAttackView.getDefendCountryIndex());
        } else if (IAttackView.ACTION_SINGLE.equals(event.getSource())) {
            this.gamePlayModel.getConsole().append(
                    "This is a Single attack from " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

            this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();

            int attackDice = theAttackView.getNumOfDiceAttack();
            int defendDice = theAttackView.getNumOfDiceDefend();
            CountryModel attackCountry = theAttackView.getAttackCountryModel();
            CountryModel defendCountry = theAttackView.getDefendCountryModel();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.singleStrike(attackDice, attackCountry, defendDice, defendCountry);
            if (val.endOfGame(this.gamePlayModel)) {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theAttackView.hideView();
            }

        } else if (IAttackView.ACTION_ALLOUT.equals(event.getSource())) {
            this.gamePlayModel.getConsole().append(
                    "This is a Allout attack from " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer());

            this.gamePlayModel.getGameMap().getPlayerTurn().executeAttack();
            CountryModel attackCountry = theAttackView.getAttackCountryModel();
            CountryModel defendCountry = theAttackView.getDefendCountryModel();
            this.gamePlayModel.setDefeatedCountry(defendCountry);
            this.gamePlayModel.getConsole()
                    .append("The attacker is " + attackCountry.getCountryName());
            this.gamePlayModel.getConsole()
                    .append("The defender is " + defendCountry.getCountryName());

            this.gamePlayModel.alloutStrike(attackCountry, defendCountry);
            if (val.endOfGame(this.gamePlayModel)) {
                JOptionPane.showOptionDialog(null, "Bravo! You have won! Game is over!", "Valid",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {}, null);
                this.theAttackView.hideView();
            }

        } else if (IAttackView.ACTION_MOVE.equals(event.getSource())) {
            CountryModel attackCountry = theAttackView.getAttackCountryModel();
            int noOfArmiesToBeMoved = theAttackView.getNumOfArmiesToBeMoved();
            CountryModel defendCountry = this.gamePlayModel.getDefeatedCountry();
            this.gamePlayModel.moveArmies(attackCountry, defendCountry, noOfArmiesToBeMoved);
            this.gamePlayModel.getConsole()
                    .append("The player " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                            + " is moving " + noOfArmiesToBeMoved + " from " + attackCountry.getName() + " to "
                            + defendCountry.getName());

        } else if (IAttackView.ACTION_SAVE.equals(event.getSource())) {
            this.gamePlayModel.setGamePhase("Attack");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename.equals("")) {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theAttackView.hideView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (IFortificationView.ACTION_MOVE.equals(event.getSource())) {
            // BFS
            this.gamePlayModel.getGameMap().getPlayerTurn().executeFortification();

            if (val.checkIfValidMove(this.gamePlayModel.getGameMap(),
                    this.theFortificationView.getFromCountryModel(),
                    this.theFortificationView.getToCountryModel())) {

                this.gamePlayModel.getGameMap().setMovingArmies(
                        this.theFortificationView.getNumOfTroops(),
                        this.theFortificationView.getFromCountryModel(),
                        this.theFortificationView.getToCountryModel());
                this.gamePlayModel.getConsole()
                        .append("The player " + this.gamePlayModel.getGameMap().getPlayerTurn().getNamePlayer()
                                + " is moving "
                                + this.theFortificationView.getNumOfTroops() + " from "
                                + this.theFortificationView.getFromCountryModel() + " to "
                                + this.theFortificationView.getToCountryModel());

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
            this.theFortificationView.hideView();
            new GamePlayController(this.gamePlayModel);

        } else if (IFortificationView.ACTION_FROM_COUNTRY_CHANGED.equals(event.getSource())) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.getFromCountryIndex());
        } else if (IFortificationView.ACTION_ITEM_FROM_COUNTRY_CHANGED.equals(event.getSource())) {
            this.gamePlayModel
                    .setSelectedComboBoxIndex(this.theFortificationView.getFromCountryIndex());
        } else if (IFortificationView.ACTION_SAVE.equals(event.getSource())) {
            this.gamePlayModel.setGamePhase("Fortification");
            filename = JOptionPane.showInputDialog("File Name");
            try {
                System.out.println(filename);
                SaveGame save = new SaveGame();
                if (filename == null || filename.equals("")) {
                    save.writeTOJSONFile(this.gamePlayModel, "file");
                } else {
                    save.writeTOJSONFile(this.gamePlayModel, filename);
                }
                JOptionPane.showMessageDialog(null, "Play has been saved");
                new WelcomeScreenController();
                this.theFortificationView.hideView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (IFortificationView.ACTION_NEXT.equals(event.getSource())) {
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
            this.theFortificationView.hideView();
            new GamePlayController(this.gamePlayModel);
        }
    }

}
