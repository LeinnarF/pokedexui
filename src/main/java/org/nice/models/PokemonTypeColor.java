package org.nice.models;

import java.util.HashMap;
import java.awt.*;

public class PokemonTypeColor {

    private static final HashMap<String, Color> typeColors = new HashMap<>();

    static {
        typeColors.put("Normal", new Color(0xB8B892));
        typeColors.put("Fighting", new Color(0xC56262));
        typeColors.put("Flying", new Color(0xB19EEA));
        typeColors.put("Poison", new Color(0xAA70D8));
        typeColors.put("Ground", new Color(0xE4BF86));
        typeColors.put("Rock", new Color(0xA68E6A));
        typeColors.put("Bug", new Color(0xC4CD78));
        typeColors.put("Ghost", new Color(0x847598));
        typeColors.put("Steel", new Color(0x8996A6));
        typeColors.put("Fire", new Color(0xF19C6C));
        typeColors.put("Water", new Color(0x71A2ED));
        typeColors.put("Grass", new Color(0x80D183));
        typeColors.put("Electric", new Color(0xFFDF6D));
        typeColors.put("Psychic", new Color(0xFF608F));
        typeColors.put("Ice", new Color(0x87E0E0));
        typeColors.put("Dragon", new Color(0x6157D6));
        typeColors.put("Dark", new Color(0x3A3531));
        typeColors.put("Fairy", new Color(0xFFAEC9));
    }

    public static Color getColor(String type) {
        return typeColors.getOrDefault(type, new Color(0xf6f6f6));
    }
}
