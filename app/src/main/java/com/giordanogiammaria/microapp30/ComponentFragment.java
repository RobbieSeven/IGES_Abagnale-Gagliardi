package com.giordanogiammaria.microapp30;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public abstract class ComponentFragment extends Fragment{

    protected ComponentType type;
    protected HashMap<String, DataType> inputTypes;
    protected ArrayList<DataType> outputTypes;
    public ComponentFragment(){
        type=ComponentType.BLANK;
        inputTypes=new HashMap<>();
        outputTypes=new ArrayList<>();
    }

    public ComponentType getType() {
        return type;
    }

    public HashMap<String, DataType> getInputTypes() {
        return inputTypes;
    }

    public ArrayList<DataType> getOutputTypes() {
        return outputTypes;
    }

    public abstract void setInputsData(HashMap<String, GenericData> dataCollection);
    public abstract HashMap<DataType, GenericData> getOutputsData();
    
}
