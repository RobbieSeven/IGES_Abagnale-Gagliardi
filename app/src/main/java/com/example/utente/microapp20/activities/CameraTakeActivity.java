package com.example.utente.microapp20.activities;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.utente.microapp20.ImageData;
import com.example.utente.microapp20.MAActivity;
import com.example.utente.microapp20.R;
import com.example.utente.microapp20.SurfaceCamera;

public class CameraTakeActivity extends MAActivity {

	private SurfaceCamera mPreview;
	private Bitmap bp;

	@Override
	protected void initialize(Bundle savedInstanceState) {
		bp = null;
		mPreview = new SurfaceCamera(this);
	}

	@Override
	protected void prepare() {
	}

	@Override
	protected void prepareView(View v) {
		// Create our Preview view and set it as the content of our activity.
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);

		Button captureButton = (Button) findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// get an image from the camera
				Camera c = mPreview.getCamera();
				if (c != null)
					c.takePicture(null, null, mPicture);
			}
		});

		Button switchButton = (Button) findViewById(R.id.button_switch);
		switchButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mPreview.switchPreview();
			}
		});

		RadioButton radio1 = (RadioButton) findViewById(R.id.flash1);
		radio1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mPreview.setFlashMode(Parameters.FLASH_MODE_ON);
			}
		});

		RadioButton radio2 = (RadioButton) findViewById(R.id.noflash1);
		radio2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mPreview.setFlashMode(Parameters.FLASH_MODE_OFF);
			}
		});

		RadioButton radio3 = (RadioButton) findViewById(R.id.autoflash1);
		radio3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				mPreview.setFlashMode(Parameters.FLASH_MODE_AUTO);
			}
		});
		radio3.setChecked(true);
		mPreview.setFlashMode(Parameters.FLASH_MODE_AUTO);
	}

	@Override
	protected void execute() {

	}

	private PictureCallback mPicture = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				if (data != null) {
					int screenWidth = getResources().getDisplayMetrics().widthPixels;
					int screenHeight = getResources().getDisplayMetrics().heightPixels;

					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 2;

					bp = BitmapFactory.decodeByteArray(data, 0, (data != null) ? data.length : 0, options);

					if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
						// Notice that width and height are reversed
						Bitmap scaled = Bitmap.createScaledBitmap(bp, screenHeight, screenWidth, true);
						int w = scaled.getWidth();
						int h = scaled.getHeight();
						// Setting post rotate to 90
						Matrix mtx = new Matrix();
						mtx.postRotate(mPreview.getOrientation());
						// Rotating Bitmap
						bp = Bitmap.createBitmap(scaled, 0, 0, w, h, mtx, true);
					} else {// LANDSCAPE MODE
							// No need to reverse width and height
						Bitmap scaled = Bitmap.createScaledBitmap(bp, screenWidth, screenHeight, true);
						bp = scaled;
					}
				}

			} catch (Exception e) {
			}
			next();

		}
	};


	@Override
	public void pause() {
		mPreview.stopPreview();
		// super.onPause();
	}

	@Override
	protected void resume() {
		mPreview.startPreview();
	}

	@Override
	public void restart() {
		mPreview.startPreview();
	}

	public void initInputs() {
	}

	@Override
	public void beforeNext() {

		mPreview.stopPreview();

		if (bp != null) {
			ImageData im = new ImageData(mycomponent.getId(), bp);
			application.putData(mycomponent, im);
		}
	}
}
