package org.nice.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record PokemonModel(
        int id,
        String name,
        List<String> type,
        Optional<BaseStats> base,
        String species,
        String description,
        Evolution evolution,
        Profile profile,
        Image image,
        ArrayList<PokemonModel> list
) {


    public record BaseStats(int HP, int Attack, int Defense, int SpAttack, int SpDefense, int Speed) {}

    public record Evolution(List<EvolutionData> next, Optional<EvolutionData> prev) {}

    public record Profile(String height, String weight, Optional<List<String>> egg, List<Ability> ability, String gender) {}

    public record Ability(String name, boolean isHidden){}
    public record Image(String sprite, String thumbnail, Optional<String> hires) {}
    public record EvolutionData(int id, String level){}

    public record EvolutionNiceData(PokemonModel model, String level) {}
    public List<EvolutionNiceData> getNextEvolution() {
        if(evolution.next.isEmpty()) {
            return List.of();
        }
        return this.evolution().next().stream().map(v-> {
                    Optional<PokemonModel> found = Optional.empty();
                    for(var i : list) {
                        if(i.id == v.id) {
                            found = Optional.of(i);
                            break;
                        }
                    }
                    return new EvolutionNiceData(
                            found.get(),
                            v.level
                    );
                }
        ).toList();
    }

    public Optional<EvolutionNiceData> getPrevEvolution() {
        if(evolution.prev.isEmpty()) {
            return Optional.empty();
        }
        return this.evolution().prev.map(v -> {
            Optional<PokemonModel> found = Optional.empty();
            for(var i : list) {
                if(i.id == v.id) {
                    found = Optional.of(i);
                    break;
                }
            }
            return new EvolutionNiceData(
                    found.get(),
                    v.level
            );
        });
    }


}