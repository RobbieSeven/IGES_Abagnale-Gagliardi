package com.giordanogiammaria.microapp30;

import java.util.ArrayList;

/**
 * Created by Roberto on 15/01/2018.
 */

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

    public boolean contains(T data) {
        return true;
    }

}
