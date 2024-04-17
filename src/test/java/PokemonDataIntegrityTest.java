import org.junit.Test;
import org.nice.services.PokemonService;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonDataIntegrityTest {
    @Test
    public void getPrevEvol() {
        if(PokemonService.getInstance() == null) {
            PokemonService.init();
        }

        var service = PokemonService.getInstance();
        assertNotNull(service);

        var list = service.getPokemonList();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        for(var pokemon : list) {
            assertNotNull(pokemon);
            var prev = pokemon.getPrevEvolution();
            prev.ifPresent(v -> {
                assertNotEquals(
                    v.model().id(),
                    pokemon.id(),
                    MessageFormat
                            .format("Pokemons prev evol should not reference itself. {0} does",
                            v.model().name()
                    )
                );
            });
            var nextEvols = pokemon.getNextEvolution();
            for(var evol : nextEvols) {
                // Should not be contained in its next evolution list
                assertNotEquals(
                        evol.model().id(),
                        pokemon.id(),
                        MessageFormat.format("Pokemon should not be included in its evolution list. {0} ({1}) does", pokemon.name(), pokemon.id())
                );
            }
            assertTrue(pokemon.image().hires().isPresent(), MessageFormat.format(
                    "All pokemons should have hires images. {0} does not have hires image",
                    pokemon.name()
            ));
            assertTrue(
                    pokemon.base().isPresent(),
                    MessageFormat.format("All pokemons should have base. {0} does not", pokemon.name())
            );
        }

    }
}
