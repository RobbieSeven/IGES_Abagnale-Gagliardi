package com.example.utente.microapp20.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;

public class BlankActivity extends MAActivity {
	TextView label;
	
	@Override
	protected void initialize(Bundle savedInstanceState) {
	}

	@Override
	protected void prepare() {
	}

	@Override
	protected void prepareView(View v) {
		label = (TextView) this.findViewById(R.id.tv);
		if(label != null) {
			label.setText(mycomponent.getType());
		}
	}	
	
	@Override
	protected void execute() {
	}	
	
	@Override
	protected void restart() {
	}

	@Override
	protected void resume() {
	}

	@Override
	protected void pause() {
	}

	@Override
	protected void onCondition(boolean state) {
	}

	@Override
	protected void initInputs() {
	}

	@Override
	protected void beforeNext() {
	}

	@Override
	protected void destroy() {
	}

}
