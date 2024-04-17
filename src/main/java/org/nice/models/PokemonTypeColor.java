package org.nice.models;

import java.util.HashMap;
import java.awt.*;

public class PokemonTypeColor {

    private static final HashMap<String, Color> typeColors = new HashMap<>();

    static {
        typeColors.put("Normal", new Color(0xD9D9D9));
        typeColors.put("Fighting", new Color(0xBC7878));
        typeColors.put("Flying", new Color(0xD6D4F6));
        typeColors.put("Poison", new Color(0xAA70D8));
        typeColors.put("Ground", new Color(0xF4CE94));
        typeColors.put("Rock", new Color(0xC1AA95));
        typeColors.put("Bug", new Color(0xB5C782));
        typeColors.put("Ghost", new Color(0x847598));
        typeColors.put("Steel", new Color(0x8996A6));
        typeColors.put("Fire", new Color(0xF19C6C));
        typeColors.put("Water", new Color(0x71A2ED));
        typeColors.put("Grass", new Color(0x80D183));
        typeColors.put("Electric", new Color(0xFFDF6D));
        typeColors.put("Psychic", new Color(0xED79B1));
        typeColors.put("Ice", new Color(0xA7EFFF));
        typeColors.put("Dragon", new Color(0x7570B1));
        typeColors.put("Dark", new Color(0x5F636C));
        typeColors.put("Fairy", new Color(0xFFCFF7));
    }

    public static Color getColor(String type) {
        return typeColors.getOrDefault(type, new Color(0xf6f6f6));
    }
}
