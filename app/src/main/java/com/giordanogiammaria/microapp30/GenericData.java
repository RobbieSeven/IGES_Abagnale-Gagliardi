package com.giordanogiammaria.microapp30;

import java.util.ArrayList;


public class GenericData<T> {

    ArrayList<T> data;

    public GenericData() {
        data = new ArrayList<>();
    }

    public ArrayList<T> getData() {
        return data;
    }

    public T addData(T newData) {
        data.add(newData);
        return newData;
    }

    public boolean contains(T singleData) {
        return data.contains(singleData);
    }

}
