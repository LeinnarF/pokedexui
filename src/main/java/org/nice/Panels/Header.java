package org.nice.Panels;

import java.awt.*;
import javax.swing.*;

import org.nice.Components.Search.*;

import net.miginfocom.swing.MigLayout;

public class Header extends JPanel {
    public Header() {
        setBackground(new Color(0xE46666));
        setPreferredSize(new Dimension(0,77));
        setLayout(new MigLayout("", "0[37%][62%]", "23[100%]23"));

        JPanel searchPanel = new JPanel(); // to group the search elements
        searchPanel.setBackground(new Color(0xE45566)); // 0xE46666
        searchPanel.setLayout(new MigLayout("","10%[80%][20%]","0[100%]0"));
        add(searchPanel, "grow");
        searchPanel.add(new SearchBar(), "grow");
        //searchPanel.add(new SearchButton(), "grow");
        //searchPanel.add(new SearchFilter(), "grow");
    }
}
