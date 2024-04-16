package org.nice.lib.navigation;

public interface Action<T> {
    void invoke(T obj);
}
