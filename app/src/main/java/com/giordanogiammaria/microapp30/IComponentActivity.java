package com.giordanogiammaria.microapp30;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public interface IComponentActivity {

    HashMap<String, DataType> getInputTypes();
    ArrayList<DataType> getOutputTypes();
    void setInputsData(HashMap<String, GenericData> dataCollection);
    HashMap<DataType, GenericData> getOutputsData();
    
}
