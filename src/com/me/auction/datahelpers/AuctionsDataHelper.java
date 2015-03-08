package com.me.auction.datahelpers;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.auction.model.Auction;
import com.me.auction.utils.Constants;

public class AuctionsDataHelper extends SQLiteOpenHelper {

	Context mContext;

	public AuctionsDataHelper(Context context) {
		super(context, Constants.DB_NAME, null, 1);
		this.mContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		createAuctionsTable(db);

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

	public long createAuction(Auction auction) {
		long id = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_ITEM_TITLE, auction.getItemTitle());
		values.put(Constants.KEY_ITEM_DESCRIPTION, auction.getItemDescription());
		values.put(Constants.KEY_START_DATE, auction.getStartDate());
		values.put(Constants.KEY_DURATION, auction.getDurationInHours());
		values.put(Constants.KEY_OWNER_ID, auction.getItemOwnerId());

		try {
			id = db.insert(Constants.TABLE_AUCTIONS, null, values);
			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}

	public Auction getAuctionWithId(Long auctionId) {
		Auction auction = new Auction();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_ID + " = ?",
				new String[] { auctionId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
			} while (cursor.moveToNext());
		}
		return auction;
	}

	public ArrayList<Auction> getAllAuctions() {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Constants.TABLE_AUCTIONS, null, null, null,
				null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public ArrayList<Auction> getUpComingAuctions() {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();
		Long nowDateInMilis = Calendar.getInstance().getTimeInMillis();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_START_DATE + " > ?",
				new String[] { nowDateInMilis.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public Auction getAuctionWithBids(Long auctionId) {
		Auction auction = new Auction();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_ID + " = ?",
				new String[] { auctionId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				// Get Bids for current "Auction"
				BidsDataHelper bidDataHelper = new BidsDataHelper(mContext);
				auction.setBidsList(bidDataHelper
						.getBidsListForAuction(auctionId));
			} while (cursor.moveToNext());
		}
		return auction;
	}

	public long updateUser(Auction auction) {
		long id = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_ITEM_TITLE, auction.getItemTitle());
		values.put(Constants.KEY_ITEM_DESCRIPTION, auction.getItemDescription());
		values.put(Constants.KEY_START_DATE, auction.getStartDate());
		values.put(Constants.KEY_DURATION, auction.getDurationInHours());
		values.put(Constants.KEY_OWNER_ID, auction.getItemOwnerId());

		try {
			id = db.update(Constants.TABLE_AUCTIONS, values, Constants.KEY_ID
					+ "=" + auction.getId(), null);

			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}

	public ArrayList<Auction> getHapeeningNowAuctions() {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();
		Long nowDateInMilis = Calendar.getInstance().getTimeInMillis();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_START_DATE + " < ?",
				new String[] { nowDateInMilis.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public ArrayList<Auction> getUserAuctions(Long userId) {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_OWNER_ID + " = ?",
				new String[] { userId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public ArrayList<Auction> getUserWonAuctions(Long userId) {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_WINNER__USER_ID + " = ?",
				new String[] { userId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public ArrayList<Auction> getAuctionsList(Long[] usersIds) {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();
		SQLiteDatabase db = getWritableDatabase();
		// Convert userIds Array to String Array
		String[] usersIdsStr = new String[usersIds.length];
		for (int i = 0; i < usersIds.length; i++) {
			usersIdsStr[i] = usersIds[i].toString();
		}
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_AUCTIONS
				+ " where " + Constants.KEY_OWNER_ID + " = ?", usersIdsStr);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Auction auction = new Auction();
				auction.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				auction.setItemTitle(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_TITLE)));
				auction.setItemDescription(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_ITEM_DESCRIPTION)));
				auction.setStartDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_START_DATE)));
				auction.setDurationInHours(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_DURATION)));
				auction.setItemOwnerId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_OWNER_ID)));
				auctionsList.add(auction);
			} while (cursor.moveToNext());
		}
		return auctionsList;
	}

	public ArrayList<Auction> getAuctionsListWithBids(Long[] auctionsIds) {
		ArrayList<Auction> auctionsList = new ArrayList<Auction>();

		for (int i = 0; i < auctionsIds.length; i++) {
			auctionsList.add(getAuctionWithBids(auctionsIds[i]));
		}
		return auctionsList;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
