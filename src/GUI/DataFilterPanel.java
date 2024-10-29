package src.GUI;

import javax.swing.*;
import java.awt.*;

public class DataFilterPanel extends JPanel {
    JCheckBox infconsumer;
    JCheckBox infgdp;
    JCheckBox GDPgrowth;
    JCheckBox GDPpercapita;
    JPanel panel;
    public DataFilterPanel() {
        panel = new JPanel();

        setPreferredSize(new Dimension(1000, 400));



    }

    public Object getSample() {
        return null;
    }
}
