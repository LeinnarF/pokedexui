package org.nice.Panels;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import net.miginfocom.swing.MigLayout;
import org.nice.Components.Search.FilterBtn;
import org.nice.Components.Search.SearchBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Header extends JPanel {
    public Header() {
        JPanel Master = new JPanel();
        setLayout(new MigLayout("center","0[100%]0","0[]0"));
        Master.setLayout(new MigLayout("", "1.5%[grow][30%]"));

        Master.setBackground(new Color(0xE46666));
        setBorder(BorderFactory.createCompoundBorder(
            new FlatDropShadowBorder(Color.DARK_GRAY, new Insets(0, 0, 10, 0),75),
            new EmptyBorder(0,0,0,0)
        ));
        add(Master,"grow");

        var leftside = new JPanel(new MigLayout("align left", "[][grow]"));

        add(leftside, "grow");
        leftside.add(new JLabel(""), "align left");
        leftside.setBackground(new Color(0xE46666)); //0xE46666
        // Search bar
        leftside.add(new SearchBar(), "grow");
        leftside.add(new FilterBtn(), "align left");

        Master.add(leftside,"grow");
    }
}
