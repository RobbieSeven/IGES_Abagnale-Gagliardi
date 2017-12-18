package com.example.utente.microapp20;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ListFileActivity extends ListActivity {

	ArrayList<String> nameFile;
	String path;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listfile);
		nameFile=new ArrayList<>();
		writeFiles();
		readFilesXML();

		setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameFile));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				try {
					MicroAppGenerator ap = new MicroAppGenerator();
					ap.setDeployPath(path+"/"+parent.getItemAtPosition(position), true);
					ap.initComponents();


				} catch (NullPointerException | SAXException | IOException | NoMoreSpaceException | InvalidComponentException e) {
					e.printStackTrace();
				}
				//finish();
			}
		});

	}

	public void onBackPressed(){
		finish();
	}

	private void writeFiles(){
		//create the folder microApp
		File folder = new File(Environment.getExternalStorageDirectory() +
				File.separator + "microApp");
		path=folder.getPath();
		if (!folder.exists()) {
			folder.mkdirs();
		}

		Element root=new Element("CONFIGURATION");
		Document doc=new Document();

		Element child1=new Element("BROWSER");
		child1.addContent("chrome");
		Element child2=new Element("BASE");
		child1.addContent("http:fut");
		Element child3=new Element("EMPLOYEE");
		child3.addContent(new Element("EMP_NAME").addContent("Anhorn, Irene"));

		root.addContent(child1);
		root.addContent(child2);
		root.addContent(child3);

		doc.setRootElement(root);

		XMLOutputter outter=new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		try {
			outter.output(doc, new FileWriter(folder.getCanonicalFile()+"/microApp.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFilesXML() {
		File f = new File(path);
		File[] files = f.listFiles();
		for (File inFile : files) {
			nameFile.add(inFile.getName());
		}

	}

}
