package com.example.utente.microapp20;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

public class Constants {

	public static String application = "it.unisa.microapp";

	// Maps
	public final static int RQS_GooglePlayServices = 1;
	public final static String api_key = "AIzaSyAeIQjkQyAyvtf44SieLQwzN-O2JEhPoVg";
	// Property
	public static boolean __DEBUG = true;

	// EditorConstants
	public final static int MATRIX_SIZE = 15;

	public static int SCREEN_OFFSET = 109;

	public static int WIDTH = 800;
	public static int HEIGTH = 480;
	public static int PHISICAL_HEIGTH = 480;

	public static int GRID_RATIO = 120;

	public static int SPACE = 20;
	public static int FSPACE = 20;
	public static int RADIUS = 10;
	public static int FRADIUS = 10;

	public static int MARGIN = 4;
	public static int FMARGIN = 4;
	public static int BUTTON = 150;
	public static int FBUTTON = 150;

	public static int BORDER_SIZE = 4;
	public static int FBORDER_SIZE = 4;

	public static int TEXT_SIZE = 32;
	public static int FTEXT_SIZE = 32;

	public static int MIN_GRID_WSIZE = 4;
	public static int MAX_GRID_WSIZE = 8;

	public static int GRID_WSIZE = MIN_GRID_WSIZE;
	public static String GRID_PERCENT = "100%";
	public static int GRID_HSIZE = 5;

	public static int SCREEN_SPLIT = 600;

	// Constants
	public static final int ID_TERMINATED = 4444;
	public static final int ID_EXIT = 4443;

	public final static int ID_RESULT_CONTACT = 2001;
	public final static int ID_SELECT_IMAGE = 2002;
	public final static int ID_SELECT_VIDEO = 2003;
	public final static int ID_SELECT_AUDIO = 2004;

	public final static int ID_SELECT_ICON = 9;

	public final static String extension = ".xml";
	public final static String cextension = ".xmlc";

	public final static String tryFilePrefix = "__";

	public final static String dateFormat = "yyyy-MM-dd";

	public final static String shortDateFormat = "yyMMdd_HHmm";

	// Error codes
	public final static int SENTENCE_CORRECT = 0;
	public final static int SENTENCE_EMPTY = 10001;
	public final static int SENTENCE_INPUT_MISSED = 10002;
	public final static int SENTENCE_INPUT_UNUSED = 10003;

	// Condition
	public static final int LIST_CONDITION = 500;
	public static final int CREATE_LIST_CONDITION = 501;
	public static final int RESTART_LIST_CONDITION = 502;
	public static final int MANDATORY_CONDITION = 2;
	public static final int OPTIONAL_CONDITION = 1;
	public static final int NO_CONDITION = 0;

	// Admob
	public final static String admobId = "a14d70b38c4cc74";

	// Options
	public final static String MY_PREFERENCES = "myPrefOptions";
	public final static String TEXT_NICKNAME_KEY = "textNickname";
	public final static String TEXT_EMAILPROVIDER_KEY = "textEmailProvider";
	public final static String TEXT_EMAILPASSWORD_KEY = "textEmailPassword";

	public final static String CB_EMAILPROVIDER_KEY = "cbEmailProvider";
	public final static String CB_DEBUG_KEY = "cbEDebug";
	public final static String CB_ADMOB_KEY = "cbAdmob";
	public final static String CB_SPEECH_KEY = "cbSpeech";
	public final static String CB_SMS_KEY = "cbSms";
	public final static String CB_WB_KEY = "cbWhiteBackground";

	public final static String WSRepository = "WSOperationsRep.xml";
	public final static String DRepository = "DomoticOperationsRep.xml";
	public final static String DDiscRepository = "domotic.xml";
	
	public final static String ScaffoldingRepository = "ScaffoldingListRep.xml";
	
	
	public final static LatLng unisaLocation = new LatLng(40.776430532200905, 14.788477420806885);

	// Scaffolding
	public static final int MAX_SCAFFOLDING = 3;

	public static void setSize(int w, int h, int m, int b) {
		Constants.WIDTH = w;
		Constants.HEIGTH = h;

		Constants.MARGIN = m;
		Constants.BUTTON = b;
	}

	public static void setScreenOffset(int off) {
		Constants.SCREEN_OFFSET = off;
	}

	public static void setPhisicalHeigth(int heigth) {
		Constants.PHISICAL_HEIGTH = heigth;
	}

	public static void initializeSize() {
		normalizeSize();
		FSPACE = Constants.SPACE;
		FBORDER_SIZE = Constants.BORDER_SIZE;
		FBUTTON = Constants.BUTTON;
		FRADIUS = Constants.RADIUS;
		FMARGIN = Constants.MARGIN;
	}

	public static void normalizeSize() {
		Constants.SCREEN_SPLIT = Constants.HEIGTH - (Constants.BUTTON + (Constants.MARGIN << 1));

		Constants.GRID_RATIO = (int) (Constants.WIDTH / Constants.GRID_WSIZE);
		Constants.GRID_HSIZE = (int) (Constants.HEIGTH / Constants.GRID_RATIO);

		Constants.SPACE = (int) (Constants.GRID_RATIO / 6);
		Constants.RADIUS = (int) (Constants.SPACE >> 1);

		Constants.BORDER_SIZE = (int) (Constants.SPACE / 6);

		switch (Constants.GRID_WSIZE) {
		case 4:
			Constants.TEXT_SIZE = 32;
			break;
		case 5:
			Constants.TEXT_SIZE = 28;
			break;
		case 6:
			Constants.TEXT_SIZE = 24;
			break;
		case 7:
			Constants.TEXT_SIZE = 20;
			break;
		case 8:
			Constants.TEXT_SIZE = 16;
			break;			
		}

	}

	public static boolean isZoomed() {
		return (Constants.GRID_WSIZE != Constants.MIN_GRID_WSIZE);
	}
	
	public static void zoomZero() {
		Constants.GRID_WSIZE = Constants.MIN_GRID_WSIZE;
		GRID_PERCENT = "100%";
	}

	public static boolean zoomIn() {
		Constants.GRID_WSIZE = Constants.GRID_WSIZE + 1;
		if (Constants.GRID_WSIZE > Constants.MAX_GRID_WSIZE) {
			Constants.GRID_WSIZE = Constants.MAX_GRID_WSIZE;
			return false;
		}
		GRID_PERCENT = String.format(Locale.getDefault(),"%3d", (100 * MIN_GRID_WSIZE / GRID_WSIZE)) + "%";
		return true;
	}

	public static boolean zoomOut() {
		Constants.GRID_WSIZE = Constants.GRID_WSIZE - 1;
		if (Constants.GRID_WSIZE < Constants.MIN_GRID_WSIZE) {
			Constants.GRID_WSIZE = Constants.MIN_GRID_WSIZE;
			return false;
		}
		GRID_PERCENT = String.format(Locale.getDefault(),"%3d", (100 * MIN_GRID_WSIZE / GRID_WSIZE)) + "%";
		return true;
	}

}
