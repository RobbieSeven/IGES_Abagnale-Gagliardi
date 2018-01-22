package com.giordanogiammaria.microapp30;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public class Component {

    private String id;
    private ComponentFragment componentFragment;
    private HashMap<String, GenericData> inputData;
    private HashMap<String, ArrayList<String>> inputSenders;
    private ArrayList<String> outputReceivers;

    public Component(String id, ComponentType type) throws ClassNotFoundException {
        this.id = id;
        componentFragment = ComponentCreator.getComponentActivity(type);  // istanzia il fragment in base al tipo della componente
        inputData = new HashMap<>();
        inputSenders = new HashMap<>();
        outputReceivers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public ComponentFragment getFragment() {
        return componentFragment;
    }

    public HashMap<String, DataType> getInputTypes() {
        return componentFragment.getInputTypes();
    }

    public ArrayList<DataType> getOutputTypes() {
        return componentFragment.getOutputTypes();
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
            if (componentFragment.getInputTypes().containsKey(dataName)) {
                DataType dataType = componentFragment.getInputTypes().get(dataName);
                if (outputData.containsKey(dataType)) {
                    GenericData data = outputData.get(dataType);
                    inputData.put(sendId, data);
                }
            }
    }

    public void setInputs() {
        for (String dataName : componentFragment.getInputTypes().keySet())
            if (!inputData.containsKey(dataName))
                return; // error
        componentFragment.setInputsData(inputData);
    }

    public HashMap<DataType, GenericData> getOutput() {
        HashMap<DataType, GenericData> dataCollection = componentFragment.getOutputsData();
        if (dataCollection != null)
            for (DataType dataType : componentFragment.getOutputTypes())
                if (!dataCollection.containsKey(dataType))
                    return null; // error
        return componentFragment.getOutputsData();
    }

}
