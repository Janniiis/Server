package com.btcag.bootcamp;

import java.util.ArrayList;
import java.util.List;

public class RequestsGenerics<T> {

    private List<T> items = new ArrayList<>();

    public void createItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            return null;
        }
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public int getSize() {
        return items.size();
    }
}
