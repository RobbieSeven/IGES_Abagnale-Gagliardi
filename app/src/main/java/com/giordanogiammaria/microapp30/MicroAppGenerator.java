package com.giordanogiammaria.microapp30;

import android.app.Fragment;

import com.giordanogiammaria.microapp30.Subsystem.DataMismatchException;
import com.giordanogiammaria.microapp30.Subsystem.MissingComponentException;
import com.giordanogiammaria.microapp30.Subsystem.MissingDataException;
import com.giordanogiammaria.microapp30.Subsystem.NoNextComponentException;
import com.giordanogiammaria.microapp30.Subsystem.NoPrevComponentException;
import com.giordanogiammaria.microapp30.Subsystem.ParsingException;
import com.giordanogiammaria.microapp30.component_fragment.ComponentFragment;
import com.giordanogiammaria.microapp30.enumerators.DataType;
import com.giordanogiammaria.microapp30.parsing.DeployParser;
import com.giordanogiammaria.microapp30.sorting.ComponentSorting;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class MicroAppGenerator {

    private ArrayList<Component> components;
    private int currentIndex;

    public MicroAppGenerator(String filePath) throws FileNotFoundException, ParsingException {
        DeployParser parser = new DeployParser(filePath);
        components = ComponentSorting.sortComponents(parser.getComponents());
        currentIndex = 0;
    }

    public Fragment prevCompFragment() throws NoPrevComponentException {
        if (currentIndex > 0) {
            currentIndex -= 1;
            return components.get(currentIndex).getFragment();
        } else
            throw new NoPrevComponentException();
    }

    public Fragment nextCompFragment() throws NoNextComponentException, MissingDataException, DataMismatchException, ParsingException {
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

    public ComponentFragment getStartComponent() throws NoNextComponentException {
        Component component = components.get(0);
        if (component != null)
            return components.get(0).getFragment();
        else
            throw new NoNextComponentException();
    }

}
