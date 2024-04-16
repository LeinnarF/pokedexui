package org.nice.Components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class StatBar extends JPanel {
    public StatBar(String statLabel, int statValue, int statMax, Color fillColor){

        setLayout(new MigLayout("gapx 12", "[55]10[500]", ""));

        JPanel statNamePanel = new JPanel();
        //statNamePanel.setBorder(new FlatRoundBorder());
        statNamePanel.setBackground(fillColor);

        JLabel statName = new JLabel();
        statName.setText(statLabel);
        statName.setForeground(Color.WHITE);
        statName.setFont(new Font("Arial", Font.BOLD, 12));

        statNamePanel.add(statName);
        add(statNamePanel, "align left center, grow");

        var pb = new JProgressBar();
        pb.setMaximum(statMax);
        pb.setMinimum(0);
        pb.setValue(statValue);
        pb.setForeground(fillColor);
        
        add(pb, "grow");
    }
}
