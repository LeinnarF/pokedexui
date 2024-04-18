package org.nice;

import com.formdev.flatlaf.FlatIntelliJLaf;
//import net.miginfocom.swing.MigLayout;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import org.nice.Panels.*;
import org.nice.services.PokemonService;

public class Main {
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();

        JFrame frame = new JFrame("Pokedex - Loading");
        ImageIcon AppIcon = new ImageIcon(Utils.getResource("/images/items/4.png"));
        frame.setIconImage(AppIcon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 120);
        frame.setMinimumSize(new Dimension(480, 120)); // 720 + 30
        frame.setLocationRelativeTo(null); // center the window
        frame.setLayout(new MigLayout("align center center"));
        frame.add(new JLabel("Initializing... Please wait"));
        frame.setVisible(true);
        PokemonService.init();

        var footer = new Footer();
        var header = new Header();
        var body = new Body();

        initMainFrame(header, footer, body);
        frame.dispose();

    }

    private static void initMainFrame(Header header, Footer footer, Body body) {
        JFrame frame = new JFrame("Pokedex");
        ImageIcon AppIcon = new ImageIcon(Utils.getResource("/images/items/4.png"));

        frame.setIconImage(AppIcon.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setMinimumSize(new Dimension(1080, 750)); // 720 + 30
        frame.setLocationRelativeTo(null); // center the window
        frame.setLayout(new BorderLayout(0, 0));
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.add(footer, BorderLayout.SOUTH);
        frame.add(body, BorderLayout.CENTER);
        frame.add(header, BorderLayout.NORTH);

        frame.setVisible(true);

    }
}
