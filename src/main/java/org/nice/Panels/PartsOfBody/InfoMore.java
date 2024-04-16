package org.nice.Panels.PartsOfBody;

import net.miginfocom.swing.MigLayout;
import org.nice.Components.StatBar;
import org.nice.Utils;
import org.nice.models.PokemonModel;
import org.nice.services.PokemonService;
import rx.Observable;

import java.awt.*;
import java.text.MessageFormat;
import java.util.Optional;
import javax.swing.*;

public class InfoMore extends JPanel{
    JPanel descriptionPanel = new JPanel(new MigLayout("align left top"));
    JPanel evolutionPanel = new JPanel(new MigLayout("align center center"));
    JPanel statsPanel = new JPanel(new MigLayout("align center center, wrap", "[]", "[]0[]0[]0[]0[]0[]"));

    int maxStat = 255;
    int HP, ATK, DEF, SPATK, SPDEF, SPD;
    String descriptionText;

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();

    public InfoMore(){
        setLayout(new MigLayout("align center center, debug", "[grow]"));
        setBackground(Color.GRAY);

        //test values start
        Optional<PokemonModel> dummyPokemon = pokemonService.getPokemon(1);
        if(dummyPokemon.isPresent()){
            PokemonModel dummyModel = dummyPokemon.get();

            Optional<PokemonModel.BaseStats> dummyStats = dummyModel.base();
            if(dummyStats.isPresent()){
                PokemonModel.BaseStats baseStats = dummyStats.get();

                HP = baseStats.HP();
                ATK = baseStats.Attack();
                DEF = baseStats.Defense();
                SPATK = baseStats.SpAttack();
                SPDEF = baseStats.SpDefense();
                SPD = baseStats.Speed();

            }else{System.out.println("NO STATS");}

            descriptionText = dummyModel.description();

        }else{System.out.println("NO POKEMON");}
        //test value end

        //description
        JLabel description = new JLabel();
        description.setText(MessageFormat.format("<HTML><br/><p>{0}</p></HTML>",descriptionText ));
        description.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionPanel.add(description);

        //stat bars
        statsPanel.add(new StatBar("HP", HP, maxStat, new Color(0xFFDF6D)));
        statsPanel.add(new StatBar("ATK", ATK, maxStat, new Color(0xE46666)));
        statsPanel.add(new StatBar("DEF", DEF, maxStat, new Color(0x7480ED)));
        statsPanel.add(new StatBar("SP. ATK", SPATK, maxStat, new Color(0xF2A6A6)));
        statsPanel.add(new StatBar("SP. DEF", SPDEF, maxStat, new Color(0x7DA6CC)));
        statsPanel.add(new StatBar("SPD", SPD, maxStat, new Color(0x796CC9)));

        //evolution
        JLabel evolutionData = new JLabel();
        evolutionData.setText("Ivysaur");
        evolutionData.setIcon(new ImageIcon(Utils.getResource("/images/thumbnails/00" + 2 + ".png")));
        evolutionData.setHorizontalTextPosition(JLabel.CENTER);
        evolutionData.setVerticalTextPosition(JLabel.BOTTOM);
        evolutionPanel.add(evolutionData);

        //Tabs
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tab.setBackground(Color.GRAY);
        tab.add("Description", descriptionPanel);
        tab.add("Evolution", evolutionPanel);
        tab.add("Stats", statsPanel);
        tab.setFont(new Font("Arial", Font.BOLD, 18));
        tab.setForeground(Color.WHITE);

        add(tab, "grow");
        //setBorder(border);
        pokemonService.onCurrentPokemon().subscribe(p -> {
            description.setText(MessageFormat.format("<HTML><br/><p>{0}</p></HTML>",p.description() ));


            var next = p.getNextEvolution();
            if(next.isEmpty()) {
                evolutionData.setText("No next evolution.");
                evolutionData.setIcon(
                        Utils.getImage(p.image().thumbnail(), 120,120)
                );
            } else {
                evolutionData.setText(next.get(0).model().name());
                evolutionData.setIcon(
                        Utils.getImage(next.get(0).model().image().thumbnail(), 120,120)
                );
            }

            Optional<PokemonModel.BaseStats> stats = p.base();
            if(stats.isPresent()){
                PokemonModel.BaseStats baseStats = stats.get();
                HP = baseStats.HP();
                ATK = baseStats.Attack();
                DEF = baseStats.Defense();
                SPATK = baseStats.SpAttack();
                SPDEF = baseStats.SpDefense();
                SPD = baseStats.Speed();

            }
            // Recreate stats
            statsPanel.removeAll();
            statsPanel.add(new StatBar("HP", HP, maxStat, new Color(0xFFDF6D)));
            statsPanel.add(new StatBar("ATK", ATK, maxStat, new Color(0xE46666)));
            statsPanel.add(new StatBar("DEF", DEF, maxStat, new Color(0x7480ED)));
            statsPanel.add(new StatBar("SP. ATK", SPATK, maxStat, new Color(0xF2A6A6)));
            statsPanel.add(new StatBar("SP. DEF", SPDEF, maxStat, new Color(0x7DA6CC)));
            statsPanel.add(new StatBar("SPD", SPD, maxStat, new Color(0x796CC9)));
            statsPanel.revalidate();
            statsPanel.repaint();

        });
    }
}
