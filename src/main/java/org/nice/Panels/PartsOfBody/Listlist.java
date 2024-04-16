package org.nice.Panels.PartsOfBody;

import net.miginfocom.swing.MigLayout;
import org.nice.lib.listview.DynamicListView;
import org.nice.lib.listview.Item;
import org.nice.models.PokemonModel;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;
import org.nice.services.SearchService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class Listlist extends JScrollPane {
    public Listlist(){
        var pokeList = PokemonService.getInstance().filterPokemons(List.of());
        var list = new DynamicListView<>(
            pokeList,
            v -> {
                var view = new JPanel(new MigLayout());
                view.add(new JLabel(String.valueOf(v.id())));
                view.add(new JLabel(v.name()));
                return new Item<>(view, Optional.empty());
            },
            new Item<>(new JLabel("nice"),Optional.empty()),
            new MigLayout("wrap, align center", "grow")
        );
        setViewportView(list);
        getVerticalScrollBar().setUnitIncrement(20);


    }
}
