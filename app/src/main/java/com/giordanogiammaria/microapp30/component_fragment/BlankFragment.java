package com.giordanogiammaria.microapp30.component_fragment;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;

import java.util.ArrayList;
import java.util.HashMap;


//questa è un Fragment blank di prova
public class BlankFragment extends ComponentFragment {

    private String string;

    public BlankFragment() {
        super();
        inputTypes.put("string", DataType.STRING);
        outputTypes.add(DataType.STRING);
    }

    @Override
    protected ComponentType setType() {
        return ComponentType.BLANK;
    }

    @Override
    protected HashMap<String, DataType> setInputTypes() {
        HashMap<String,DataType> inputTypes = new HashMap<>();
        inputTypes.put("string", DataType.STRING);
        return inputTypes;
    }

    @Override
    protected ArrayList<DataType> setOutputTypes() {
        ArrayList<DataType>outputTypes = new ArrayList<>();
        // outputTypes.add(DataType.STRING);
        return outputTypes;
    }

    @Override
    public void setInputsData(HashMap<String, GenericData> dataCollection) {
        String newString = (String) dataCollection.get("string").getData().get(0);
        if (newString != null)
            string = newString;
        else
            string = "string";
        System.out.println(string);
    }

    @Override
    public HashMap<DataType, GenericData> getOutputData() {
        HashMap<DataType, GenericData> outputData = new HashMap<>();
        GenericData<String> data = new GenericData<>();
        data.addData(string);
        outputData.put(DataType.STRING, data);
        System.out.println(string);
        return outputData;
    }
}
