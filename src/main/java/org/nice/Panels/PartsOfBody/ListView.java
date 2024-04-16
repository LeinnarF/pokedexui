package org.nice.Panels.PartsOfBody;

import net.miginfocom.swing.MigLayout;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.lib.roundcorner.*;
import org.nice.models.PokemonTypeColor;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ListView extends JScrollPane {


    public ListView(){

        setPreferredSize(new Dimension(414,586));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        var list = new DynamicListView<>(
            PokemonService.getInstance().filterPokemons(List.of()), //1st list ng poke
            pokemonModel -> String.valueOf(pokemonModel.id()),// 2nd id builder
            v -> {

                Color typeColor = PokemonTypeColor.getColor(v.type().get(0));

                //var view = new JPanel(new MigLayout());
                RoundedCorners listbox = new RoundedCorners();
                listbox.setAllRound(10);
                listbox.setPreferredSize(new Dimension(375, 68)); 
                listbox.setBackground(typeColor);
                listbox.setLayout(new MigLayout("align center center","[10%][80%][10%]"));

                // id
                RoundedCorners idPanel = new RoundedCorners();
                idPanel.setSize(new Dimension(50,28));
                idPanel.setMinimumSize(new Dimension(50,28));
                idPanel.setMaximumSize(new Dimension(50,28));
                idPanel.setLayout(new MigLayout("align center center"));
                idPanel.setBackground(Color.white);
                idPanel.setAllRound(5);
                JLabel idLabel = new JLabel(String.valueOf(v.id())) {{
                    setFont(new Font("Arial", Font.BOLD,20));
                    setForeground(typeColor);
                }};
                idPanel.add(idLabel);
                listbox.add(idPanel, "grow");

                //name
                JPanel Pname = new JPanel();
                Pname.setPreferredSize(new Dimension(200,60));
                Pname.setLayout(new MigLayout("align center center"));
                Pname.setBackground(typeColor);
                JLabel pokeName = new JLabel(v.name().english()){{
                    setFont(new Font("Arial", Font.BOLD,20));
                    setForeground(Color.white);
                }};
                Pname.add(pokeName);
                listbox.add(Pname,"grow");

                int ID = v.id();
                String zeros = "";

                if(ID < 10){
                    zeros = "00";
                }
                else if(ID < 100){
                    zeros = "0";
                }

                // sprite
                RoundedCorners Psprite = new RoundedCorners();
                Dimension spriteSize = new Dimension(60,60);
                Psprite.setPreferredSize(spriteSize);
                Psprite.setMaximumSize(spriteSize);
                Psprite.setMinimumSize(spriteSize);
                Psprite.setLayout(new MigLayout("align center center"));
                Psprite.setBackground(Color.white);
                Psprite.setAllRound(100);

                ImageIcon image = new ImageIcon("src/main/resources/images/sprites/"+zeros+ID+".png");
                Image img =image.getImage();
                Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                image = new ImageIcon(newimg);

                JLabel pokeSprite = new JLabel(image);
                Psprite.add(pokeSprite);
                listbox.add(Psprite, "grow");

                return new Item<>(listbox, Optional.of("grow"));
            }, // 3rd listbox builder or view
            new Item<>(new JLabel("nice"),Optional.empty()), // 4th 
            new MigLayout("wrap, align center", "grow")
        );
        setViewportView(list);
        getVerticalScrollBar().setUnitIncrement(20);
    }
}