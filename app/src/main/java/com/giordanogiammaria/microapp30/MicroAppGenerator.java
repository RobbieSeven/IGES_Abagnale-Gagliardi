package com.giordanogiammaria.microapp30;

import com.giordanogiammaria.microapp30.component_fragment.ComponentFragment;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.sorting.ComponentSorting;
import com.giordanogiammaria.microapp30.parsing.DeployParser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roberto on 15/01/2018.
 */

public class MicroAppGenerator {

    private ArrayList<Component> components;
    private int currentIndex;

    public MicroAppGenerator(String filePath) {
        DeployParser parser = new DeployParser(filePath);
        components = ComponentSorting.sortComponents(parser.getComponents());
        currentIndex = 0;
    }

    public ComponentFragment prevCompFragment() {
        if (currentIndex > 0)
            currentIndex -= 1;
        return components.get(currentIndex).getFragment();
    }

    public ComponentFragment nextCompFragment() {
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

    public boolean hasNextComponent() {
        return currentIndex < components.size();
    }

}
