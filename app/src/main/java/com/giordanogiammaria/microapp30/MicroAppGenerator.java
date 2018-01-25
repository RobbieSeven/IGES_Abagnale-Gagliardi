package com.giordanogiammaria.microapp30;

import android.app.Fragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public class MicroAppGenerator {

    private ArrayList<Component> components;
    private DeployParser parser;
    private int currentIndex;
    private String filePath;

    public MicroAppGenerator(String filePath) {
        this.filePath = filePath;
        parser = new DeployParser(filePath);
        components = ComponentSorting.sortComponents(parser.getComponents());
        currentIndex = 0;
    }

    public Fragment prevCompFragment() {
        if (currentIndex > 0)
            currentIndex -= 1;
        return components.get(currentIndex).getFragment();
    }

    public Fragment nextCompFragment() {
        Component currentComp = components.get(currentIndex);
        HashMap<DataType, GenericData> dataCollection = currentComp.getOutput();
        for (String destId : currentComp.getOutputReceivers()) {
            Component destComp = null;
            for (Component comp : components)
                if (comp.getId().equals(destId))
                    destComp = comp;
            if (destComp != null)
                destComp.putData(dataCollection, currentComp.getId());
        }
        if (currentIndex < components.size() - 1) {
            currentIndex += 1;
            Component nextComp = components.get(currentIndex);
            nextComp.setInputs();
            return nextComp.getFragment();
        } else      // se non ci sono altre componenti, restituisce null
            return null;
    }

}
