package org.nice.Components.Search;

import java.awt.*;
import javax.swing.*;

public class SearchBar extends JTextField {
    public SearchBar(){
        setPreferredSize(new Dimension(310, 35));
        setFont(new Font("Inter", Font.PLAIN, 14));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        setBackground(new Color(0xFfFfFf));
    }
}
