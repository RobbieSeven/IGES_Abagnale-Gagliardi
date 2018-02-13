package com.giordanogiammaria.microapp30;

import android.util.Log;

import com.giordanogiammaria.microapp30.Subsystem.MissingInputException;
import com.giordanogiammaria.microapp30.Subsystem.MissingOutputException;
import com.giordanogiammaria.microapp30.Subsystem.NonExistentComponentException;
import com.giordanogiammaria.microapp30.component_fragment.ComponentFragment;
import com.giordanogiammaria.microapp30.component_fragment.ComponentFragmentCreator;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;

import java.util.ArrayList;
import java.util.HashMap;


public class Component {

    private String id;
    private ComponentType type;
    private ComponentFragment compFragment;
    private HashMap<String, GenericData> inputData;
    private HashMap<String, ArrayList<String>> inputSenders;
    private ArrayList<String> outputReceivers;

    public Component(String id, ComponentType type) throws NonExistentComponentException {
        this.id = id;
        this.type = type;
        compFragment = ComponentFragmentCreator.getComponentFragment(type);  // istanzia l'activity in base al tipo della componente
        inputData = new HashMap<>();
        inputSenders = new HashMap<>();
        outputReceivers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    ComponentFragment getFragment() {
        return compFragment;
    }

    public HashMap<String, DataType> getInputTypes() {
        return compFragment.getInputTypes();
    }

    public ArrayList<DataType> getOutputTypes() {
        return compFragment.getOutputTypes();
    }

    public HashMap<String, ArrayList<String>> getInputSenders() {
        return inputSenders;
    }

    public ArrayList<String> getOutputReceivers() {
        return outputReceivers;
    }

    public String toString() {
        return "Componente " + id + " di tipo " + type;
    }

    public void addInputSender(String compId, String dataName) {
        if (inputSenders.containsKey(compId)) {
            inputSenders.get(compId).add(dataName);
        } else {
            ArrayList<String> dataNames = new ArrayList<>();
            dataNames.add(dataName);
            inputSenders.put(compId, dataNames);
        }
    }

    public void addOutputReceiver(String compId) {
        outputReceivers.add(compId);
    }

    void putData(HashMap<DataType, GenericData> outputData, String sendId) {
        Log.d("inputSenders", inputSenders.toString());
        Log.d("sendId", sendId);
        for (String dataName : inputSenders.get(sendId)) {
            DataType dataType = compFragment.getInputTypes().get(dataName);
            GenericData data = outputData.get(dataType);
            if (dataType != null && data != null)
                inputData.put(dataName, data);
        }
    }

    void setInputs() throws MissingInputException {
        for (String dataName : compFragment.getInputTypes().keySet()) {
            Log.d("DATANAME:", dataName);
            Log.d("inputData:","" + inputData.containsKey(dataName));
            if (!inputData.containsKey(dataName))
                throw new MissingInputException(dataName);
        }
        compFragment.setInputsData(inputData);
    }

    HashMap<DataType, GenericData> getOutput() throws MissingOutputException {
        HashMap<DataType, GenericData> dataCollection = compFragment.getOutputsData();
        for (DataType dataType : compFragment.getOutputTypes())
            if (!dataCollection.containsKey(dataType))
                throw new MissingOutputException(dataType.toString());
        return dataCollection;
    }

}
