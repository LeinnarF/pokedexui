import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PokemonServiceTest {
    @Test
    public void init() {
        org.nice.services.PokemonService.init();
        Assertions.assertNotNull(org.nice.services.PokemonService.getInstance());
        var pokemonList = org.nice.services.PokemonService.getInstance().getPokemonList();
        Assertions.assertEquals(898, pokemonList.size());
    }

}
