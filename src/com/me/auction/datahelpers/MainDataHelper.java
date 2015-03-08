package com.me.auction.datahelpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.auction.utils.Constants;

public class MainDataHelper extends SQLiteOpenHelper {

	Context mContext;
	boolean isTablesCreated = false;

	public MainDataHelper(Context context) {
		super(context, Constants.DB_NAME, null, 1);
		this.mContext = context;

	}

	public void createTables() {
		this.getWritableDatabase();
		if (isTablesCreated)
			fillInitialData();

	}

	private void fillInitialData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUsersTable(db);
		createAuctionsTable(db);
		createBidsTable(db);
		isTablesCreated = true;
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

	private void createAuctionsTable(SQLiteDatabase db) {
		String tbl = "create table " + Constants.TABLE_ITEMS + " ("
				+ Constants.KEY_ID + " integer primary key autoincrement,"
				+ Constants.KEY_ITEM_TITLE + " text not null, "
				+ Constants.KEY_OWNER_USER_NAME + " text not null, "
				+ Constants.KEY_ITEM_DESCRIPTION + " text, "
				+ Constants.KEY_START_DATE + " integer, "
				+ Constants.KEY_DURATION + " integer, "
				+ Constants.KEY_WINNER__USER_ID + " integer, "
				+ Constants.KEY_IS_CLOSED + " integer, "
				+ Constants.KEY_OWNER_ID + " integer not null)";
		try {
			db.execSQL(tbl);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void createBidsTable(SQLiteDatabase db) {
		String tbl = "create table " + Constants.TABLE_BIDS + " ("
				+ Constants.KEY_ID + " integer primary key autoincrement,"
				+ Constants.KEY_BID_DATE + " integer not null, "
				+ Constants.KEY_USER_NAME + " text not null, "
				+ Constants.KEY_USER_ID + " integer, "
				+ Constants.KEY_AUCTION_ID + " integer not null, "
				+ Constants.KEY_IS_WON + " integer, " + Constants.KEY_BID_VALUE
				+ " integer not null)";
		try {
			db.execSQL(tbl);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
