package risk.game.main.phases;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Model that drives the phase view
 * @author shareenali
 * @version 0.1
 */
@SuppressWarnings("deprecation")
public class PhaseModel extends Observable {
    public static final int PHASE_REINFORCEMENT = 0;
    public static final int PHASE_ATTACK = 1;
    public static final int PHASE_FORTIFICATION = 2;

    static final String CHANGE_PHASE = "change:phase";
    static final String CHANGE_PLAYER = "change:player";

    private String[] phaseNames = {"Reinforcement", "Attack", "Fortification"};
    private int phase = -1;
    private int playerIndex = -1;
    private int totalPlayers;
    private ArrayList<String> players;
    private ArrayList<Color> colors;

    /**
     * Sets the players and colors into the phase
     * @param players list of players
     * @param colors list of colors
     */
    void setPlayers(ArrayList<String> players, ArrayList<Color> colors) {
        this.totalPlayers = players.size();
        this.players = players;
        this.colors = colors;
    }

    /**
     * Move to next phase
     */
    void nextPhase() {
        this.phase++;

        if (this.phase == 3) {
            this.phase = 0;
            this.nextPlayer();
        }

        setChanged();
        notifyObservers(CHANGE_PHASE);
    }

    /**
     * Move to next player
     */
    void nextPlayer() {
        this.playerIndex = (this.playerIndex < this.totalPlayers - 1) ? this.playerIndex + 1 : 0;
        setChanged();
        notifyObservers(CHANGE_PLAYER);
    }

    /**
     * Get the active player's name
     * @return name of player
     */
    public String getActivePlayer() {
        return this.players.get(this.playerIndex);
    }

    /**
     * Set the active player's index
     * @param name name of the active palyer
     */
    void setActivePlayer(String name){
        this.playerIndex = this.players.indexOf(name);
    }

    /**
     * Get the active player's color
     * @return color of the player
     */
    Color getActiveColor() {
        return this.colors.get(this.playerIndex);
    }

    /**
     * Get the active phase
     * @return active phase
     */
    int getActivePhase() {
        return this.phase;
    }

    /**
     * Set the active phase
     * @param name name of the active phase
     */
    void setActivePhase(int name){
        this.phase = name;
    }

    /**
     * Get the name of the active phase
     * @return name of the phase
     */
    String getActivePhaseName() {
        return this.phaseNames[this.phase];
    }

    public int getPlayerIndex() {
        return this.playerIndex;
    }

    public int getPhase() {
        return this.phase;
    }

    void setPhase(int phase) {
        this.phase = phase;
    }

    void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
