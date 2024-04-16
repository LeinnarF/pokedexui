package org.nice.Panels.PartsOfBody;

import net.miginfocom.swing.MigLayout;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class Listlist extends JScrollPane {
    public Listlist(){
        var list = new DynamicListView<>(
            PokemonService.getInstance().filterPokemons(List.of()),
            pokemonModel -> String.valueOf(pokemonModel.id()),
            v -> {
                var view = new JPanel(new MigLayout());
                view.add(new JLabel(String.valueOf(v.id())));
                view.add(new JLabel(v.name().english()));
                return new Item<>(view, Optional.empty());
            },
            new Item<>(new JLabel("nice"),Optional.empty()),
            new MigLayout("wrap, align center", "grow")
        );
        setViewportView(list);
        getVerticalScrollBar().setUnitIncrement(20);
    }
}
