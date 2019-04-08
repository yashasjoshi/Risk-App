package risk.game.main.logs;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View that displays Logs
 *
 * @author shareenali
 * @version 0.1
 */
@SuppressWarnings("deprecation")
public class LogsView implements Observer {
    private JPanel panelMain;
    private JList listLogs;
    private JPanel panelTitle;
    private JLabel labelTitle;
    private JScrollPane scrollList;

    private DefaultListModel<String> modelLogs = new DefaultListModel<>();

    /**
     * Attach the log to UI
     *
     * @param log game step to attach to reply
     */
    @SuppressWarnings("unchecked")
    private void appendLog(String log) {
        this.modelLogs.add(0, log);
        this.listLogs.setModel(modelLogs);
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
            switch ((String) arg) {
                case LogsModel.ADD_LOG:
                    this.appendLog(((LogsModel) o).getLog());
                    break;
            }
        }
    }

    /**
     * Returns the list of logs that are already been displayed on the game View
     *
     * @return list of Logs
     */
    public JList getlistLog() {
        return this.listLogs;
    }

    /**
     * Sets the list of Logs
     *
     * @param logs new list of Logs
     */
    public void setListLog(JList logs) {
        this.listLogs = logs;
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
        panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelMain.add(panelTitle, BorderLayout.NORTH);
        labelTitle = new JLabel();
        labelTitle.setText("Gameplay");
        panelTitle.add(labelTitle);
        scrollList = new JScrollPane();
        scrollList.setPreferredSize(new Dimension(300, 400));
        panelMain.add(scrollList, BorderLayout.CENTER);
        listLogs = new JList();
        scrollList.setViewportView(listLogs);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
