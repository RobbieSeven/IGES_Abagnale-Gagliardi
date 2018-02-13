package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.Subsystem.DataMismatchException;
import com.giordanogiammaria.microapp30.Subsystem.InputMismatchException;
import com.giordanogiammaria.microapp30.Subsystem.InputNotFoundException;
import com.giordanogiammaria.microapp30.Subsystem.InputNotRequiredException;
import com.giordanogiammaria.microapp30.Subsystem.MissingInputException;
import com.giordanogiammaria.microapp30.Subsystem.MissingOutputException;
import com.giordanogiammaria.microapp30.Subsystem.NonExistentComponentException;
import com.giordanogiammaria.microapp30.component_fragment.ComponentFragment;
import com.giordanogiammaria.microapp30.component_fragment.ComponentFragmentCreator;
import com.giordanogiammaria.microapp30.enumerators.ComponentType;
import com.giordanogiammaria.microapp30.enumerators.DataType;

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

    public Component(String id, ComponentType type) throws NonExistentComponentException {
        this.id = id;
        this.type = type;
        compFragment = ComponentFragmentCreator.getComponentFragment(type);  // istanzia il fragment in base al tipo della componente
        inputData = new HashMap<>();
        inputSenders = new HashMap<>();
        outputReceivers = new ArrayList<>();
    }

    public String toString() {
        return "Component " + id + " of type " + type;
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

    public void addInputSender(String compId, String dataName) throws InputNotRequiredException {
        if (getInputTypes().containsKey(dataName))
            throw new InputNotRequiredException(compId, id, dataName);
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

    public void putData(HashMap<DataType, GenericData> outputData, String sendId) throws DataMismatchException {
        /*Log.d("inputSenders", inputSenders.toString());
        Log.d("sendId", sendId);*/
        ArrayList<String> dataNames = inputSenders.get(sendId);                 // prendo i nomi dei dati che il mittente dovrebbe inviare
        if (dataNames == null)
            throw new InputNotRequiredException(sendId, id);
        for (String dataName : dataNames) {                                     // per ogni nome di dato che il mittente dovrebbe inviare
            DataType dataType = compFragment.getInputTypes().get(dataName);     // trovo il tipo di quel dato
            if (dataType == null)
                throw new InputMismatchException(sendId, id, dataName);
            GenericData data = outputData.get(dataType);                        // dall'insieme dei dati ricevuti, prendo il dato di quel tipo
            if (data == null)
                throw new InputNotFoundException(sendId, id, dataName, dataType.toString().toLowerCase());
            inputData.put(dataName, data);                                      // aggiungo il dato trovato all'insieme dei miei dati
        }
    }

    public void setInputs() throws MissingInputException {
        /*Log.d("DATANAME:", dataName);
        Log.d("inputData:","" + inputData.containsKey(dataName));*/
        for (String dataName : compFragment.getInputTypes().keySet())
            if (!inputData.containsKey(dataName))
                throw new MissingInputException(dataName, compFragment.getInputTypes().get(dataName).toString());
        compFragment.setInputsData(inputData);
    }

    public HashMap<DataType, GenericData> getOutput() throws MissingOutputException {
        HashMap<DataType, GenericData> dataCollection = compFragment.getOutputData();
        for (DataType dataType : compFragment.getOutputTypes())
            if (!dataCollection.containsKey(dataType))
                throw new MissingOutputException(dataType.toString());
        return dataCollection;
    }

}
