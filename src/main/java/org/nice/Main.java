package org.nice;

import com.formdev.flatlaf.FlatIntelliJLaf;
//import net.miginfocom.swing.MigLayout;

import java.awt.*;
import javax.swing.*;

import org.nice.Panels.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pokedex");
        FlatIntelliJLaf.setup();
        ImageIcon AppIcon = new ImageIcon(Utils.getResource("/images/items/4.png"));
        
        frame.setIconImage(AppIcon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setMinimumSize(new Dimension(1080, 750));  //720 + 30
        frame.setLocationRelativeTo(null); // center the window
        frame.setLayout(new BorderLayout(0,0));
       // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.add(new Footer(), BorderLayout.SOUTH);
        frame.add(new Header(), BorderLayout.NORTH);
        frame.add(new Body(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
