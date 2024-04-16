package org.nice.Panels;

import com.formdev.flatlaf.ui.FlatLineBorder;
import net.miginfocom.swing.MigLayout;
import org.nice.Components.Search.SearchBar;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header() {
        setLayout(new MigLayout("", "grow"));
        setBorder(
                new FlatLineBorder(
                        new Insets(0,0,1,0),
                        UIManager.getColor("Component.borderColor"))
        );
        init();

    }

    private void init() {
        var leftside = new JPanel(new MigLayout("al left", "[][grow]"));

        add(leftside, "grow");
        leftside.add(new JLabel("Pokedexx"), "align left");
        // Search bar
        leftside.add(new SearchBar(), "grow, align right");


    }

}
