package com.giordanogiammaria.microapp30;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public class Component {

    private String id;
    private ComponentType type;
    private ComponentFragment compFragment;
    private HashMap<String, GenericData> inputData;
    private HashMap<String, ArrayList<String>> inputSenders;
    private ArrayList<String> outputReceivers;

    public Component(String id, ComponentType type) {
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

    public ComponentFragment getFragment() {
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

    public void putData(HashMap<DataType, GenericData> outputData, String sendId) {
        for (String dataName : inputSenders.get(sendId))
            if (compFragment.getInputTypes().containsKey(dataName)) {
                DataType dataType = compFragment.getInputTypes().get(dataName);
                if (outputData.containsKey(dataType)) {
                    GenericData data = outputData.get(dataType);
                    inputData.put(dataName, data);
                }
            }
    }

    public void setInputs() {
        for (String dataName : compFragment.getInputTypes().keySet())
            if (!inputData.containsKey(dataName))
                return; // error
        compFragment.setInputsData(inputData);
    }

    public HashMap<DataType, GenericData> getOutput() {
        HashMap<DataType, GenericData> dataCollection = compFragment.getOutputsData();
        if (dataCollection != null)
            for (DataType dataType : compFragment.getOutputTypes())
                if (!dataCollection.containsKey(dataType))
                    return null; // error
        return compFragment.getOutputsData();
    }

}
