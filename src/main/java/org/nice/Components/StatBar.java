package org.nice.Components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import org.nice.lib.roundcorner.RoundedCorners;

import java.awt.*;

public class StatBar extends JPanel {
    public StatBar(String statLabel, int statValue, int statMax, Color fillColor){

        setLayout(new MigLayout("gapx 12", "[55]10[500]10[]", ""));

        RoundedCorners statNamePanel = new RoundedCorners();
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

        JLabel statValueText = new JLabel();
        statValueText.setText(Integer.toString(statValue));
        statValueText.setForeground(Color.BLACK);
        statValueText.setFont(new Font("Arial", Font.BOLD, 12));
        add(statValueText, "grow");
        
    }
}
