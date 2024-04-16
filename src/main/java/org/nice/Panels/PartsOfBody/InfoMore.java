package org.nice.Panels.PartsOfBody;

import java.awt.*;
import javax.swing.*;

public class InfoMore extends JPanel{
    public InfoMore() {
        JPanel TabPanel = new JPanel();
        JPanel MainPanel = new JPanel();

        MainPanel.setPreferredSize(new Dimension(640,276));
        MainPanel.setBackground(Color.pink);
        MainPanel.setLayout(new BorderLayout(0,0));
        MainPanel.add(new JLabel("MainPanel"));

        TabPanel.setPreferredSize(new Dimension(666,50));
        TabPanel.setBackground(new Color(0x747474));
        TabPanel.add(new Label("TabPanel"));

        setPreferredSize(new Dimension(666,586));
        setBackground(Color.cyan);
        add(new JLabel("InfoMore"));

        setLayout(new BorderLayout(0,0));
        MainPanel.add(TabPanel, BorderLayout.NORTH);      
        add(MainPanel, BorderLayout.CENTER);
    }
}
