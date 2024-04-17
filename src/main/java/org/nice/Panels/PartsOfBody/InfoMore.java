package org.nice.Panels.PartsOfBody;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
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
import java.util.Comparator;
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
        setPreferredSize(new Dimension(640,276));
        setBackground(Color.GRAY);
        setLayout(new MigLayout("align center ","0[grow]0","[grow]0"));


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
        description.setFont(new Font("Verdana", Font.PLAIN, 16));
        description.setForeground(Color.DARK_GRAY);
        descriptionPanel.add(description);

        //stat bars
        statsPanel.add(new StatBar("HP", HP, maxStat, new Color(0xFFDF6D)));
        statsPanel.add(new StatBar("ATK", ATK, maxStat, new Color(0xE46666)));
        statsPanel.add(new StatBar("DEF", DEF, maxStat, new Color(0x7480ED)));
        statsPanel.add(new StatBar("SP. ATK", SPATK, maxStat, new Color(0xF2A6A6)));
        statsPanel.add(new StatBar("SP. DEF", SPDEF, maxStat, new Color(0x7DA6CC)));
        statsPanel.add(new StatBar("SPD", SPD, maxStat, new Color(0x796CC9)));


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

            // Set the evolList
            if(p.id()==133){
                String eeveeData = "{\"evolution\":{\"next\":[[\"134\",\"use Water Stone\"],[\"135\",\"use Thunder Stone\"],[\"136\",\"use Fire Stone\"],[\"196\",\"high Friendship, Daytime\"],[\"197\",\"high Friendship, Nighttime\"],[\"470\",\"level up near a Mossy Rock\"],[\"471\",\"level up near an Icy Rock\"],[\"700\",\"High Affection and knowing Fairy move\"]]}}";
                JSONObject eeveeObject = new JSONObject(eeveeData);
                JSONArray eeveeEvols = eeveeObject.getJSONObject("evolution").getJSONArray("next");

                evolutionPanel.revalidate();
                evolutionPanel.repaint();

                for(int i = 0; i < eeveeEvols.length();i++){
                    JSONArray evolInfo = eeveeEvols.getJSONArray(i);
                    int evolModelID = evolInfo.getInt(0);
                    String evolMethod = evolInfo.getString(1);

                    Optional<PokemonModel> evolInstanceOptional = pokemonService.getPokemon(evolModelID);
                    PokemonModel evolInstance = evolInstanceOptional.get();

                    JLabel evolInstanceImage = new JLabel();
                    evolInstanceImage.setIcon(Utils.getImage(evolInstance.image().thumbnail()));
                    evolInstanceImage.setText(evolInstance.name());
                    evolInstanceImage.setHorizontalTextPosition(JLabel.CENTER);
                    evolInstanceImage.setVerticalTextPosition(JLabel.BOTTOM);
                    evolutionPanel.setLayout(new MigLayout("wrap 4", "[grow][grow][grow][grow]", "[grow][grow]"));
                    evolutionPanel.add(evolInstanceImage);
                }
            }

            else
            {
                evolutionPanel.setLayout(new MigLayout("align center"));
                var evolList = new ArrayList<PokemonModel>();
                var next = p.getNextEvolution();
                var prev = p.getPrevEvolution();
                evolList.add(p);
                while(prev.isPresent()) {
                    evolList.add(prev.get().model());
                    prev = prev.get().model().getPrevEvolution();
                }
                while (!next.isEmpty()) {
                    var a = next.get(0);
                    evolList.add(a.model());
                    next = a.model().getNextEvolution();
                }
                evolList.sort(Comparator.comparingInt(PokemonModel::id));

                var i = 0;
                for(var evol : evolList) {
                    var card = new JPanel(new MigLayout("wrap", "grow"));
                    card.setPreferredSize(new Dimension(120,120));
                    card.setBorder(
                            BorderFactory.createCompoundBorder(
                                    new FlatDropShadowBorder(
                                            UIManager.getColor("Component.shadowColor"),
                                            new Insets(10,10,10,10),
                                            0.05f
                                    ),
                                    new EmptyBorder(20,20,20,20)
                            )
                    );
                    var image = new JLabel();
                    image.setIcon(Utils.getImage(
                            evol.image().thumbnail(),
                            100,100
                    ));
                    card.add(image);
                    card.add(new JLabel(evol.name()), "align center");
                    evolutionPanel.add(card);
                    if(i != evolList.size() - 1) {
                        evolutionPanel.add(new JLabel("---->"));
                    }
                    i++;
                }

                evolutionPanel.revalidate();
                evolutionPanel.repaint();
            }



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
}
