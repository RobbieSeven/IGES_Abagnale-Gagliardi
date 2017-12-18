package com.example.utente.microapp20.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import com.example.utente.microapp20.Constants;
import com.example.utente.microapp20.FileManagement;
import com.example.utente.microapp20.ListFileActivity;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.MAComponent;
import com.example.utente.microapp20.OptionsActivity;
import com.example.utente.microapp20.R;
import com.example.utente.microapp20.Utils;
import com.example.utente.microapp20.exceptions.InvalidComponentException;
import com.example.utente.microapp20.exceptions.NoMoreSpaceException;

import android.content.ComponentName;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings.Secure;

public class First extends MAActivity {
	private boolean runtime = false;

	private Button b_avvia;
	private String textPath;
	private String description;
	private String android_id;

	private boolean doubleBackToExitPressedOnce = false;

	public First() {

	}

	private void initialize() {

		String uri = "";
		if (application.getDeployPath() == null) {
			textPath = "";
			b_avvia.setEnabled(false);
			uri = "@drawable/microapp";
		}
		else{
			b_avvia.setEnabled(true);

		}
		TextView v = (TextView) findViewById(R.id.namefile);
		v.setText(textPath);
		TextView d = (TextView) findViewById(R.id.descfile);
		d.setText(description);

		ImageView image = (ImageView) findViewById(R.id.logoapp);
		int res = getResources().getIdentifier(uri, null, this.getPackageName());
		image.setImageResource(res);
		image.setPadding(0, 100, 0, 0);
		Constants.setScreenOffset(getBarHeight());

	}


	private int getBarHeight() {
		int StatusBarHeight = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			StatusBarHeight = getResources().getDimensionPixelSize(resourceId);
		}

		int TitleBarHeight = (int) Utils.convertDpToPixel(48, this);
		return StatusBarHeight + TitleBarHeight;
	}

	@Override
	protected void execute() {

	}

	@Override
	public void resume() {
		initialize();
		this.doubleBackToExitPressedOnce = false;
	}

	private void addShortcut(String filename, int resourceIcon, String name) {

		Intent shortcutIntent = new Intent(getApplicationContext(), First.class);

		shortcutIntent.setAction(Intent.ACTION_MAIN);

		Intent addIntent = new Intent();
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(getApplicationContext(), resourceIcon));
		addIntent.putExtra("filename", filename);


		addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		getApplicationContext().sendBroadcast(addIntent);

	}

	@SuppressWarnings("unused")
	private void delShortcut(String name) {
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

		// Shortcut name
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

		String appClass = this.getPackageName() + "." + this.getLocalClassName();
		ComponentName comp = new ComponentName(this.getPackageName(), appClass);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));

		sendBroadcast(shortcut);
	}

	@Override
	protected void initialize(Bundle savedInstanceState) {

	}

	@Override
	protected void prepare() {

	}

	@Override
	protected void prepareView(View v) {

	}

	public void onCreate(Bundle b) {
		super.onCreate(b);
		android_id = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);

		TextView tv = (TextView) findViewById(R.id.benvv);
		if (tv != null) {
			tv.setText("versione pezzottata da giamma");
		}

		if (OptionsActivity.isFirstRun(this)) {
			description = "Thank you in advance for using "
					 //this.getString(R.string.app_fullname)
					// + ".\n\n"
					+ "This is the first time you run the application. Please, select and set important information available in the Options menu.";

			TextView d = (TextView) findViewById(R.id.descfile);
			d.setTextColor(Color.YELLOW);
		} else
			description = "";

		final Intent intent = getIntent();
		String extra = intent.getStringExtra("filename");

		if (extra != null) {
			Utils.debug("Running " + extra);

			try {
				runtime = true;
				application.setDeployPath(extra, true);

				try {
					application.initComponents();
				} catch (InvalidComponentException e) {
					Utils.errorDialog(this, getString(R.string.notRunnable), e.getMessage());
				}


				MAComponent mycomponent = (MAComponent) application.getCurrentState();

				if (!mycomponent.isOutFilled()) {
					Toast.makeText(this, "Some outputs are missing " + mycomponent.getType() + " id:" + mycomponent.getId(),
							Toast.LENGTH_SHORT).show();

				}
				MAComponent comp = application.nextStep();

				if (comp == null) {
					setResult(Constants.ID_TERMINATED);
					//checkSettingUpdate();
					finish();
				}
				try {
					Intent runi = new Intent(this, comp.getActivityClass());

					Bundle bnd = new Bundle();
					bnd.putString("description", comp.getDescription());
					runi.putExtras(bnd);

					startActivityForResult(runi, 0);
				} catch (ClassNotFoundException e) {
					Utils.errorDialog(this, e.getMessage());
				}

			} catch (Exception e) {
				Utils.error(e.getMessage(), e);
			}
		}

		b_avvia = (Button) findViewById(R.id.avvia);
		Button bott = (Button) findViewById(R.id.sfoglia);
		final Intent i = new Intent(getApplicationContext(), ListFileActivity.class);


		bott.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivityForResult(i, 0);
			}

		});



		b_avvia.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					application.initComponents();
				} catch (InvalidComponentException | NoMoreSpaceException e) {
					Utils.errorDialog(First.this, getString(R.string.notRunnable), e.getMessage());

					if (runtime) {
						finish();
					}
					return;
				}
				next();
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, getString(R.string.about)).setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(0, 1, 1, getString(R.string.setting)).setIcon(android.R.drawable.ic_menu_manage);
		menu.add(0, 2, 2, getString(R.string.previewMap)).setIcon(android.R.drawable.ic_menu_mapmode);
		menu.add(0, 3, 3, getString(R.string.test)).setIcon(android.R.drawable.ic_menu_sort_by_size);
		menu.add(0, 4, 4, getString(R.string.update)).setIcon(android.R.drawable.ic_menu_rotate);
		menu.add(0, 5, 5, getString(R.string.restore)).setIcon(android.R.drawable.ic_menu_delete);
		menu.add(0, 6, 6, getString(R.string.legal)).setIcon(android.R.drawable.ic_menu_view);
		menu.add(0, 7, 7, getString(R.string.exit)).setIcon(android.R.drawable.ic_lock_power_off);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return true;
	}


	@Override
	public void initInputs() {
		return;
	}

	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			finish();
			return;
		}
		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please back once more to exit", Toast.LENGTH_SHORT).show();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;

			}
		}, 2000);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Constants.ID_EXIT) {
			application.reset();
			finish();
		}

		if (resultCode == Constants.ID_TERMINATED) {
			application.reset();


			if (runtime) {

				try {
					application.setDeployPath(null, false);
				} catch (Exception e) {
				}
				finish();
			}
		}

		if (runtime) {
			try {
				application.setDeployPath(null, false);
			} catch (Exception e) {
			}
			finish();
		}
	}

	@Override
	public void beforeNext() {
	}

	public void newDeploy(View v) throws ParserConfigurationException, SAXException, IOException, Exception {
		boolean flag = false;
		if (application.getDeployPath() != null) {
			String path = application.getDeployPath();
			try {
				File xmlUrl = new File(FileManagement.getLocalAppPath(), path);
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document doc = db.parse(xmlUrl);
				doc.getDocumentElement().normalize();
				Element root = doc.getDocumentElement();

				NodeList activator = root.getElementsByTagName("activator");

				GestureLibrary store = GestureLibraries.fromFile(FileManagement.getGestureDir());
				if (activator.item(0).getTextContent().contains("GestureActivator")) {
					boolean flag2 = false;
					if (store.load())
						flag = true;

					for (String name : store.getGestureEntries()) {

						for (Gesture gesture : store.getGestures(name)) {

							if ((path).equals(name)) {
								store.removeGesture(name, gesture);
								store.addGesture(name + "�^", gesture);
								store.save();
								flag2 = true;
								break;
							}

							if (flag2)
								break;

						}
					}

				}
			} catch (FactoryConfigurationError | Exception e) {
				e.printStackTrace();
			}
			if (!flag) {

				int resIcon = getResources().getIdentifier(application.getIconPath(), "drawable", getPackageName());
				addShortcut(FileManagement.getLocalAppPath() + path, resIcon, path.substring(0, path.lastIndexOf('.')));
			}

		}
	}



	protected void saveOntoDevice(Element root, File f) {
		FileOutputStream fileos;
		try {
			fileos = new FileOutputStream(f);
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(fileos, "UTF-8");
			serializer.startDocument(null, null);
			serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

			preorder(serializer, root);

			serializer.endDocument();
			serializer.flush();

		} catch (IllegalArgumentException | IOException | IllegalStateException e) {
			Utils.error(e.getMessage(), e);
		}
	}

	private void preorder(XmlSerializer serializer, Element root) {
		try {
			serializer.startTag(null, root.getNodeName());

			if (root.hasAttributes()) {
				NamedNodeMap attr = root.getAttributes();
				for (int i = 0; i < attr.getLength(); i++) {
					Attr at = (Attr) attr.item(i);
					serializer.attribute(null, at.getName(), at.getNodeValue());
				}
			}

			NodeList list = root.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				if (list.item(i) instanceof Element)
					preorder(serializer, (Element) list.item(i));
				else {
					Text txt = (Text) list.item(i);
					serializer.text(txt.getTextContent());
				}
			}

			serializer.endTag(null, root.getNodeName());
		} catch (IllegalArgumentException | IllegalStateException | IOException e) {
			Utils.error(e.getMessage(), e);
		}
	}
}
