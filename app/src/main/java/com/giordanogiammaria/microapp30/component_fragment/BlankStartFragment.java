package com.giordanogiammaria.microapp30.component_fragment;

import com.giordanogiammaria.microapp30.GenericData;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;

import java.util.ArrayList;
import java.util.HashMap;


public class BlankStartFragment extends ComponentFragment {

    @Override
    protected ComponentType setType() {
        return ComponentType.BLANKSTART;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        return new HashMap<String, DataType>();
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType> outputTypes = new ArrayList<>();
        outputTypes.add(DataType.STRING);
        return outputTypes;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {

    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
        HashMap<DataType, GenericData> dataCollection = new HashMap<>();
        GenericData<String> data = new GenericData<>();
        data.addData("string");
        dataCollection.put(DataType.STRING, data);
        return dataCollection;
    }

}
