package org.nice.Panels;

import com.formdev.flatlaf.ui.FlatLineBorder;
import net.miginfocom.swing.MigLayout;
import org.nice.Components.Search.FilterBtn;
import org.nice.Components.Search.SearchBar;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header() {
        setLayout(new MigLayout("", "1.5%[grow][30%]"));
        setBorder(
                new FlatLineBorder(
                        new Insets(0,0,1,0),
                        UIManager.getColor("Component.borderColor"))
        );
        init();
        setBackground(new Color(0xE46666));
    }

    private void init() {
        var leftside = new JPanel(new MigLayout("align left", "[grow]"));

        add(leftside, "grow");
        //leftside.add(new JLabel(""), "align left");
        leftside.setBackground(new Color(0xE46666)); //0xE46666
        // Search bar
        leftside.add(new SearchBar(), "grow, align left");
        leftside.add(new FilterBtn(), "align left");

    }
}
