package org.nice.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PokemonTypeWeakness {

    private static HashMap<String, String[]> weaknesses;
    private static HashMap<String, String[]> resistances;

    public PokemonTypeWeakness() {
        weaknesses = new HashMap<>();
        resistances = new HashMap<>();

        // Define weaknesses and resistances(including immunities) for each type
        weaknesses.put("Normal", new String[] { "Fighting" });
        weaknesses.put("Fire", new String[] { "Water", "Rock", "Ground" });
        weaknesses.put("Water", new String[] { "Electric", "Grass" });
        weaknesses.put("Electric", new String[] { "Ground" });
        weaknesses.put("Grass", new String[] { "Fire", "Ice", "Poison", "Flying", "Bug" });
        weaknesses.put("Ice", new String[] { "Fire", "Fighting", "Rock", "Steel" });
        weaknesses.put("Fighting", new String[] { "Flying", "Psychic", "Fairy" });
        weaknesses.put("Poison", new String[] { "Ground", "Psychic" });
        weaknesses.put("Ground", new String[] { "Water", "Grass", "Ice" });
        weaknesses.put("Flying", new String[] { "Electric", "Ice", "Rock" });
        weaknesses.put("Psychic", new String[] { "Bug", "Ghost", "Dark" });
        weaknesses.put("Bug", new String[] { "Fire", "Flying", "Rock" });
        weaknesses.put("Rock", new String[] { "Water", "Grass", "Fighting", "Ground", "Steel" });
        weaknesses.put("Ghost", new String[] { "Ghost", "Dark" });
        weaknesses.put("Dragon", new String[] { "Ice", "Dragon", "Fairy" });
        weaknesses.put("Dark", new String[] { "Fighting", "Bug", "Fairy" });
        weaknesses.put("Steel", new String[] { "Fire", "Fighting", "Ground" });
        weaknesses.put("Fairy", new String[] { "Poison", "Steel" });

        resistances.put("Fire", new String[] { "Fire", "Grass", "Ice", "Bug", "Steel", "Fairy" });
        resistances.put("Water", new String[] { "Water", "Fire", "Ice", "Steel" });
        resistances.put("Electric", new String[] { "Electric", "Flying", "Steel" });
        resistances.put("Grass", new String[] { "Water", "Electric", "Grass", "Ground" });
        resistances.put("Ice", new String[] { "Ice" });
        resistances.put("Fighting", new String[] { "Bug", "Rock", "Dark" });
        resistances.put("Poison", new String[] { "Grass", "Fighting", "Poison", "Bug", "Fairy" });
        resistances.put("Ground", new String[] { "Poison", "Rock", "Electric" });
        resistances.put("Flying", new String[] { "Grass", "Fighting", "Bug", "Ground" });
        resistances.put("Psychic", new String[] { "Fighting", "Psychic" });
        resistances.put("Bug", new String[] { "Grass", "Fighting", "Ground" });
        resistances.put("Rock", new String[] { "Normal", "Fire", "Poison", "Flying" });
        resistances.put("Ghost", new String[] { "Poison", "Bug", "Normal", "Fighting" });
        resistances.put("Dragon", new String[] { "Fire", "Water", "Electric", "Grass" });
        resistances.put("Dark", new String[] { "Ghost", "Dark", "Psychic" });
        resistances.put("Steel", new String[] { "Normal", "Grass", "Ice", "Flying", "Psychic", "Bug", "Rock", "Dragon",
                "Steel", "Fairy", "Poison" });
        resistances.put("Fairy", new String[] { "Fighting", "Bug", "Dark", "Dragon" });
        resistances.put("Normal", new String[] { "Ghost" });
    }

    public String[] getWeaknesses(String type) {
        return getCombinedWeaknesses(type, null);
    }

    public String[] getWeaknesses(String type1, String type2) {
        return getCombinedWeaknesses(type1, type2);
    }

    private static String[] getCombinedWeaknesses(String type1, String type2) {
        Set<String> combinedWeaknesses = new HashSet<>();

        // Get weaknesses for the first type
        String[] weaknesses1 = weaknesses.getOrDefault(type1, new String[] {});
        for (String weakness : weaknesses1) {
            if (type2 == null || !isResistantTo(type2, weakness)) {
                combinedWeaknesses.add(weakness);
            }
        }
        // If second type is provided, add its weaknesses as well
        if (type2 != null) {
            String[] weaknesses2 = weaknesses.getOrDefault(type2, new String[] {});
            for (String weakness : weaknesses2) {
                if (!isResistantTo(type1, weakness)) {
                    combinedWeaknesses.add(weakness);
                }
            }
        }

        return combinedWeaknesses.toArray(new String[0]);
    }

    private static boolean isResistantTo(String type, String target) {
        String[] resistancesOfType = resistances.getOrDefault(type, new String[] {});
        for (String resistance : resistancesOfType) {
            if (resistance.equals(target)) {
                return true;
            }
        }
        return false;
    }

    // for testing
    // public static void main(String[] args) {
    // PokemonTypeWeakness typeWeakness = new PokemonTypeWeakness();

    // String[] weaknessesSingleType = typeWeakness.getWeaknesses("Water","Ground");
    // System.out.println("Weaknesses: "+String.join(", ", weaknessesSingleType));
    // }
}
