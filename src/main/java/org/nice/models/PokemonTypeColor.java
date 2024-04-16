package org.nice.models;

import java.util.HashMap;
import java.awt.*;

public class PokemonTypeColor {

    private static final HashMap<String, String> typeColors = new HashMap<>();

    static {
        typeColors.put("Normal", "0xD9D9D9");
        typeColors.put("Fighting", "0xBC7878");
        typeColors.put("Flying", "0xD6D4F6");
        typeColors.put("Poison", "0xAA70D8");
        typeColors.put("Ground", "0xF4CE94");
        typeColors.put("Rock", "0xC1AA95");
        typeColors.put("Bug", "0xB5C782");
        typeColors.put("Ghost", "0x847598");
        typeColors.put("Steel", "0x8996A6");
        typeColors.put("Fire", "0xF19C6C");
        typeColors.put("Water", "0x71A2ED");
        typeColors.put("Grass", "0x80D183");
        typeColors.put("Electric", "0xFFDF6D");
        typeColors.put("Psychic", "0xED79B1");
        typeColors.put("Ice", "0xA7EFFF");
        typeColors.put("Dragon", "0x7570B1");
        typeColors.put("Dark", "0x5F636C");
        typeColors.put("Fairy", "0xFFCFF7");
    }

    public static Color getColor(String type) {
        String hexColor = typeColors.getOrDefault(type, "0xf6f6f6");
        return Color.decode(hexColor);
    }
}
