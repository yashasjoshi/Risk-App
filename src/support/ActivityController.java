package support;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class ActivityController {
    protected JFrame frame = new JFrame();
    protected abstract void prepareUi();

    public void setupValues() { }

    public void setupValues(HashMap<String, Object> data) { }

    public void destroy() {
        this.frame.dispose();
    }

    /**
     * It initializes the view, and wraps it to a frame.
     */
    public void initUi() {
        this.frame.setTitle("Risk Conquest!");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.prepareUi();
    }

    /**
     * It displays view to the standard output device.
     */
    public void displayUi() {
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
