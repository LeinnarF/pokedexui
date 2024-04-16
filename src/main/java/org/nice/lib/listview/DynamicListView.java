package org.nice.lib.listview;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DynamicListView<T> extends JPanel {

    private Collection<T> items;

    private final FunctionArg1<Item<JComponent>, T> viewBuilder ;
    private final Item<JComponent> defaultView;
    private LayoutManager providedLayout;


    public DynamicListView(
            Collection<T> items,
            FunctionArg1<Item<JComponent>, T> viewBuilder,
            Item<JComponent> defaultView
    ) {
        this.defaultView = defaultView;
        this.items = items;
        this.viewBuilder = viewBuilder;

        init();
    }

    public DynamicListView(
            Collection<T> items,
            FunctionArg1<Item<JComponent>, T> viewBuilder,
            Item<JComponent> defaultView,
            LayoutManager layout
    ) {
        this.providedLayout = layout;
        this.defaultView = defaultView;
        this.items = items;
        this.viewBuilder = viewBuilder;

        this.setLayout(this.providedLayout);
        init();
    }


    public void updateItems(Collection<T> items) {
        removeAll();
        this.items = items;
        init();
    }

    private void init() {

        for(var item : items) {
            var returnedItem = viewBuilder.call(item);
            var itemView = returnedItem.item();
            add(itemView, returnedItem.addOptions().orElse(""));
        }
        if(items.isEmpty()) {
            add(defaultView.item(), defaultView.addOptions().orElse(""));
        }
    }
}
