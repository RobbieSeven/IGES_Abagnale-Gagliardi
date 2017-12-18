package com.example.utente.microapp20;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.utente.microapp20.components.BlankComponent;
import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

import org.jgrapht.DirectedGraph;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;

public class MicroAppGenerator extends Application  {
	private String deployPath;
	private String iconPath;
	private int iconId;
	private String description;
	private Stack<MAComponent> executing;
	private Stack<MAComponent> executed = new Stack<MAComponent>();
	private static MAComponent defaultState;
	private MAComponent currentState = defaultState;
	private MAComponent startComponent;
	private DeployParser parser = null;
	private Map<String, DataCollection> dataTable = new HashMap<String, DataCollection>();
	private Map<String, DataCollection> outputTable = new HashMap<String, DataCollection>();
	private ArrayList<MAComponent> componentList;
	private Set<DirEdge> edges;
	ArrayList<ArrayList<MAComponent>> listGraph;

	static {
		defaultState = new BlankComponent("0");
	}

	public MicroAppGenerator() {
		super();
		try {
			parser = new DeployParser(new ConcreteComponentCreator(), this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected void attachBaseContext(Context base){
		super.attachBaseContext(base);
	}

	public void reset() {
		deployPath = null;
		iconPath = null;
		iconId = 0;
		description = null;
		executing = null;
		currentState = defaultState;
		executed = new Stack<MAComponent>();
		dataTable = new HashMap<String, DataCollection>();
		outputTable = new HashMap<String, DataCollection>();
	}

	public String getDescription() {
		return description;
	}

	public String getIconPath() {
		return iconPath;
	}

	
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
		this.iconId = getResources().getIdentifier(iconPath, "drawable", this.getPackageName());
	}

	public int getIconId() {
		return iconId;
	}
	
	public void setStartComponent(MAComponent startComponent) {
		this.startComponent = startComponent;
	}

	public MAComponent getStartComponent() {
		return startComponent;
	}

	public void initComponents(DirectedGraph<MAComponent, DirEdge> compGraph, MAComponent startComp) {
		listGraph = new ArrayList<ArrayList<MAComponent>>();
		edges = compGraph.edgeSet();
		for(DirEdge edge: edges)
		{
			MAComponent src = (MAComponent)edge.getSource();
			MAComponent dst = (MAComponent)edge.getTarget();
			dst.setFirstComponentGraph(false);
			
			if(src.getFirstComponentGraph()){
				listGraph.add(new ArrayList<MAComponent>());
				listGraph.get(listGraph.size()-1).add(src);
			}
		}
		createListSubGraph();
		
		DepthFirstOrder<MAComponent, DirEdge> topord = new DepthFirstOrder<MAComponent, DirEdge>(compGraph, startComp);
		componentList = topord.reversePost();
			
		String sequence = "";
		for(MAComponent clist: componentList)
		{
			sequence = sequence.concat( clist.getType()+ " -> "); 
		}
				
		toStack(componentList.toArray(new MAComponent[componentList.size()]));
	}
		
	public void initComponents() throws InvalidComponentException,NoMoreSpaceException {

		DirectedGraph<MAComponent, DirEdge> compGraph = parser.createGraph();

		Log.d("INFO" , "sono qui" + compGraph);
		listGraph = new ArrayList<ArrayList<MAComponent>>();
		edges = compGraph.edgeSet();
		for(DirEdge edge: edges)
		{

			MAComponent src = (MAComponent)edge.getSource();
			MAComponent dst = (MAComponent)edge.getTarget();

			dst.setFirstComponentGraph(false);
			
			if(src.getFirstComponentGraph()){
				listGraph.add(new ArrayList<MAComponent>());
				listGraph.get(listGraph.size()-1).add(src);
			}
		}
		createListSubGraph();

		DepthFirstOrder<MAComponent, DirEdge> topord = new DepthFirstOrder<MAComponent, DirEdge>(compGraph, parser.getStartComp());
		componentList = topord.reversePost();

		// log
		String sequence = "";
		MAComponent compPrev=null;
		for(MAComponent clist: componentList)
		{
			sequence = sequence.concat( clist.getType()+ " -> ");
			if (compPrev != null){
				if (clist.getFirstFalseComponentCondition()){
					compPrev.setLastTrue(true);
				}

			}
			compPrev=clist;
		}

		toStack(componentList.toArray(new MAComponent[componentList.size()]));
	}

	private void createListSubGraph(){
		boolean change=true;
		while(change){
			change=false;
			for(DirEdge edge: edges){
				MAComponent src1 = (MAComponent)edge.getSource();
				MAComponent dst1 = (MAComponent)edge.getTarget();
				for(int j=0; j <listGraph.size() ;j++){
					ArrayList<MAComponent> nList = listGraph.get(j);
					if(nList.get(nList.size()-1).equals(src1)){
						nList.add(dst1);
						change=true;
					}
				}
			}
		}

		for(int i=0; i<listGraph.size()-1 ;i++){
			ArrayList<MAComponent> prevList = listGraph.get(i);
			for(int j=i+1; j<listGraph.size() ;j++){
				ArrayList<MAComponent> nextList = listGraph.get(j);
				for(int k=0; k < prevList.size() ;k++){
					MAComponent prevListComp = prevList.get(k);
					for(int l=0; l < nextList.size() ;l++){
						MAComponent nextListComp = nextList.get(l);
						if(prevListComp.equals(nextListComp)){
							MAComponent fComp = nextList.get(0);
							fComp.setFirstComponentGraph(false);

						}
					}
				}
			}
		}
	}
	
	public ArrayList<ArrayList<MAComponent>> getListGraph(){
		return listGraph;
	}

	public void setListGraph(ArrayList<ArrayList<MAComponent>> listGraph){
		this.listGraph = listGraph;
	}

	public Set<DirEdge> getEdges() {
		return edges;
	}

	public void setEdges(Set<DirEdge> edges) {
		this.edges = edges;
	}	
	
	public ArrayList<MAComponent> getComponentList() {
		return componentList;
	}

	public void toStack(MAComponent[] components) {
		executing = new Stack<MAComponent>();
		for (int i = components.length - 1; i >= 0; i--) {
			executing.push(components[i]);
		}
	}

	public void setDeployPath(String path, boolean full) throws NullPointerException, IOException, SAXException {
		if (path != null) {
			if (full) {
					parser.setDeployFile(new File(path));
					path = new File(path).getName();
			} else {
				parser.setDeployFile(new File(FileManagement.getLocalAppPath() + path));
			}

			iconPath = parser.getIcon();
			Log.d("INFO", "iconpath:" + iconPath);
			description = parser.getDescription();
		}
		this.deployPath = path;
	}

	public String getDeployPath() {
		return deployPath;
	}

	public DeployParser getParser() {
		return parser;
	}
	
	public MAComponent nextStep() {
		executed.push(currentState);
		currentState.resetOutCounting();
		if (executing.isEmpty())
			return null;
		else
			currentState = executing.pop();
		return currentState;
	}

	public MAComponent prevStep() {
		try{
			executing.push(currentState);
			if (executed.isEmpty())
				currentState = defaultState;
			currentState = executed.pop();
			return currentState;
		}
		catch (Exception e){
			
		}
		return null;
		
	}

	public void putData(MAComponent sender, GenericData<?> data) {
		DataType type = data.getDataType();
		String sid = sender.getId();
		DataCollection dataColl = outputTable.get(sid);
		GenericData<?> dt;
		if (dataColl != null) {
			if(dataColl.getData(type) != null)
			{	
				dt = dataColl.getData(type).iterator().next();
				if (dt != null) {
					dt.copyData(data);
					sender.outputAdded();
					return;
				}
			}
		}

		Iterable<String> it = sender.getOutActivityId(data.getDataType());
		if (it == null)
		{	
			return;
		}	
		for (String actID : it) {
			dataColl = dataTable.get(actID);
			if (dataColl == null)
				dataColl = new DataCollection();
			dataTable.put(actID, dataColl);
			dataColl.addData(data);

			dataColl = outputTable.get(sid);
			if (dataColl == null) {
				dataColl = new DataCollection();
				outputTable.put(sid, dataColl);
			}

			dataColl.addData(data);
			sender.outputAdded();
		}
	}
	
	public Iterable<GenericData<?>> getData(String actID, DataType type) {
		DataCollection coll = dataTable.get(actID);
		if (coll == null)
			return null;
		Iterable<GenericData<?>> i = coll.getData(type);
		return i;
	}

	public MAComponent getCurrentState() {
		return currentState;
	}

}
