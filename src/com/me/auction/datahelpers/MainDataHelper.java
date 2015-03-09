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
		// Create User
		User user = new User();
		user.setfName("Eslam");
		user.setlName("Eslam");
		user.setDisplayName("Eslam");
		user.setPassword("12345");
		UserPresenter mPresnter = new UserPresenter(mContext);
		Long id = mPresnter.register(user);
		// Create Auction
		Auction auction = new Auction();
		auction.setIsClosed(0);
		auction.setItemDescription("Description");
		auction.setItemOwnerId((long) 1);
		auction.setItemOwnerName("Eslam");
		auction.setItemTitle("Item 1");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 5);
		auction.setStartDate(cal.getTimeInMillis());
		auction.setDurationInHours(2);
		AuctionPresenter mAuctionPresnter = new AuctionPresenter(mContext);
		Long auctionId = mAuctionPresnter.createAuction(auction);
		ArrayList<Auction> auctionsList = mAuctionPresnter.getAllAuctions();
		auctionsList = mAuctionPresnter.getHapeeningNowAuctions();
		auctionsList = mAuctionPresnter.getUpComingAuctions();
		auctionsList = mAuctionPresnter.getUserAuctions((long) 1);
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
