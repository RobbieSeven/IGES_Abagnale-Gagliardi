package com.example.utente.microapp20;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.FileOutputStream;
//import java.io.OutputStream;

public class FileManagement {
	private static String baseDir = "/MicroApps";
	private static String cameraDir = baseDir + "/Camera";
	private static String repositoryDir = baseDir + "/Repository";
	private static String screenshotDir = baseDir + "/Screenshots";
	private static String filesDir = baseDir + "/Files";
	private static String gestureDir = baseDir + "/Gestures";
	private static String addedComponent = baseDir + "/Components";
	private static String compileSrc = baseDir + "/Sources";
	private static String domoticArea = baseDir + "/Domotic";

	public FileManagement() {
		checkDirectory();
	}

	public static String getDefaultPath() {
		return Environment.getExternalStorageDirectory().toString() + baseDir + "/";
	}

	public static String getLocalAppPath() {
		return Environment.getExternalStorageDirectory().toString() + baseDir + "/LocalApps/";
	}

	public static String getCameraPath() {
		return Environment.getExternalStorageDirectory().toString() + cameraDir + "/";
	}

	public static String getRepositoryPath() {
		return Environment.getExternalStorageDirectory().toString() + repositoryDir + "/";
	}

	public static String getScreenshotPath() {
		return Environment.getExternalStorageDirectory().toString() + screenshotDir + "/";
	}

	public static String getFilesPath() {
		return Environment.getExternalStorageDirectory().toString() + filesDir + "/";
	}

	public static String getGestureDir() {
		return Environment.getExternalStorageDirectory().toString() + gestureDir + "/";
	}

	public static String getAddedComponent() {
		return Environment.getExternalStorageDirectory().toString() + addedComponent + "/";
	}

	public static String getCompileSrc() {
		return Environment.getExternalStorageDirectory().toString() + compileSrc + "/";
	}

	public static String getDomoticArea() {
		return Environment.getExternalStorageDirectory().toString() + domoticArea + "/";
	}	
	
	
	public static void checkDirectory() {
		File f = new File(getDefaultPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getCameraPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getRepositoryPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getFilesPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getGestureDir());
		if (!f.exists()) {
			try {
				f.createNewFile();
				// f.mkdir();
			} catch (IOException e) {
			}
		}

		f = new File(getScreenshotPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getLocalAppPath());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getAddedComponent());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getCompileSrc());
		if (!f.exists()) {
			f.mkdir();
		}

		f = new File(getDomoticArea());
		if (!f.exists()) {
			f.mkdir();
		}		
		
	}

	public static File[] getListOnLocalDirectory() {
		File f = new File(getLocalAppPath());
		return f.listFiles(new PamFilter());
	}

	public String[] getListOnLocalDiretory() {
		File f = new File(getLocalAppPath());
		String[] ret = f.list(new PamFilter());

		return ret;
	}

	public static void remove() {
		deleteRecursive(new File(Environment.getExternalStorageDirectory().toString() + baseDir));
	}

	private static void deleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				deleteRecursive(child);

		fileOrDirectory.delete();
	}
}
