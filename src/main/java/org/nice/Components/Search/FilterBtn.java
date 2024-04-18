package org.nice.Components.Search;

import net.miginfocom.swing.MigLayout;
import org.nice.Main;
import org.nice.Utils;
import org.nice.models.PokemonType;
import org.nice.models.PokemonTypeColor;
import org.nice.services.PokemonService;
import org.nice.services.SearchService;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.FlatRoundBorder;

import rx.Subscription;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterBtn extends JButton {

    public FilterBtn() {

        addActionListener(v -> {
            FilterModal modal = new FilterModal();
            Point p = this.getLocationOnScreen();
            modal.setLocation(p.x, p.y + this.getHeight());
            modal.setVisible(true);
        });

        ImageIcon icon = new ImageIcon(Utils.getResource("/images/icons/filter.png"));
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        setIcon(icon);
        setBackground(Color.decode("#e46666"));
        setFocusable(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }
}

class FilterModal extends JDialog {
    private final Subscription subscription;

    @Override
    public void removeNotify() {
        super.removeNotify();
        subscription.unsubscribe();
    }

    public FilterModal() {
        setTitle("");
        setUndecorated(true);
        setMinimumSize(new Dimension(120, 520));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        var root = new JPanel(new MigLayout("align center, wrap"));

        root.setBackground(Color.decode("#f0f0f0"));

        add(root);

        var unselectAllBtn = new JButton("Unselect All");
        unselectAllBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        unselectAllBtn.setFocusable(false);

        root.add(unselectAllBtn, "grow");

        var currentFilters = SearchService.getInstance().currentTypeFilters();
        var btnMap = new HashMap<String, JCheckBox>();
        for (PokemonType value : PokemonType.values()) {
            var btn = new JCheckBox(value.name(), currentFilters.contains(value));
            root.add(btn, "grow");
            btnMap.put(value.name(), btn);
            btn.addActionListener(e -> {
                var list = new ArrayList<>(SearchService.getInstance().currentTypeFilters());

                if (btn.isSelected()) {
                    list.add(value);
                    if (list.size() > 2) {
                        list.remove(0);
                    }
                } else {
                    list.remove(value);
                }
                SearchService.getInstance().setTypeFilters(
                        list);
            });
            Color color = PokemonTypeColor.getColor(value.name());
            btn.setBackground(color);
            btn.setForeground(Color.white);
            btn.setFont(new Font("Verdana", Font.BOLD, 12));
            btn.setFocusable(false);

        }

        unselectAllBtn.addActionListener(e -> {
            SearchService.getInstance().setTypeFilters(List.of());
        });

        this.subscription = SearchService.getInstance().onTypeFilterChange().subscribe(typeList -> {
            btnMap.keySet().forEach(type -> {
                var btn = btnMap.get(type);
                btn.setSelected(typeList.contains(PokemonType.valueOf(type)));
            });
        });
        this.addWindowFocusListener(new WindowFocusListener() {

            public void windowGainedFocus(WindowEvent e) {
                // do nothing
            }

            public void windowLostFocus(WindowEvent e) {
                if (SwingUtilities.isDescendingFrom(e.getOppositeWindow(), FilterModal.this)) {
                    return;
                }
                FilterModal.this.dispose();
            }

        });

    }
}