package org.nice.models;

import java.util.List;
import java.util.Optional;

public record PokemonModel(
        int id,
        Name name,
        List<String> type,
        Optional<BaseStats> base,
        String species,
        String description,
        Evolution evolution,
        Profile profile,
        Image image
) {
    public record Name(String english, String japanese, String chinese, String french) {}

    public record BaseStats(int HP, int Attack, int Defense, int SpAttack, int SpDefense, int Speed) {}

    public record Evolution(Optional<List<List<String>>> next, Optional<List<String>> prev) {}

    public record Profile(String height, String weight, Optional<List<String>> egg, List<List<String>> ability, String gender) {}

    public record Image(String sprite, String thumbnail, Optional<String> hires) {}
}