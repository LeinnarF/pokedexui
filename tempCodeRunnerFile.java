import java.awt.Color;
import java.util.HashMap;

public class PokemonTypeColor {

    private static final HashMap<String, Color> typeColors = new HashMap<>();

    static {
        typeColors.put("Normal", Color.decode("#D9D9D9"));
        typeColors.put("Fighting", Color.decode("#BC7878"));
        typeColors.put("Flying", Color.decode("#D6D4F6"));
        typeColors.put("Poison", Color.decode("#AA70D8"));
        typeColors.put("Ground", Color.decode("#F4CE94"));
        typeColors.put("Rock", Color.decode("#C1AA95"));
        typeColors.put("Bug", Color.decode("#B5C782"));
        typeColors.put("Ghost", Color.decode("#847598"));
        typeColors.put("Steel", Color.decode("#8996A6"));
        typeColors.put("Fire", Color.decode("#F19C6C"));
        typeColors.put("Water", Color.decode("#71A2ED"));
        typeColors.put("Grass", Color.decode("#80D183"));
        typeColors.put("Electric", Color.decode("#FFDF6D"));
        typeColors.put("Psychic", Color.decode("#ED79B1"));
        typeColors.put("Ice", Color.decode("#A7EFFF"));
        typeColors.put("Dragon", Color.decode("#7570B1"));
        typeColors.put("Dark", Color.decode("#5F636C"));
        typeColors.put("Fairy", Color.decode("#FFCFF7"));
    }

    public static Color getColor(String type) {
        return typeColors.getOrDefault(type, Color.WHITE); // Default to white if type not found
    }

    public static void main(String[] args) {
        String pokemonType = "Water";
        Color color = getColor(pokemonType);
        System.out.println(color);
    }
}
