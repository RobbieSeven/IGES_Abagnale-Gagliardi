package com.example.utente.microapp20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public abstract class MAActivity extends Activity {
	private String description = "";

	protected MAComponent mycomponent;
	protected MicroAppGenerator application;
	protected Menu contextmenu;

	private ViewGroup appView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.microapp);
		application = (MicroAppGenerator) getApplication();

		Bundle bu = getIntent().getExtras();
		Log.d("debug", "il bundle: " + bu.toString());
		if (bu != null) {
			setDescription(bu.getString("description"));
			// updateTitle(application.getDeployPath(), bu.getString("name"));

			// this.requestWindowFeature(Window.FEATURE_LEFT_ICON);
			// this.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
			// application.getIconId());
		}

		mycomponent = (MAComponent) application.getCurrentState();
		initialize(savedInstanceState);
		initInputs();
		prepare();
	}

	protected abstract void initialize(Bundle savedInstanceState);

	protected abstract void initInputs();

	protected abstract void prepare();

	protected abstract void prepareView(View v);

	protected abstract void execute();

	protected void start() {
	};

	protected void restart() {
	};

	protected void resume() {
	};

	protected void pause() {
	};

	protected void stop() {
	};

	protected void backPressed() {
	};

	protected void onCondition(boolean state) {
	};

	protected abstract void beforeNext();

	protected void destroy() {
	};

	// fine modifica

	@Override
	public void onStart() {
		super.onStart();
		start();
	}

	@Override
	public void onStop() {
		super.onStop();
		stop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// reVerificationCondition();
		restart();
	}

	private void updateTitle(String title, String subtitle) {
		if (title != null && !title.equals("")) {
			this.setTitle(title + (subtitle != null ? (" [" + subtitle + "]") : ""));
		} else
			this.setTitle(getString(R.string.app_name));
	}

	public void gotoPrevious(View v) {
		this.previous();
	}

	public void gotoNext(View v) {
		this.next();
	}

	protected String getPreviousLabel() {
		return getString(R.string.previous);
	}

	protected String getNextLabel() {
		return getString(R.string.next);
	}

	protected String getProgressTitle() {
		return null;
	}

	protected Drawable getPreviousDrawable() {
		return ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_back);
	}

	protected Drawable getNextDrawable() {
		return ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_forward);
	}

	protected Drawable getVoidDrawable() {
		return ContextCompat.getDrawable(getApplicationContext(), R.drawable.pixel);
	}

	protected boolean enablePrevious() {
		return true;
	}

	protected boolean enableNext() {
		return true;
	}

	protected boolean enableInfo() {
		return (onCreateDescription() != null && !onCreateDescription().equals(""));
	}

	protected boolean enableExit() {
		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		int order = Menu.FIRST;
		int gruppo = 0;

		menu.add(gruppo, 0, order++, getPreviousLabel()).setIcon(android.R.drawable.ic_media_previous);
		menu.add(gruppo, 1, order++, getNextLabel()).setIcon(android.R.drawable.ic_media_next);
		gruppo++;
		gruppo++;
		menu.add(gruppo, 3, order++, getString(R.string.info)).setIcon(android.R.drawable.ic_dialog_info);
		menu.add(gruppo, 4, order++, getString(R.string.exit)).setIcon(android.R.drawable.ic_lock_power_off);

		contextmenu = menu;
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.getItem(0).setEnabled(enablePrevious());
		menu.getItem(1).setEnabled(enableNext());
		menu.getItem(2).setEnabled(enableInfo());
		menu.getItem(3).setEnabled(enableExit());
		return true;
	}

	protected void setAppView(int resId) { // Vincenzo Savarese nuova modifica
		// da verificare se tenere o meno
		// (originale setAppView2)
		// setContentView(resId);------------------
		inflateButtons();
	}

	protected void inflateButtons() {
		try {
			Button b = (Button) findViewById(R.id.buttprev);
			if (b != null) {
				b.setText(getPreviousLabel());
				b.setEnabled(enablePrevious());
				b.setCompoundDrawablesWithIntrinsicBounds(getPreviousDrawable(), null, null, null);
			}
		} catch (ClassCastException e) {
		}
		try {
			Button b = (Button) findViewById(R.id.buttnext);
			if (b != null) {
				b.setText(getNextLabel());
				b.setEnabled(enableNext());
				b.setCompoundDrawablesWithIntrinsicBounds(null, null, getNextDrawable(), null);
			}
		} catch (ClassCastException e) {
		}
	}

	public void setPreviousMenuItem(boolean state) {
		contextmenu.getItem(0).setEnabled(state);
	}

	public void setNextMenuItem(boolean state) {
		contextmenu.getItem(1).setEnabled(state);
	}

	public void setInfoMenuItem(boolean state) {
		contextmenu.getItem(2).setEnabled(state);
	}

	public void setExitMenuItem(boolean state) {
		contextmenu.getItem(3).setEnabled(state);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			this.previous();
			break;
		case 1:
			this.next();
			break;
		case 3:
			this.getInfo();
			break;
		case 4:
			this.exit();
			break;
		}
		return true;
	}

	public void exit() {
		setResult(Constants.ID_EXIT);
		finish();
	}

	public void getInfo() {
		new AlertDialog.Builder(this).setTitle("Info...").setMessage(this.onCreateDescription())
				.setNeutralButton("Close", null).setIcon(android.R.drawable.ic_dialog_info).show();
	}

	public void next() {
		beforeNext();
		if (!mycomponent.isOutFilled()) {
			Toast.makeText(this, "Some outputs are missing " + mycomponent.getType() + " id:" + mycomponent.getId(),
					Toast.LENGTH_SHORT).show();
			return;
		}
		MAComponent comp = application.nextStep();
		if (comp == null) {
			setResult(Constants.ID_TERMINATED);
			finish();
			return;
		}

		try {
			Intent i = new Intent(this, comp.getActivityClass());

			Bundle b = new Bundle();
			b.putString("description", comp.getDescription());
			b.putString("name", comp.getType());
			i.putExtras(b);

			startActivityForResult(i, 0);
		} catch (ClassNotFoundException e) {
		}
	}

	public void previous() {
		application.prevStep();
		finish();
	}

	public void setDescription(String s) {
		description = s;
	}

	@Override
	public String onCreateDescription() {
		return description;
	}

	@Override
	public void onBackPressed() {
		backPressed();
		previous();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Constants.ID_EXIT || resultCode == Constants.ID_TERMINATED) {
			setResult(resultCode);
			finish();
			return;
		}
	}

}
