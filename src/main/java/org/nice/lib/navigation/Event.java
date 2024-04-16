package org.nice.lib.navigation;

import java.util.ArrayList;


public class Event<T> {

    protected ArrayList<Action<T>> listeners = new ArrayList<>();

    public void addListener(Action<T> arg0){
        listeners.add(arg0);
    }

    public void removeListener(Action<T> arg0){
        listeners.remove(arg0);
    }

    public void invoke(T args){
        for (Action<T> listener : listeners) {
            listener.invoke(args);
        }
    }
}
