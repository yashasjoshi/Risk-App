package risk.game.main.phases;

import risk.game.main.MainModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * View that displays UI
 *
 * @author shareeali
 * @version 0.1
 */
@SuppressWarnings("deprecation")
public class PhaseView implements Observer {
    private JPanel panelMain;
    private JTable tablePlayers;
    private JPanel panelContent;
    private JPanel panelPhase;
    private JLabel labelPhase;
    private JPanel panelAction;
    private JLabel labelPlayer;
    private JPanel panelPlayer;
    private JButton buttonChange;
    private JScrollPane scrollPanelPlayers;
    private JButton buttonSaveGame;

    private DefaultTableModel modelPlayers = new DefaultTableModel();

    /**
     * It initializes the default values inside the view.
     */
    void initializeValues() {
        this.modelPlayers.addColumn("Player");
        this.modelPlayers.addColumn("Continents");
        this.modelPlayers.addColumn("Armies");
        this.modelPlayers.addColumn("%");

        this.tablePlayers.setModel(this.modelPlayers);
    }

    /**
     * Bind the change phase listener
     *
     * @param listener listener to bind
     */
    void bindChangePhaseListener(ActionListener listener) {
        this.buttonChange.addActionListener(listener);
    }

    void bindSaveGameListener(ActionListener listener) {
        this.buttonSaveGame.addActionListener(listener);
    }

    /**
     * It adds players to the domination table in order to initialize the table
     *
     * @param players list of players
     */
    void addPlayers(ArrayList<String> players) {
        for (String name : players) {
            String[] row = {name};
            this.modelPlayers.addRow(row);
        }

        this.tablePlayers.setModel(this.modelPlayers);
    }

    /**
     * It executes set of actions when the phase has been changed
     *
     * @param model the model which had been changed
     */
    private void onPhaseChanged(PhaseModel model) {
        this.labelPhase.setText(model.getActivePhaseName() + " Phase");
    }

    /**
     * It executes set of actions when the player has been changed
     *
     * @param model the model which had been changed
     */
    private void onPlayerChanged(PhaseModel model) {
        this.labelPlayer.setText(model.getActivePlayer() + "'s turn");
        this.labelPlayer.setForeground(model.getActiveColor());
    }

    /**
     * Updates the view the observable notifies
     *
     * @param o   the model
     * @param arg identification string
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String content[] = ((String) arg).split(":");
            String actual = content[0].concat(":").concat(content[1]);
            switch (actual) {
                case PhaseModel.CHANGE_PHASE:
                    this.onPhaseChanged((PhaseModel) o);
                    break;
                case PhaseModel.CHANGE_PLAYER:
                    this.onPlayerChanged((PhaseModel) o);
                    break;
                case MainModel.CHANGE_ARMY:
                    this.addPlayers(((MainModel) o).getDominationTable(), ((MainModel) o).getPlayerNames());
                    break;
                case MainModel.UPDATE_PLAYER:
                    this.addPlayer((MainModel) o, content[2]);
                    break;
            }
        }
    }

    /**
     * Add player to the view
     *
     * @param mainModel the root model
     * @param name      name of the player to add
     */
    private void addPlayer(MainModel mainModel, String name) {
        String row[] = mainModel.getDominationRow(mainModel.getPlayer(name));
        HashMap<String, String[]> dominationTable = new HashMap<>();
        dominationTable.put(name, row);
        this.addPlayers(dominationTable, mainModel.getPlayerNames());
    }

    /**
     * Add players to the view
     *
     * @param players     list of players
     * @param playerNames names of players
     */
    private void addPlayers(HashMap<String, String[]> players, ArrayList<String> playerNames) {
        for (Map.Entry<String, String[]> entry : players.entrySet()) {
            int row = playerNames.indexOf(entry.getKey());
            String values[] = entry.getValue();

            this.modelPlayers.setValueAt(values[0], row, 0);
            this.modelPlayers.setValueAt(values[1], row, 1);
            this.modelPlayers.setValueAt(values[2], row, 2);
            this.modelPlayers.setValueAt(values[3], row, 3);
        }

        this.tablePlayers.setModel(this.modelPlayers);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(0, 0));
        panelContent = new JPanel();
        panelContent.setLayout(new BorderLayout(0, 0));
        panelMain.add(panelContent, BorderLayout.CENTER);
        panelPhase = new JPanel();
        panelPhase.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelContent.add(panelPhase, BorderLayout.NORTH);
        labelPhase = new JLabel();
        labelPhase.setText("Phase");
        panelPhase.add(labelPhase);
        panelAction = new JPanel();
        panelAction.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelContent.add(panelAction, BorderLayout.SOUTH);
        buttonChange = new JButton();
        buttonChange.setText("Change Phase");
        panelAction.add(buttonChange);
        buttonSaveGame = new JButton();
        buttonSaveGame.setText("Save Game");
        panelAction.add(buttonSaveGame);
        panelPlayer = new JPanel();
        panelPlayer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelContent.add(panelPlayer, BorderLayout.CENTER);
        labelPlayer = new JLabel();
        labelPlayer.setText("Player");
        panelPlayer.add(labelPlayer);
        scrollPanelPlayers = new JScrollPane();
        scrollPanelPlayers.setPreferredSize(new Dimension(254, 100));
        panelMain.add(scrollPanelPlayers, BorderLayout.SOUTH);
        tablePlayers = new JTable();
        scrollPanelPlayers.setViewportView(tablePlayers);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }
}
