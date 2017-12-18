package com.example.utente.microapp20;

import android.content.ActivityNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class MAComponent implements Cloneable {
	private String type, id, description;
	private String location;
	protected String comp_type;

	private Map<DataType, ArrayList<String>> outputs = new HashMap<DataType, ArrayList<String>>();
	private int counter = 0, outQty = 0;
	private Map<DataType, List<MAEntry<String, String>>> inputs;
	private List<String> bindings;
	private boolean firstComponentGraph;
	private boolean firstFalseComponentCondition;
	private boolean lastTrue;
	private boolean jumpComponent;

	public MAComponent(String id, String description) {
		super();
		this.id = id;
		this.description = description;
		location = getLocationQName();
		type = getCompType(id);
		inputs = new HashMap<DataType, List<MAEntry<String, String>>>();

		firstComponentGraph = true;
		firstFalseComponentCondition=false;
		jumpComponent = false;
	}

	public MAComponent(String id, String description, String scname, String sctype) {
		super();

		this.id = id;
		this.description = description;
		comp_type = sctype;
		location = getLocationQName();
		type = getCompType(id);
		inputs = new HashMap<DataType, List<MAEntry<String, String>>>();

		firstComponentGraph = true;
		firstFalseComponentCondition=false;
		lastTrue=false;
		jumpComponent = false;
	}

	public boolean getFirstComponentGraph() {
		return firstComponentGraph;
	}

	public boolean getFirstFalseComponentCondition(){ return firstFalseComponentCondition;}

	public boolean getLastTrue(){
		return lastTrue;
	}

	public void setLastTrue(boolean b){lastTrue=b;}

	public void setFirstComponentGraph(boolean s) {
		firstComponentGraph = s;
	}

	public boolean getJumpComponent() {
		return jumpComponent;
	}

	public void setJumpComponent(boolean s) {
		jumpComponent = s;
	}

	protected abstract String getLocationQName();

	protected abstract String getCompType(String id);

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Class<?> getActivityClass() throws ClassNotFoundException {
		try {
			return Class.forName(location);
		} catch (ActivityNotFoundException e) {
			throw new ClassNotFoundException();
		}
	}

	public void addOutput(DataType type, String comp, String binding) {
		ArrayList<String> ha = outputs.get(type);
		if (ha != null)
			ha.add(comp);
		else {
			ha = new ArrayList<String>();
			ha.add(comp);
			outputs.put(type, ha);
		}

		if (bindings == null)
			bindings = new LinkedList<String>();
		bindings.add(binding);
		outQty++;
	}

	public List<String> getBindings() {
		if(bindings == null) {
			bindings = new ArrayList<String>();
			bindings.add("");
		}
		return bindings;
	}

	public String getBindings(int pos) {
		if (bindings == null)
			return "";
		if (pos >= bindings.size())
			return "";
		return bindings.get(pos);
	}

	public Iterable<String> getOutActivityId(DataType key) {
		return outputs.get(key);
	}

	public void outputAdded() {
		counter++;
	}

	public boolean isOutFilled() {
		return counter >= outQty;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

	public void resetOutCounting() {
		counter = 0;
	}

	@Override
	public String toString() {
		return "MAComponent [type=" + type + ", id=" + id + "]";
	}

	public void addInput(DataType dataType, String comp, String binding) {
		List<MAEntry<String, String>> l = inputs.get(dataType);

		if (l != null)
			l.add(new MAEntry<String, String>(comp, binding));
		else {
			l = new LinkedList<MAEntry<String, String>>();
			l.add(new MAEntry<String, String>(comp, binding));
			inputs.put(dataType, l);
		}
	}

	public List<MAEntry<String, String>> getInputComponents(DataType key) {
		return inputs.get(key);
	}

	public void setInputCompomponents(DataType key, List<MAEntry<String, String>> inputComp) {
		inputs.put(key, inputComp);
	}
}
