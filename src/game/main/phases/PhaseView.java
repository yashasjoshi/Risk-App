package game.main.phases;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PhaseView implements Observer {
    private JPanel panelPhase;
    private JLabel labelPhaseName;

    @Override
    public void update(Observable o, Object arg) {

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
        panelPhase = new JPanel();
        panelPhase.setLayout(new BorderLayout(0, 0));
        labelPhaseName = new JLabel();
        labelPhaseName.setText("Phase Name");
        panelPhase.add(labelPhaseName, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPhase;
    }
}
