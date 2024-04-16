package org.nice.Panels;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import org.nice.Utils;

public class Footer extends JPanel {
    public Footer() {
        setLayout(new MigLayout(""));
        setBackground(new Color(0xE46666));

        ImageIcon pokeball = new ImageIcon(Utils.getResource("/images/items/4.png"));
        add(new JLabel(pokeball), "align left center");

        JLabel pokedex = new JLabel();
        pokedex.setText("POKEDEX");
        pokedex.setForeground(Color.WHITE);
        pokedex.setFont(new Font("Arial", Font.BOLD, 16));
        add(pokedex);
    }
}
