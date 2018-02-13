package com.giordanogiammaria.microapp30.component_fragment;

import android.app.Fragment;

import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.GenericData;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class ComponentFragment extends Fragment{

    protected ComponentType type;
    protected HashMap<String, DataType> inputTypes;
    protected ArrayList<DataType> outputTypes;

    public ComponentFragment(){
        type=setType();
        inputTypes=setInputTypes();
        outputTypes=setOutputTypes();
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

    protected abstract ComponentType setType();
    protected abstract  HashMap<String, DataType> setInputTypes();
    protected abstract ArrayList<DataType> setOutputTypes();

    public abstract void setInputsData(HashMap<String, GenericData> dataCollection);
    public abstract HashMap<DataType, GenericData> getOutputsData();
    
}
