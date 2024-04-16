package org.nice.Components.Search;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.models.PokemonType;
import org.nice.services.PokemonService;
import org.nice.services.SearchService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterBtn extends JButton  {

    public FilterBtn() {
        setText("Filter");
        addActionListener(v -> {
            new FilterModal().setVisible(true);
        });
    }
}


class FilterModal extends JDialog {
    public FilterModal() {
//        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Filter Options");
//        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(480,480));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        var root = new JPanel(new MigLayout("align center center, wrap 2, insets 24", "grow"));
        add(root);

        var unselectAllBtn = new JButton("Unselect All");
        var selectAllBtn = new JButton("Select All");
        root.add(unselectAllBtn, "grow");

        var currentFilters = SearchService.getInstance().currentTypeFilters();
        var btnMap = new HashMap<String, JCheckBox>();
        for (PokemonType value : PokemonType.values()) {
            var btn = new JCheckBox(value.name(), currentFilters.contains(value));
            root.add(btn, "grow");
            btnMap.put(value.name(), btn);
            btn.addActionListener(e -> {
                var list = new ArrayList<>(SearchService.getInstance().currentTypeFilters());

                if(btn.isSelected()) {
                    list.add(value);
                    if(list.size() > 2) {
                        list.remove(0);
                    }
                } else {
                    list.remove(value);
                }
                SearchService.getInstance().setTypeFilters(
                        list
                );
            });
        }

        unselectAllBtn.addActionListener(e -> {
            SearchService.getInstance().setTypeFilters(List.of());
        });

        SearchService.getInstance().onTypeFilterChange().subscribe(typeList -> {
            btnMap.keySet().forEach(type -> {
                var btn = btnMap.get(type);
                btn.setSelected(typeList.contains(PokemonType.valueOf(type)));
            });
        });


    }
}