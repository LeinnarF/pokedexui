package org.nice.lib.listview;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DynamicListView<T> extends JPanel {

    private final Collection<T> items;

    private final Map<String, JComponent> itemViewMap = new HashMap<>();

    private final FunctionArg1<Item<JComponent>, T> viewBuilder ;
    private final FunctionArg1<String,T> keyBuilder;
    private final Item<JComponent> defaultView;
    private LayoutManager providedLayout;

    /**
     * Must be called everytime the provided list changes to reflect the change in the ui as well.
     */
    public void update() {
        var updatedMap = new HashMap<String, T>();
        for(var item : items) {
            var key = keyBuilder.call(item);
            updatedMap.put(key, item);

            var wasAdded = !itemViewMap.containsKey(key);
            if(wasAdded) {
                var returnedItem  = viewBuilder.call(updatedMap.get(key));
                itemViewMap.put(
                        key,
                        returnedItem.item()
                );
                add(returnedItem.item(), returnedItem.addOptions().orElse(""));
            }
        }

        var toRemove = new ArrayList<String>();
        for(var itemKey : itemViewMap.keySet()) {
            var wasRemoved =  !updatedMap.containsKey(itemKey);
            if(wasRemoved) {
                toRemove.add(itemKey);
            }
        }

        for(var itemKey : toRemove) {
            var view = itemViewMap.get(itemKey);
            remove(view);
            itemViewMap.remove(itemKey);
        }

        if(items.isEmpty()) {
            add(defaultView.item(), defaultView.addOptions().orElse(""));
        } else {
            remove(defaultView.item());
        }
        revalidate();
        repaint();

    }

    public DynamicListView(
            Collection<T> items,
            FunctionArg1<String, T> keyBuilder,
            FunctionArg1<Item<JComponent>, T> viewBuilder,
            Item<JComponent> defaultView
    ) {
        this.defaultView = defaultView;
        this.items = items;
        this.keyBuilder = keyBuilder;
        this.viewBuilder = viewBuilder;

        init();
    }

    public DynamicListView(
            Collection<T> items,
            FunctionArg1<String, T> keyBuilder,
            FunctionArg1<Item<JComponent>, T> viewBuilder,
            Item<JComponent> defaultView,
            LayoutManager layout
    ) {
        this.providedLayout = layout;
        this.defaultView = defaultView;
        this.items = items;
        this.keyBuilder = keyBuilder;
        this.viewBuilder = viewBuilder;

        this.setLayout(this.providedLayout);
        init();
    }

    private void init() {

        for(var item : items) {
            var id = keyBuilder.call(item);
            var returnedItem = viewBuilder.call(item);
            var itemView = returnedItem.item();
            itemViewMap.put(id, itemView);
            add(itemView, returnedItem.addOptions().orElse(""));
        }
        if(items.isEmpty()) {
            add(defaultView.item(), defaultView.addOptions().orElse(""));
        }
    }
}
