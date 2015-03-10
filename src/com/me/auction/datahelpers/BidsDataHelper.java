package com.me.auction.datahelpers;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.auction.model.Auction;
import com.me.auction.model.Bid;
import com.me.auction.utils.Constants;

public class BidsDataHelper extends SQLiteOpenHelper {

	Context mContext;

	public BidsDataHelper(Context context) {
		super(context, Constants.DB_NAME, null, 1);
		this.mContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		createBidsTable(db);

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

	public long createBid(Bid bid) {
		long id = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_BID_DATE, bid.getBidDate());
		values.put(Constants.KEY_USER_ID, bid.getUserId());
		values.put(Constants.KEY_AUCTION_ID, bid.getAuctionId());
		values.put(Constants.KEY_IS_WON, bid.getIsWon());
		values.put(Constants.KEY_BID_VALUE, bid.getBidValue());
		values.put(Constants.KEY_USER_NAME, bid.getUserName());

		try {
			id = db.insert(Constants.TABLE_BIDS, null, values);
			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}

	public long updateUserBid(Bid bid) {
		long id = 0;
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Constants.KEY_BID_DATE, bid.getBidDate());
		values.put(Constants.KEY_USER_ID, bid.getUserId());
		values.put(Constants.KEY_AUCTION_ID, bid.getAuctionId());
		values.put(Constants.KEY_IS_WON, bid.getIsWon());
		values.put(Constants.KEY_BID_VALUE, bid.getBidValue());

		try {
			id = db.update(Constants.TABLE_BIDS, values, Constants.KEY_ID + "="
					+ bid.getId(), null);

			if (db.isOpen())
				db.close();
		} catch (Exception ex) {

			System.out.println(ex.getMessage());
		}

		return id;
	}

	public Bid getBidWithId(Long bidId) {
		Bid bid = new Bid();
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_BIDS
				+ " where " + Constants.KEY_ID + " = ?",
				new String[] { bidId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				bid.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				bid.setAuctionId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_AUCTION_ID)));
				bid.setUserId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_USER_ID)));
				bid.setBidDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_BID_DATE)));
				bid.setUserName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_USER_NAME)));
				bid.setBidValue(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_BID_VALUE)));
				bid.setIsWon(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_IS_WON)));

			} while (cursor.moveToNext());
		}
		return bid;
	}

	public Bid getUserBidinAuction(Long userId, Long auctionId) {
		Bid bid = new Bid();
		SQLiteDatabase db = getWritableDatabase();
		String queryString = "select * from " + Constants.TABLE_BIDS
				+ " where " + Constants.KEY_USER_ID + " = " + userId.toString()
				+ " AND " + Constants.KEY_AUCTION_ID + " = "
				+ auctionId.toString();
		Cursor cursor = db.rawQuery(queryString, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				bid.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				bid.setAuctionId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_AUCTION_ID)));
				bid.setUserId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_USER_ID)));
				bid.setBidDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_BID_DATE)));
				bid.setUserName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_USER_NAME)));
				bid.setBidValue(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_BID_VALUE)));
				bid.setIsWon(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_IS_WON)));
			} while (cursor.moveToNext());
		}
		return bid;
	}

	public ArrayList<Bid> getAllBids() {
		ArrayList<Bid> bidsList = new ArrayList<Bid>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Constants.TABLE_BIDS, null, null, null, null,
				null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Bid bid = new Bid();
				bid.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				bid.setAuctionId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_AUCTION_ID)));
				bid.setUserId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_USER_ID)));
				bid.setBidDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_BID_DATE)));
				bid.setUserName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_USER_NAME)));
				bid.setBidValue(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_BID_VALUE)));
				bid.setIsWon(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_IS_WON)));

				bidsList.add(bid);
			} while (cursor.moveToNext());
		}
		return bidsList;
	}

	public int getBidssCount() {

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.query(Constants.TABLE_BIDS, null, null, null, null,
				null, null);
		return cursor.getCount();
	}

	public ArrayList<Bid> getBidsListForAuction(Long auctionId) {
		ArrayList<Bid> bidsList = new ArrayList<Bid>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + Constants.TABLE_BIDS
				+ " where " + Constants.KEY_AUCTION_ID + " = ?",
				new String[] { auctionId.toString() });
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Bid bid = new Bid();
				bid.setId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_ID)));
				bid.setAuctionId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_AUCTION_ID)));
				bid.setUserId(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_USER_ID)));
				bid.setBidDate(cursor.getLong(cursor
						.getColumnIndex(Constants.KEY_BID_DATE)));
				bid.setUserName(cursor.getString(cursor
						.getColumnIndex(Constants.KEY_USER_NAME)));
				bid.setBidValue(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_BID_VALUE)));
				bid.setIsWon(cursor.getInt(cursor
						.getColumnIndex(Constants.KEY_IS_WON)));

				bidsList.add(bid);
			} while (cursor.moveToNext());
		}
		return bidsList;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
