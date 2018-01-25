package com.giordanogiammaria.microapp30;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public abstract class ComponentFragment extends Fragment {

    private ComponentType type;
    private HashMap<String, DataType> inputTypes;
    private ArrayList<DataType> outputTypes;

    public abstract HashMap<String, DataType> getInputTypes();
    public abstract ArrayList<DataType> getOutputTypes();
    public abstract void setInputsData(HashMap<String, GenericData> dataCollection);
    public abstract HashMap<DataType, GenericData> getOutputsData();
    
}
