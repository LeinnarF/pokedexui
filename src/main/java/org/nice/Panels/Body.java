package org.nice.Panels;

import java.awt.*;
import javax.swing.*;
import org.nice.Panels.PartsOfBody.*;
import net.miginfocom.swing.MigLayout;

public class Body extends JPanel{
    public Body() {

        JPanel Info = new JPanel();
    
        // Info section
        Info.setPreferredSize(new Dimension(666,586));
        Info.setBackground(Color.white);
        Info.setLayout(new MigLayout("","0[100%]0", "[50%]0[50%]"));

        Info.add(new InfoBase(),"grow, wrap");
        Info.add(new InfoMore(),"grow");
    
        setLayout(new MigLayout("", "0[38%][62%]", "0[100%]0"));

        add(new ListView(),"grow");
        add(Info,"grow");
        
        setPreferredSize(new Dimension(1080,586));
        setBackground(new Color(0xF6F6F6));
    }
}
