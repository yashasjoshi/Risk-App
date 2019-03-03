package game.main.phases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
     * It adds players to the domination table in order to initialize the table
     * @param players list of players
     */
    void addPlayers(ArrayList<String> players) {
        for (String name : players) {
            String[] row = {name};
            this.modelPlayers.addRow(row);
        }

        this.tablePlayers.setModel(this.modelPlayers);
    }

    private void onPhaseChanged(PhaseModel model) {
        this.labelPhase.setText(model.getActivePhaseName() + " Phase");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            System.out.println(arg);
            switch ((String) arg) {
                case PhaseModel.CHANGE_PHASE:
                    this.onPhaseChanged((PhaseModel) o);
                    break;
                case PhaseModel.CHANGE_PLAYER:
                    break;
            }
        }
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
