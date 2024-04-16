package org.nice.Panels;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Footer extends JPanel{
    public Footer() {
        setLayout(new MigLayout("align left center"));
        
        JLabel label = new JLabel("Pokedex");
        label.setFont(new Font("Inter", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        add(label, "gapleft 10");

        setBackground(new Color(0xE46666));
        setPreferredSize(new Dimension(0, 57));
    }
}
