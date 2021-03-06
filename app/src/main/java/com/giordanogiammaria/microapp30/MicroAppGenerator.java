package com.giordanogiammaria.microapp30;

import android.app.Fragment;

import com.giordanogiammaria.microapp30.exceptions.DataMismatchException;
import com.giordanogiammaria.microapp30.exceptions.DeployFileException;
import com.giordanogiammaria.microapp30.exceptions.MissingComponentException;
import com.giordanogiammaria.microapp30.exceptions.MissingDataException;
import com.giordanogiammaria.microapp30.exceptions.NoNextComponentException;
import com.giordanogiammaria.microapp30.exceptions.NoPrevComponentException;
import com.giordanogiammaria.microapp30.exceptions.ParsingException;
import com.giordanogiammaria.microapp30.component_fragments.ComponentFragment;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.parsing.DeployParser;
import com.giordanogiammaria.microapp30.sorting.ComponentSorting;

import java.util.ArrayList;
import java.util.HashMap;

class MicroAppGenerator {

    private ArrayList<Component> components;
    private int currentIndex;

    MicroAppGenerator(String filePath) throws DeployFileException, ParsingException {
        DeployParser parser = new DeployParser(filePath);
        components = ComponentSorting.sortComponents(parser.getComponents());
        currentIndex = 0;
    }

    Fragment prevCompFragment() throws NoPrevComponentException {
        if (currentIndex > 0) {
            currentIndex -= 1;
            return components.get(currentIndex).getFragment();
        } else
            throw new NoPrevComponentException();
    }

    Fragment nextCompFragment() throws NoNextComponentException, MissingDataException, DataMismatchException, ParsingException {
        if (currentIndex < components.size() - 1) {
            Component currentComp = components.get(currentIndex);
            HashMap<DataType, GenericData> dataCollection = currentComp.getOutput();
            for (String destId : currentComp.getOutputReceivers()) {
                Component destComp = null;
                for (Component comp : components)
                    if (comp.getId().equals(destId))
                        destComp = comp;
                if (destComp != null)
                    destComp.putData(dataCollection, currentComp.getId());
                else
                    throw new MissingComponentException(currentComp.getId(), destId);
            }
            currentIndex += 1;
            Component nextComp = components.get(currentIndex);
            nextComp.setInputs();
            return nextComp.getFragment();
        } else
            throw new NoNextComponentException();
    }

    ComponentFragment getStartComponent() throws NoNextComponentException {
        Component component = components.get(0);
        if (component != null)
            return components.get(0).getFragment();
        else
            throw new NoNextComponentException();
    }

}
