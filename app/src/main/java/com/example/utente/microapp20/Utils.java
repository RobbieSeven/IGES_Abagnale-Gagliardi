package com.example.utente.microapp20;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

enum Type {
	VERBOSE, DEBUG, INFO, WARN, ERROR
}

public class Utils {

	public static String version() {

		StringBuffer buf = new StringBuffer();

		buf.append("Android Release: " + Build.VERSION.RELEASE + "\n");
		buf.append("Android Sdk: " + Build.VERSION.SDK_INT + "\n");
		buf.append("Device: " + Build.BRAND + " " + Build.DEVICE + "\n");

		return buf.toString();
	}

	public static void info(String message) {
		if (Constants.__DEBUG)
			log(Type.INFO, message);
	}	
	
	public static void verbose(String message) {
		if (Constants.__DEBUG)
			log(Type.VERBOSE, message);
	}

	public static void error(String message) {
		if (Constants.__DEBUG)
			log(Type.ERROR, message);
	}

	public static void error(String message, Exception e) {
		if (Constants.__DEBUG) {
			log(Type.ERROR, message);
			e.printStackTrace();
		}
	}

	public static void error(Exception e) {
		error(e.getMessage(), e);
	}

	public static void debug(String message) {
		if (Constants.__DEBUG)
			log(Type.DEBUG, message);
	}

	public static void errorDialog(Activity activity, String message) {
		errorDialog(activity, message, null);
	}

	public static void errorDialog(Activity activity, String message, String error) {
		dialog(activity, Type.ERROR, "Error", message, error, true, true);
	}

	private static void log(Type type, String message) {
		if (message == null)
			message = "unknown";
		switch (type) {
		case VERBOSE:
			Log.v(Constants.application, message);
			break;
		case DEBUG:
			Log.d(Constants.application, message);
			break;
		case INFO:
			Log.i(Constants.application, message);
			break;
		case WARN:
			Log.w(Constants.application, message);
			break;
		case ERROR:
			Log.e(Constants.application, message);
			break;
		}
	}

	public static void dialog(Activity activity, Type type, String title, String message, String error, boolean modal, boolean show) {
		String msg = message;
		if (error != null)
			msg = msg.concat(" : " + error);

		if (show) {
			if (modal) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
				alertDialog.setTitle(title);
				alertDialog.setMessage(msg).setCancelable(false)
						.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				alertDialog.create().show();
			} else {
				Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			}
		}

		log(type, msg);
	}

	public static void vibrate(Activity context) {
		// Get instance of Vibrator from current Context
		Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 100 milliseconds
		v.vibrate(100);
		// playAudio(context);
	}

	public static void playBeep(Activity context) {
		try {
			final MediaPlayer mMediaPlayer = MediaPlayer.create(context, R.raw.beep);
			mMediaPlayer.setLooping(false);
			mMediaPlayer.start();

			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					if (mMediaPlayer != null) {
						mMediaPlayer.release();
						// mMediaPlayer = null;
					}
				}
			});
		} catch (Exception e) {
		}
	}

	public static String splitCamelCase(String s) {
		return s.replace(" ", "").replaceAll(
				String.format(Locale.getDefault(),"%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}

	public static String[] splitter(String s) {
		int len = 3;

		s = splitCamelCase(s);

		ArrayList<String> ar = new ArrayList<String>();
		char[] sAr = s.toCharArray();
		int start = 0;
		// start with
		for (int i = len; i < sAr.length; i++) {
			if (sAr[i] == ' ') {
				ar.add(s.substring(start, i));
				start = i + 1;
				i += len;
			}
		}
		ar.add(s.substring(start));

		String[] strArray = new String[ar.size()];
		return ar.toArray(strArray);
	}

	public static boolean chkeckNetworkConnection(Application app) {
		boolean isNetAvailable = false;

		final ConnectivityManager connMgr = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr != null) {

			boolean mobileNetworkConnecetd = false;
			boolean wifiNetworkConnecetd = false;

			final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifi != null) {
				if (wifi.isAvailable())
					wifiNetworkConnecetd = wifi.isConnectedOrConnecting();
			}

			final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mobile != null) {
				if (mobile.isAvailable())
					mobileNetworkConnecetd = mobile.isConnectedOrConnecting();
			}

			isNetAvailable = wifiNetworkConnecetd || mobileNetworkConnecetd;
		}

		return isNetAvailable;
	}

	public static boolean chkeckNetworkConnection3g(Application app) {
		boolean isNetAvailable = false;

		final ConnectivityManager connMgr = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connMgr != null) {

			boolean mobileNetworkConnecetd = false;

			final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mobile != null) {
				if (mobile.isAvailable())
					mobileNetworkConnecetd = mobile.isConnectedOrConnecting();
			}

			isNetAvailable = mobileNetworkConnecetd;
		}

		return isNetAvailable;
	}

	public static String stripExtension(String fileName) {
		if (fileName == null)
			return null;
		int dotInd = fileName.lastIndexOf('.');
		return (dotInd > 0) ? fileName.substring(0, dotInd) : fileName;
	}

	public static String typeToString(String type) {
		return type.replace("_", " ").toLowerCase(Locale.getDefault());
	}

	// Tronca digits cifre decimali dopo la virgola
	public static double getRound(double x, int digits) {
		double powerOfTen = Math.pow(10, digits);
		return ((double) Math.round(x * powerOfTen) / powerOfTen);
	}

	public static String locationStringFromLocation(final Location location) {
		return "Location [" + Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + ", "
				+ Location.convert(location.getLongitude(), Location.FORMAT_DEGREES) + "]";
	}

	public static String addressStringFromAddress(final Address address) {
		String sadd = address.getFeatureName() != null ? "" + address.getFeatureName() : "-";
		sadd += address.getAdminArea() != null ? ", " + address.getAdminArea() : "";
		sadd += address.getSubAdminArea() != null ? ", " + address.getSubAdminArea() : "";
		sadd += address.getLocality() != null ? ", " + address.getLocality() : "";
		sadd += address.getPostalCode() != null ? ", " + address.getPostalCode() : "";
		sadd += address.getCountryName() != null ? ", " + address.getCountryName() : "";

		// sadd += "\n\n" + address.toString();
		return sadd;
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);

			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			return null;
		}
	}

	public static String getStaticMap(GoogleMap map, Location loc, int w, int h) {

		CameraPosition cp = map.getCameraPosition();

		String mtype = "roadmap";
		switch (map.getMapType()) {
		case com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE:
			mtype = "satellite";
			break;
		case com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID:
			mtype = "hybrid";
			break;
		case com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL:
			mtype = "roadmap";
			break;
		}

		return "http://maps.googleapis.com/maps/api/staticmap?center=" + cp.target.latitude + "," + cp.target.longitude + "&zoom="
				+ (int) (cp.zoom + 2) + "&size=" + w + "x" + h + "&maptype=" + mtype + "&markers=color:red%7Ccolor:red%7C"
				+ loc.getLatitude() + "," + loc.getLongitude() + "&sensor=false" + "&key=" + Constants.api_key;
	}

	public static void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	/*
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp A value in dp (density independent pixels) unit. Which we need
	 * to convert into pixels
	 * 
	 * @param context Context to get resources and device specific display
	 * metrics
	 * 
	 * @return A float value to represent px equivalent to dp depending on
	 * device density
	 */
	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static void extractAssetsCompilation(Context con) {
		File f_and = new File(FileManagement.getDefaultPath() + "/android.jar");
		if (!f_and.exists()) {
			Utils.verbose("copying the android.jar from asssets to the internal storage to make it available to the compiler");
			BufferedInputStream bis = null;
			OutputStream dexWriter = null;
			int BUF_SIZE = 8 * 1024;

			try {
				bis = new BufferedInputStream(con.getAssets().open("android.jar"));
				dexWriter = new BufferedOutputStream(new FileOutputStream(FileManagement.getDefaultPath() + "/android.jar"));
				byte[] buf = new byte[BUF_SIZE];
				int len;
				while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
					dexWriter.write(buf, 0, len);
				}
				dexWriter.close();
				bis.close();
			} catch (Exception e) {
				Utils.error("Error while copying from assets: " + e.getMessage(), e);

			}
		}

		Utils.extractAssets(con);
	}

	public static void extractAssets(Context con) {
		File f_ma_cl = new File(FileManagement.getDefaultPath(), "ma_classes.jar");
		if (!f_ma_cl.exists()) {
			// Copia ma_classes.jar
			Utils.verbose("copying the ma_classes.jar from assets to the internal storage to make it available to the compiler");

			BufferedInputStream bis = null;
			OutputStream dexWriter = null;
			int BUF_SIZE = 8 * 1024;

			try {
				bis = new BufferedInputStream(con.getAssets().open("ma_classes.jar"));
				dexWriter = new BufferedOutputStream(new FileOutputStream(FileManagement.getDefaultPath() + "/ma_classes.jar"));
				byte[] buf = new byte[BUF_SIZE];
				int len;
				while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
					dexWriter.write(buf, 0, len);
				}
				dexWriter.close();
				bis.close();
			} catch (Exception e) {
				Utils.error("Error while copying from assets: " + e.getMessage(), e);
			}
		}

		File f_ab = new File(FileManagement.getDefaultPath(), "activityBase.java");
		if (!f_ab.exists()) {
			Utils.verbose("copying the activityBase.java from assets to the internal storage to make it available to the compiler");

			BufferedInputStream bis = null;
			OutputStream dexWriter = null;
			int BUF_SIZE = 8 * 1024;

			try {
				bis = new BufferedInputStream(con.getAssets().open("activityBase.java"));
				dexWriter = new BufferedOutputStream(new FileOutputStream(FileManagement.getDefaultPath() + "activityBase.java"));
				byte[] buf = new byte[BUF_SIZE];
				int len;
				while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
					dexWriter.write(buf, 0, len);
				}
				dexWriter.close();
				bis.close();
			} catch (Exception e) {
				Utils.error("Error while copying from assets: " + e.getMessage(), e);
			}
		}

	}

	public static String linearizeData() {

		SimpleDateFormat format = new SimpleDateFormat(Constants.shortDateFormat, Locale.getDefault());
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}

	public static int versionToNumber(String version) {
		int value = 0;
		version = "1".concat(version);
		String digits = version.replace(".", "");
		try {
			value = Integer.parseInt(digits);
		} catch (NumberFormatException e) {
			value = 0;
		}

		return value;
	}
	
	public static String buildVersion(String base, String builds) {
		int build = 0;
		try {
			build = Integer.parseInt(builds);
		} catch(NumberFormatException e) { Utils.error("Build Number is not correct.", e);}
		
		return base+"."+ String.format(Locale.getDefault(),"%03d", build);
	}

}
