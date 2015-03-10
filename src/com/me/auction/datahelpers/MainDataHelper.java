package com.me.auction.datahelpers;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.me.auction.model.Auction;
import com.me.auction.model.User;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.presenters.UserPresenter;
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
		// Create 2 Dummy User
		User user = new User();
		user.setfName("Eslam");
		user.setlName("Youssef");
		user.setDisplayName("EslamYoussef");
		user.setPassword("12345");
		UserPresenter mPresnter = new UserPresenter(mContext);
		Long id = mPresnter.register(user);
		//
		User user2 = new User();
		user2.setfName("John");
		user2.setlName("Marc");
		user2.setDisplayName("JohnMarc");
		user2.setPassword("54321");
		mPresnter = new UserPresenter(mContext);
		Long id2 = mPresnter.register(user2);
		// Create 3 Auction
		Auction auction = new Auction();
		auction.setIsClosed(0);
		auction.setItemDescription("The Auction Description");
		auction.setItemOwnerId((long) 1);
		auction.setItemOwnerName("EslamYoussef");
		auction.setItemTitle("My Old Fashioned Car");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 5);
		auction.setStartDate(cal.getTimeInMillis());
		auction.setDurationInHours(2);
		AuctionPresenter mAuctionPresnter = new AuctionPresenter(mContext);
		Long auctionId = mAuctionPresnter.createAuction(auction);
		//
		Auction auction2 = new Auction();
		auction2.setIsClosed(0);
		auction2.setItemDescription("The Second Auction Description");
		auction2.setItemOwnerId((long) 2);
		auction2.setItemOwnerName("JohnMarc");
		auction2.setItemTitle("My Beautiful Old House");
		Calendar cal2 = Calendar.getInstance();

		auction2.setStartDate(cal2.getTimeInMillis());
		auction2.setDurationInHours(2);
		mAuctionPresnter = new AuctionPresenter(mContext);
		Long auctionId2 = mAuctionPresnter.createAuction(auction2);
		//
		Auction auction3 = new Auction();
		auction3.setIsClosed(1);
		auction3.setItemDescription("The Third Auction Description");
		auction3.setItemOwnerId((long) 2);
		auction3.setItemOwnerName("JohnMarc");
		auction3.setItemTitle("My Old Laptop");
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.MONTH, 1);
		auction3.setStartDate(cal3.getTimeInMillis());
		auction3.setDurationInHours(2);
		mAuctionPresnter = new AuctionPresenter(mContext);
		Long auctionId3 = mAuctionPresnter.createAuction(auction3);
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
		String tbl = "create table " + Constants.TABLE_AUCTIONS + " ("
				+ Constants.KEY_ID + " integer primary key autoincrement, "
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
