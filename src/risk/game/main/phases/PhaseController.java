package risk.game.main.phases;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller that handles the phase functionality
 * @author shareenali, farhanrw
 * @version 0.1
 */
public class PhaseController {
    private PhaseView view;
    private PhaseModel model;
    private static PhaseController instance;

    /**
     * It initializes the view and the model
     */
    public PhaseController() {
        this.view = new PhaseView();
        this.model = new PhaseModel();
    }
    /**
     * It returns the current active instance
     */
    public static PhaseController getInstance() {
        if (instance == null)
            instance = new PhaseController();
        return instance;
    }

    /**
     * Initializes the values before displaying
     * @param buttonChangeLs change button listener
     * @param buttonSaveLs save game listener
     */
    public void initializeValues(ActionListener buttonChangeLs, ActionListener buttonSaveLs) {
        this.view.initializeValues();
        this.view.bindChangePhaseListener(buttonChangeLs);
        this.view.bindSaveGameListener(buttonSaveLs);

        this.model.addObserver(this.view);
    }

    /**
     * Sets up pre defined values
     * @param players list of players
     * @param colors list of colors
     */
    public void setupValues(ArrayList<String> players, ArrayList<Color> colors) {
        this.model.setPlayers(players, colors);
        this.view.addPlayers(players);
    }

    /**
     * Change the phase
     */
    public void changePhase() {
        this.model.nextPhase();
    }

    /**
     * It returns the active phase
     * @return index of the active phase
     */
    public int activePhase() {
        return this.model.getActivePhase();
    }

    /**
     * Sets the active phase
     * @param name name of the active phase
     */
    public void setActivePhase (int name){ this.model.setActivePhase(name); }

    /**
     * It returns the active player's name
     * @return name of the player
     */
    public String activePlayer() {
        return this.model.getActivePlayer();
    }
    

    /**
     * Sets the player with name "name" as the current active player
     * @param name name of the active player
     */
    public void setActivePlayer(String name){ this.model.setActivePlayer(name); }

    /**
     * Changes the active player
     */
    public void changePlayer() {
        this.model.nextPlayer();
    }

    /**
     * Returns the root panel
     * @return root panel
     */
    public JPanel getRootPanel() {
        return (JPanel) this.view.$$$getRootComponent$$$();
    }

    /**
     * Get the view
     * @return view
     */
    public PhaseView getView() {
        return this.view;
    }

    public PhaseModel getModel() {
        return this.model;
    }

    public void setLoadGameValues(int phase, int playerIdx) {
        this.model.setPhase(phase);
        this.model.setPlayerIndex(playerIdx);
    }
}
