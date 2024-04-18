package org.nice.Panels.PartsOfBody;

import java.awt.*;
import java.util.Optional;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonTypeColor;
import org.nice.models.PokemonModel.Ability;
import org.nice.services.PokemonImage;
import org.nice.services.PokemonService;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;

import net.miginfocom.swing.MigLayout;
import rx.Observable;

public class InfoBase extends JPanel {

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();
    String Name, Height, Weight, Species, Gender;
    int ID;
    List<String> Type;
    List<Ability> Ability;
    String zeros = "";

    public InfoBase() {

        Optional<PokemonModel> findPokemon = pokemonService.getPokemon(1); // 1 is the default pokemon
        if (findPokemon.isPresent()) {
            PokemonModel pokeModel = findPokemon.get();
            Name = pokeModel.name();
            Species = pokeModel.species();
            ID = pokeModel.id();
            Gender = pokeModel.profile().gender();
            Height = pokeModel.profile().height();
            Weight = pokeModel.profile().weight();
            Type = pokeModel.type();
            Ability = pokeModel.profile().ability();

        } else {
            System.out.println("NO POKEMON");
        }

        pokemonService.onCurrentPokemon().subscribe(p -> {
            Name = p.name();
            Species = p.species();
            ID = p.id();

            Height = p.profile().height();
            Weight = p.profile().weight();
            Gender = p.profile().gender();
            Ability = p.profile().ability();
        });

        JPanel PokeImage = new JPanel();
        JPanel PokeImageShadow = new JPanel();
        JPanel PokeBasic = new JPanel();
        RoundedCorners PokeStat = new RoundedCorners();
        RoundedCorners PokeStatShadow = new RoundedCorners();

        // shadow
        PokeImageShadow.setLayout(new MigLayout("center", "0[grow]0", "0[grow]0"));
        PokeImageShadow.setBackground(Color.white);
        PokeImageShadow.setBorder(
                BorderFactory.createCompoundBorder(
                        new FlatDropShadowBorder(Color.BLACK, new Insets(1, 1, 10, 1), 0.3f),
                        new EmptyBorder(0, 0, 0, 0)));

        ImageIcon image = PokemonImage.getHires(findPokemon.get(), 200, 200);

        JLabel imagetoLabel = new JLabel(image);
        PokeImage.setPreferredSize(new Dimension(280, 280));
        PokeImage.setBackground(new Color(0xE9FFFB));
        PokeImage.setBorder(BorderFactory.createLineBorder(new Color(0x808080), 15));
        PokeImage.setLayout(new MigLayout("align center center"));
        PokeImage.add(imagetoLabel);
        PokeImageShadow.add(PokeImage, "grow");

        // Basic info section
        PokeBasic.setPreferredSize(new Dimension(280, 280));
        PokeBasic.setBackground(Color.white);
        PokeBasic.setLayout(new MigLayout("", "[100%]", "[100%]"));

        JLabel pokemonID = new JLabel();
        pokemonID.setText("00" + ID);
        pokemonID.setFont(new Font("Arial", Font.BOLD, 18));
        PokeBasic.add(pokemonID, "wrap");

        JLabel pokemonName = new JLabel();
        pokemonName.setText(Name);
        pokemonName.setFont(new Font("Verdana", Font.BOLD, 18));
        PokeBasic.add(pokemonName, "wrap");

        JLabel pokeSpec = new JLabel();
        pokeSpec.setText(Species);
        pokeSpec.setFont(new Font("Verdana", Font.PLAIN, 16));
        pokeSpec.setForeground(Color.gray);
        PokeBasic.add(pokeSpec, "wrap");

        // Stats section
        JLabel pokeH = new JLabel();
        JLabel pokeW = new JLabel();
        JLabel pokeG = new JLabel();
        JLabel pokeA = new JLabel();
        Font statfont = new Font("courier", Font.PLAIN, 16);

        // shadow
        PokeStatShadow.setLayout(new MigLayout("center", "0[grow]0", "0[grow]0"));
        PokeStatShadow.setBackground(Color.white);
        PokeStatShadow.setAllRound(20);
        PokeStatShadow.setBorder(
                BorderFactory.createCompoundBorder(
                        new FlatDropShadowBorder(Color.BLACK, new Insets(1, 1, 10, 1), 0.15f),
                        new EmptyBorder(0, 0, 0, 0)));
        PokeStat.setBackground(new Color(0xFFF3C7)); // 0xFFF3C7
        PokeStat.setLayout(new MigLayout("", "5%[100%]", "[100%]"));
        PokeStat.setAllRound(20);
        pokeH.setFont(statfont);
        pokeG.setFont(statfont);
        pokeW.setFont(statfont);
        pokeA.setFont(statfont);
        pokeH.setText("Height: " + Height);
        pokeW.setText("Weight: " + Weight);
        pokeG.setText("Gender: " + Gender);
        pokeA.setText("Ability: " + Ability.get(0).name());
        PokeStat.add(pokeH, "wrap");
        PokeStat.add(pokeW, "wrap");
        PokeStat.add(pokeG, "wrap");
        PokeStat.add(pokeA, "wrap");
        PokeStatShadow.add(PokeStat, "grow");

        // Type
        JPanel typePanel = new JPanel();
        typePanel.setPreferredSize(new Dimension(100, 30));
        typePanel.setBackground(Color.white);
        typePanel.setLayout(new MigLayout("align left center"));
        PokeBasic.add(typePanel, "grow");
        RoundedCorners type1 = new RoundedCorners();
        RoundedCorners type2 = new RoundedCorners();
        type1.setPreferredSize(new Dimension(35, 18));
        type2.setPreferredSize(new Dimension(35, 18));
        type1.setAllRound(20);
        type2.setAllRound(20);
        type1.setLayout(new MigLayout("align center center"));
        type2.setLayout(new MigLayout("align center center"));

        JLabel type1Label = new JLabel();
        type1Label.setText(Type.get(0));
        JLabel type2Label = new JLabel();
        type2Label.setText(Type.get(1));

        Color typeColor = PokemonTypeColor.getColor(Type.get(0));

        type1.setBackground(typeColor);
        type2.setBackground(Color.white);

        type1.add(type1Label);
        type1Label.setFont(new Font("Arial", Font.BOLD, 14));
        type1Label.setForeground(Color.white);

        if (Type.size() == 2) {
            type2.add(type2Label);
            type2Label.setFont(new Font("Arial", Font.BOLD, 14));
            type2Label.setForeground(Color.white);
            typeColor = PokemonTypeColor.getColor(Type.get(1));
            type2.setBackground(typeColor);
        }
        typePanel.add(type1, "grow ");
        typePanel.add(type2, "grow");

        setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
        add(PokeBasic, "cell 1 0, grow");
        add(PokeStatShadow, "cell 1 1, grow");
        add(PokeImageShadow, "cell 0 0, span 1 2, grow");
        setPreferredSize(new Dimension(666, 586));
        setBackground(Color.white); // 0xF6F6F6

        pokemonService.onCurrentPokemon().subscribe(p -> {

            if (p.id() < 10) {
                zeros = "00";
            } else if (p.id() < 100) {
                zeros = "0";
            } else {
                zeros = "";
            }
            String gender = "♂ ♀";

            if (p.profile().gender().equals("Genderless")) {
                gender = "genderless";
            } else if (p.profile().gender().equals("100:0")) {
                gender = "♂";
            } else if (p.profile().gender().equals("0:100")) {
                gender = "♀";
            }

            pokemonID.setText(zeros + p.id());
            pokemonName.setText(p.name());
            pokeSpec.setText(p.species());

            ImageIcon newImage = PokemonImage.getHires(p, 200, 200);
            imagetoLabel.setIcon(newImage);

            pokeH.setText("Height: " + p.profile().height());
            pokeW.setText("Weight: " + p.profile().weight());
            pokeG.setText("Gender: " + gender);
            pokeA.setText("Ability: " + p.profile().ability().get(0).name());

            Color typCol = PokemonTypeColor.getColor(p.type().get(0));

            type1Label.setText(p.type().get(0));
            type1.setBackground(typCol);
            type2.setBackground(Color.white);

            if (p.type().size() == 2) {
                type2Label.setText(p.type().get(1));
                typCol = PokemonTypeColor.getColor(p.type().get(1));
                type2.setBackground(typCol);
            }

        });

    }
}
