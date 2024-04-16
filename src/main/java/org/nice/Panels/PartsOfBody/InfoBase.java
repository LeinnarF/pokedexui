package org.nice.Panels.PartsOfBody;

import java.awt.*;
import java.util.Optional;
import java.util.List;

import javax.swing.*;

import org.nice.lib.roundcorner.RoundedCorners;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonTypeColor;
import org.nice.services.PokemonService;

import net.miginfocom.swing.MigLayout;
import rx.Observable;

public class InfoBase extends JPanel  {

    PokemonService pokemonService = PokemonService.getInstance();
    Observable<PokemonModel> currentPokemonModel = pokemonService.onCurrentPokemon();
    String Name, Height, Weight, Species, Gender;
    int ID;
    List<String> Type;

    public InfoBase() {

    Optional<PokemonModel> findPokemon = pokemonService.getPokemon(887);
    if(findPokemon.isPresent()){
        PokemonModel pokeModel = findPokemon.get();
        Name = pokeModel.name().english();
        Species = pokeModel.species();
        ID = pokeModel.id();
        Gender = pokeModel.profile().gender();
        Height = pokeModel.profile().height();
        Weight = pokeModel.profile().weight();
        Type = pokeModel.type();
        

    }else {System.out.println("NO POKEMON");}
    
    String zeros = "";
    if(ID < 10){
        zeros = "00";
    }
    else if (ID < 100){
        zeros = "0";
    }
    
    JPanel PokeImage = new JPanel(); 
    JPanel PokeBasic = new JPanel(); 
    RoundedCorners PokeStat  = new RoundedCorners(); 
    
    ImageIcon image = new ImageIcon("src/main/resources/images/hires/"+zeros+ID+".png");  
    Image img = image.getImage(); 
    Image newimg = img.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    image = new ImageIcon(newimg);  
    JLabel imagetoLabel = new JLabel(image);
    PokeImage.setPreferredSize(new Dimension(280,280));
    PokeImage.setBackground(new Color(0xE9FFFB));
    PokeImage.setBorder(BorderFactory.createLineBorder(new Color(0xD9D9D9), 15));  
    PokeImage.setLayout(new MigLayout("align center center"));
    PokeImage.add((imagetoLabel));
    
    // Basic info section
    PokeBasic.setPreferredSize(new Dimension(280,280));
    PokeBasic.setBackground(new Color(0xF6F6F6));
    PokeBasic.setLayout(new MigLayout("","[100%]","[100%]"));

    PokeBasic.add(new JLabel("No. "+zeros+ID){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
    PokeBasic.add(new JLabel(Name){{setFont(new Font("Arial",Font.BOLD, 16));}}, "wrap");
    PokeBasic.add(new JLabel(Species){{
            setFont(new Font("Arial",Font.PLAIN, 16));
            setForeground(Color.gray);
        }}, "wrap");

    // Stats section
    PokeStat.setPreferredSize(new Dimension(280,280));
    PokeStat.setBackground(new Color(0xFFF3C7));
    PokeStat.setLayout(new MigLayout("","5%[100%]","[100%]"));
    PokeStat.setAllRound(20);
    PokeStat.add(new JLabel("Height: "+Height){{setFont(new Font("Courier",Font.PLAIN, 16));}}, "wrap");
    PokeStat.add(new JLabel("Weight: "+Weight){{setFont(new Font("Courier",Font.PLAIN, 16));}}, "wrap");
    PokeStat.add(new JLabel("Gender: "+Gender){{setFont(new Font("Courier",Font.PLAIN, 16));}}, "wrap");
    
    //type -> PokemBasic
    

    JPanel typePanel = new JPanel();
    typePanel.setPreferredSize(new Dimension(100,30));
    typePanel.setBackground(new Color(0xF6F6F6));
    typePanel.setLayout(new MigLayout("align left center"));
    PokeBasic.add(typePanel,"grow");
    RoundedCorners type1 = new RoundedCorners();
    RoundedCorners type2 = new RoundedCorners();
    type1.setPreferredSize(new Dimension(35,18));
    type2.setPreferredSize(new Dimension(35,18));
    type1.setAllRound(20);
    type2.setAllRound(20);
    type1.setLayout(new MigLayout("align center center"));
    type2.setLayout(new MigLayout("align center center"));
    
    Color typeColor = PokemonTypeColor.getColor(Type.get(0));

    type1.setBackground(typeColor);
    type2.setBackground(new Color(0xF6F6F6));

    type1.add(new JLabel(Type.get(0)){{
        setFont(new Font("Arial", Font.BOLD,14));
        setForeground(Color.white);
    }});
    if(Type.size()==2){
        type2.add(new JLabel(Type.get(1)){{
            setFont(new Font("Arial", Font.BOLD,14));
            setForeground(Color.white);
        }});
        typeColor = PokemonTypeColor.getColor(Type.get(1));
        type2.setBackground(typeColor);
    }
    typePanel.add(type1,"grow ");
    typePanel.add(type2,"grow");

    setLayout(new MigLayout("", "[50%][50%]", "[50%][50%]"));
    add(PokeBasic,"cell 1 0, grow");
    add(PokeStat,"cell 1 1, grow");
    add(PokeImage,"cell 0 0, span 1 2, grow");
    setPreferredSize(new Dimension(666,586));
    setBackground(new Color(0xF6F6F6)); // 0xF6F6F6
    }
}
