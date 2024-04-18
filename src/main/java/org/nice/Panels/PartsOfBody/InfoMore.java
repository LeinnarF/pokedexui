package org.nice.Panels.PartsOfBody;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import net.miginfocom.layout.LC;
import net.miginfocom.layout.AlignX;
import net.miginfocom.swing.MigLayout;
import org.nice.Components.StatBar;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nice.Utils;
import org.nice.models.PokemonModel;
import org.nice.services.PokemonService;
import rx.Observable;

import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InfoMore extends JPanel{
    JPanel descriptionPanel = new JPanel(new MigLayout("align left top"));
    JPanel evolutionPanel = new JPanel(new MigLayout("align center"));
    JPanel statsPanel = new JPanel(new MigLayout("align center, wrap", "[]", "[]0[]0[]0[]0[]0[]"));
    JPanel weaknessPanel = new JPanel();

    int maxStat = 255;
    int HP, ATK, DEF, SPATK, SPDEF, SPD;
    String descriptionText;

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();

    public InfoMore(){
//        setPreferredSize(new Dimension(640,276));
        setBackground(Color.GRAY);
        setLayout(new MigLayout("align center ","0[grow]0","[grow]0"));


        //test values start
        JLabel description = new JLabel();
        description.setText(MessageFormat.format("<HTML><br/><p>{0}</p></HTML>",descriptionText ));
        description.setFont(new Font("Verdana", Font.PLAIN, 16));
        description.setForeground(Color.DARK_GRAY);
        descriptionPanel.add(description);

        //Tabs
        UIManager.put("TabbedPane.foreground", Color.white);
        UIManager.put("TabbedPane.background", Color.GRAY);
        var bg = UIManager.getColor("TabbedPane.background");
        UIManager.put("TabbedPane.underlineColor", bg);
        UIManager.put("TabbedPane.focusColor", bg);
        JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tab.add("Description", descriptionPanel);
        tab.add("Evolution", evolutionPanel);
        tab.add("Stats", statsPanel);
        tab.add("Weakness", weaknessPanel);
        tab.setFont(new Font("Verdana", Font.PLAIN, 18));

        add(tab, "grow");
        //setBorder(border);
        pokemonService.onCurrentPokemon().subscribe(p -> {

            //description
            description.setText(MessageFormat.format("<HTML><br><p>{0}</p></br></HTML>",p.description() ));

            evolutionPanel.removeAll();


            evolutionPanel.setLayout(new MigLayout("align center"));
            var next = p.getNextEvolution();
            var prev = p.getPrevEvolution();

            prev.ifPresent(v -> {
                evolutionPanel.add(renderEvolutionCard(v));
                evolutionPanel.add(new JLabel("--->"));
            });
            evolutionPanel.add(renderEvolutionCard(
                    new PokemonModel.EvolutionNiceData(
                            p,""
                    )
            ));
            if(!next.isEmpty()) {
                var nextEvolPanel=  new JPanel(
                        new MigLayout(
                                new LC().wrapAfter(4).gridGap("0px","0px")
                        )
                );
                evolutionPanel.add(new JLabel("--->"));
                var scrollable = new JScrollPane(nextEvolPanel);
                scrollable.setBorder(null);
                evolutionPanel.add(scrollable);
                next.forEach(n -> {
                    var card = renderEvolutionCard(n);
                    nextEvolPanel.add(card);
                });
            }

            evolutionPanel.revalidate();
            evolutionPanel.repaint();


            statsPanel.removeAll();
            Optional<PokemonModel.BaseStats> stats = p.base();
            if(stats.isPresent()){
                PokemonModel.BaseStats baseStats = stats.get();
                HP = baseStats.HP();
                ATK = baseStats.Attack();
                DEF = baseStats.Defense();
                SPATK = baseStats.SpAttack();
                SPDEF = baseStats.SpDefense();
                SPD = baseStats.Speed();
                // Recreate stats
                statsPanel.add(new StatBar("HP", HP, maxStat, new Color(0xFFDF6D)));
                statsPanel.add(new StatBar("ATK", ATK, maxStat, new Color(0xE46666)));
                statsPanel.add(new StatBar("DEF", DEF, maxStat, new Color(0x7480ED)));
                statsPanel.add(new StatBar("SP. ATK", SPATK, maxStat, new Color(0xF2A6A6)));
                statsPanel.add(new StatBar("SP. DEF", SPDEF, maxStat, new Color(0x7DA6CC)));
                statsPanel.add(new StatBar("SPD", SPD, maxStat, new Color(0x796CC9)));
            } else {
                statsPanel.add(new JLabel("Pokemon got no stats available. );"));
            }

            statsPanel.revalidate();
            statsPanel.repaint();

        });
    }

    private JPanel renderEvolutionCard(PokemonModel.EvolutionNiceData evol) {
        var card = new JPanel(new MigLayout("wrap", ""));
        card.setPreferredSize(new Dimension(120,120));
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new FlatDropShadowBorder(
                                UIManager.getColor("Component.shadowColor"),
                                new Insets(10,10,10,10),
                                0.05f
                        ),
                        new EmptyBorder(10,10,10,10)
                )
        );
        var image = new JLabel();
        image.setIcon(Utils.getImage(
                evol.model().image().thumbnail(),
                60,60
        ));
        card.add(image, "wrap 12px, align center ");
        var nameLabel = new JLabel(
                MessageFormat.format(
                        "{0} #{1}",
                        evol.model().name(),
                        evol.model().id()
                )
        );
        nameLabel.setFont(new Font(
                "Verdana", Font.BOLD, 14
        ));
        card.add(nameLabel, "align center");
        var condition = new JLabel(evol.level());
        condition.setFont(
                new Font("Verdana", Font.ITALIC, 10)
        );
        condition.setForeground(
                Color.darkGray
        );
        card.add(condition, "align center");
        return card;
    }
}


