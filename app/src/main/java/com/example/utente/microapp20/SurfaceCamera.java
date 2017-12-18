package com.example.utente.microapp20;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class SurfaceCamera extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mHolder;
	private Camera mCamera;
	private boolean isFront; 
	private int dorientation = 90;
	
	public SurfaceCamera(Context context) {
		super(context);

		isFront = false;
		int id = getCameraInstance();
		setCameraDisplayOrientation((Activity)context, id, mCamera);
		
		//TODO: verificare orientation
		Parameters parameters=mCamera.getParameters();
		parameters.set("orientation", "portrait");
		// set other parameters ..
		mCamera.setParameters(parameters);			
		
		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
	}

	public Camera getCamera() {
		return mCamera;
	}
	
	public int getOrientation() {
		return this.dorientation;
	}
	public void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
	    Camera.CameraInfo info = new Camera.CameraInfo();
	    Camera.getCameraInfo(cameraId, info);
	    int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
	    int degrees = 0;
	    switch (rotation) {
	    case Surface.ROTATION_0:
	            degrees = 0;
	            break;
	    case Surface.ROTATION_90:
	            degrees = 90;
	            break;
	    case Surface.ROTATION_180:
	            degrees = 180;
	            break;
	    case Surface.ROTATION_270:
	            degrees = 270;
	            break;
	    }

	    
	    if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
	    	dorientation = (info.orientation + degrees) % 360;
	    	dorientation = (360 - dorientation) % 360; // compensate the mirror
	    } else { // back-facing
	    	dorientation = (info.orientation - degrees + 360) % 360;
	    }
	    camera.setDisplayOrientation(dorientation);
	}
	
	
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the
		// preview.
		try {
			if (mCamera != null) {
				mCamera.setPreviewDisplay(holder);
				startPreview();
			}	
		} catch (IOException e) {
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		stopPreview();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.

		if (mHolder.getSurface() == null) {
			return;
		}

		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}

		// make any resize, rotate or reformatting changes here

		// start preview with new settings
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();

		} catch (Exception e) {
		}
	}

	public int  getCameraInstance() {
		int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
		try {
			stopPreview();
		    if (isFront && Camera.getNumberOfCameras() > 1 && camId < Camera.getNumberOfCameras() - 1) {
		    	camId = Camera.CameraInfo.CAMERA_FACING_FRONT;
		    }
	        mCamera = Camera.open(camId);
			//mCamera = Camera.open(); // attempt to get a Camera instance

		} catch (Exception e) {
		}
		return camId;
	}

	public void startPreview() {
	    if (mCamera == null) {
	    	int id = getCameraInstance();
	    	setCameraDisplayOrientation((Activity)this.getContext(), id, mCamera);
	    } 
    	mCamera.startPreview();
	}	
	
	public void stopPreview() {
		if (mCamera != null) {
			try {
				mCamera.stopPreview();
			} catch (Exception e) {
				// ignore: tried to stop a non-existent preview
			}
			// mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}

	public void switchPreview()
	{
		isFront = !isFront;
		
		if (mHolder.getSurface() == null) {
			return;
		}

		try {
			mCamera.stopPreview();
		} catch (Exception e) {
		}		
		
		int id = getCameraInstance();
		setCameraDisplayOrientation((Activity)this.getContext(), id, mCamera);
		
		try {
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();

		} catch (Exception e) {
		}
	}
	
	public void setFlashMode(String mode) {
		if (mCamera != null) {
			Parameters p = mCamera.getParameters();
			p.setFlashMode(mode);
			mCamera.setParameters(p);
		}
	}	
}
