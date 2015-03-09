package com.me.auction;
import android.app.Application;

import com.me.auction.datahelpers.MainDataHelper;


public class AuctionApplication extends Application {
	MainDataHelper mMainDataHelper;

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Create teacherkit database, and create the tables.
		mMainDataHelper = new MainDataHelper(this);
		mMainDataHelper.createTables();
		
	}

	

}
