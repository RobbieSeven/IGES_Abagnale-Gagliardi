package com.giordanogiammaria.microapp30.Activity;

import com.giordanogiammaria.microapp30.ComponentFragment;
import com.giordanogiammaria.microapp30.DataType;
import com.giordanogiammaria.microapp30.GenericData;

import java.util.HashMap;

/**
 * Created by Roberto on 29/01/2018.
 */

public class BlankFragment extends ComponentFragment {

    private String string;

    public BlankFragment() {
        super();
        inputTypes.put("string", DataType.STRING);
        outputTypes.add(DataType.STRING);
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
    public HashMap<DataType, GenericData> getOutputsData() {
        HashMap<DataType, GenericData> outputData = new HashMap<>();
        GenericData<String> data = new GenericData<>();
        data.addData(string);
        outputData.put(DataType.STRING, data);
        System.out.println(string);
        return outputData;
    }
}
