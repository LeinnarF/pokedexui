package org.nice.services;

import org.nice.Utils;
import org.nice.models.PokemonModel;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class PokemonImage {
    private static Map<String, ImageIcon> thumbnailMap = new HashMap<>();
    private static Map<String, ImageIcon> hiresMap = new HashMap<>();
    private static Map<String, ImageIcon> spriteMap = new HashMap<>();


    public static ImageIcon getThumbnail(PokemonModel pokemon) {
        var id = pokemon.id();
        var key = String.valueOf(id);
        if(thumbnailMap.containsKey(key)) {
            return thumbnailMap.get(key);
        }
        var image = Utils.getImage(
          pokemon.image().thumbnail()
        );

        thumbnailMap.put(key, image);
        return image;
    }
    public static ImageIcon getSprite(PokemonModel pokemon) {
        var id = pokemon.id();
        var key = String.valueOf(id);
        if(spriteMap.containsKey(key)) {
            return spriteMap.get(key);
        }
        var image = Utils.getImage(
                PokemonService.getInstance().getPokemon(id).get().image().sprite()
        );

        spriteMap.put(key, image);
        return image;
    }

    public static ImageIcon getHires(PokemonModel pokemon) {
        var id = pokemon.id();
        var key = String.valueOf(id);

        if(hiresMap.containsKey(key)) {
            return hiresMap.get(key);
        }
        var p = PokemonService.getInstance().getPokemon(id).get();
        var image = Utils.getImage(
                p.image().hires().orElse(p.image().thumbnail())
        );

        hiresMap.put(key, image);
        return image;
    }

    public static ImageIcon getHires(PokemonModel pokemon, int width, int height) {
        var id = pokemon.id();
        var key = MessageFormat.format("{0}|{1}:{2}",id, width,height);

        if(hiresMap.containsKey(key)) {
            return hiresMap.get(key);
        }
        var image = Utils.getImage(
                pokemon.image().hires().orElse(pokemon.image().thumbnail()),
                width,
                height
        );

        hiresMap.put(key, image);
        return image;
    }

    public static ImageIcon getThumbnail(PokemonModel pokemon, int width, int height) {
        var id = pokemon.id();
        var key = MessageFormat.format("{0}|{1}:{2}",id, width,height);

        if(thumbnailMap.containsKey(key)) {
            return thumbnailMap.get(key);
        }
        var image = Utils.getImage(
                pokemon.image().thumbnail(),
                width,
                height
        );

        thumbnailMap.put(key, image);
        return image;
    }

    public static ImageIcon getSprite(PokemonModel pokemon, int width, int height) {
        var id = pokemon.id();
        var key = MessageFormat.format("{0}|{1}:{2}",id, width,height);

        if(spriteMap.containsKey(key)) {
            return spriteMap.get(key);
        }
        var image = Utils.getImage(
                pokemon.image().sprite(),
                width, height
        );

        spriteMap.put(key, image);
        return image;
    }


}
