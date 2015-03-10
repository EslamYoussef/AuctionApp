package com.me.auction.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.me.auction.R;
import com.me.auction.model.User;
import com.me.auction.presenters.UserPresenter;

public class Utils {

	public static ArrayList<Integer> getWelcomeImagesResourceList() {
		ArrayList<Integer> welcomeImages = new ArrayList<Integer>();
//		welcomeImages.add(R.drawable.auc_2);
//		welcomeImages.add(R.drawable.auc_1);
		welcomeImages.add(R.drawable.auc_3);
		welcomeImages.add(R.drawable.auc_4);
		return welcomeImages;
	}

	public static Long getSavedUserId(Context context) {
		SharedPreferences prefs = context.getSharedPreferences("credentials",
				Context.MODE_PRIVATE);
		return prefs.getLong(Constants.KEY_USER_ID, 0);

	}

	public static void saveUserId(Context context, Long userId) {
		SharedPreferences prefs = context.getSharedPreferences("credentials",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(Constants.KEY_USER_ID, userId);
		editor.commit();

	}

	public static User getCurrentUser(Context context) {

		UserPresenter presenter = new UserPresenter(context);
		return presenter.getUserWithId(getSavedUserId(context));
	}

	public static String getStringMonth(Context context, Calendar calender) {

		Integer monthIndex = calender.get(Calendar.MONTH);
		return getLocaleMonthName(context, monthIndex + 1);
	}

	public static String getDateStringfromDate(Context context, long dateInMilis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateInMilis);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String strMonth = Utils.getStringMonth(context, calendar);

		return new StringBuilder()
				// Month is 0 based, just add 1
				.append(strMonth).append(" ").append(day).append(",")
				.append(year).toString();
	}

	private static String getLocaleMonthName(Context context, Integer monthIndex) {
		Resources res = context.getResources();

		switch (monthIndex) {
		case 1:
			return res.getString(R.string.str_jan);

		case 2:
			return res.getString(R.string.str_feb);

		case 3:
			return res.getString(R.string.str_mar);

		case 4:
			return res.getString(R.string.str_april);

		case 5:
			return res.getString(R.string.str_may);

		case 6:
			return res.getString(R.string.str_june);

		case 7:
			return res.getString(R.string.str_july);

		case 8:
			return res.getString(R.string.str_aug);

		case 9:
			return res.getString(R.string.str_sep);

		case 10:
			return res.getString(R.string.str_oct);

		case 11:
			return res.getString(R.string.str_nov);

		case 12:
			return res.getString(R.string.str_dec);

		default:
			return "";
		}
	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
