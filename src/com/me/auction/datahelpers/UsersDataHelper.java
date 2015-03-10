package com.me.auction.datahelpers;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.auction.model.User;
import com.me.auction.utils.Constants;

public class UsersDataHelper extends SQLiteOpenHelper {

	Context mContext;

	public UsersDataHelper(Context context) {
		super(context, Constants.DB_NAME, null, 1);
		this.mContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUsersTable(db);
	}

	private void createUsersTable(SQLiteDatabase db) {
		String tbl = "create table " + Constants.TABLE_USERS + " ("
				+ Constants.KEY_ID + " integer primary key autoincrement,"
				+ Constants.KEY_FIRST_NAME + " text not null, "
				+ Constants.KEY_LAST_NAME + " text not null, "
				+ Constants.KEY_DISPLAY_NAME + " text not null, "
				+ Constants.KEY_PASSWORD + " integer not null)";
		try {
			db.execSQL(tbl);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public long createUser(User user) {
		long id = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_FIRST_NAME, user.getfName());
		values.put(Constants.KEY_LAST_NAME, user.getlName());
		values.put(Constants.KEY_DISPLAY_NAME, user.getDisplayName());
		values.put(Constants.KEY_PASSWORD, user.getPassword());

		try {
			id = db.insert(Constants.TABLE_USERS, null, values);
			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}

	public ArrayList<User> getAllUsers() {

		ArrayList<User> usersList = null;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Constants.TABLE_USERS, null, null, null, null,
				null, null);
		if (cursor.getCount() > 0) {
			usersList = new ArrayList<User>();

			cursor.moveToFirst();
			do {
				User user = new User();
				user.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				user.setfName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_FIRST_NAME)));
				user.setlName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_LAST_NAME)));
				user.setDisplayName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_DISPLAY_NAME)));
				user.setPassword(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_PASSWORD)));
				usersList.add(user);
			} while (cursor.moveToNext());

		}
		return usersList;
	}

	public int getUsersCount() {

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Constants.TABLE_USERS, null, null, null, null,
				null, null);
		return cursor.getCount();
	}

	public User getUserWithId(Long userId) {
		User user = null;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_USERS
				+ " where " + Constants.KEY_ID + " = ?",
				new String[] { userId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				user = new User();
				user.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				user.setfName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_FIRST_NAME)));
				user.setlName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_LAST_NAME)));
				user.setDisplayName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_DISPLAY_NAME)));
				user.setPassword(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_PASSWORD)));
			} while (cursor.moveToNext());
		}
		return user;
	}

	public User getUserForUserNameAndPassword(String userName, String password) {
		User user = new User();
		SQLiteDatabase db = getWritableDatabase();
		String queryStr = "select * from " + Constants.TABLE_USERS + " where "
				+ Constants.KEY_DISPLAY_NAME + "=\"" + userName + "\" AND "
				+ Constants.KEY_PASSWORD + "=\"" + password + "\"";
		Cursor cursor = db.rawQuery(queryStr, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				user.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				user.setfName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_FIRST_NAME)));
				user.setlName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_LAST_NAME)));
				user.setDisplayName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_DISPLAY_NAME)));
				user.setPassword(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_PASSWORD)));
			} while (cursor.moveToNext());
		}
		return user;
	}

	public int updateUser(User user) {
		int affectedRows = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_FIRST_NAME, user.getfName());
		values.put(Constants.KEY_LAST_NAME, user.getlName());
		values.put(Constants.KEY_DISPLAY_NAME, user.getDisplayName());
		values.put(Constants.KEY_PASSWORD, user.getPassword());

		try {
			affectedRows = db.update(Constants.TABLE_USERS, values,
					Constants.KEY_ID + "=" + user.getId(), null);

			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return affectedRows;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
