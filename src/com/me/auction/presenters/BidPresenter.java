package com.me.auction.presenters;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.auction.datahelpers.BidsDataHelper;
import com.me.auction.model.Bid;
import com.me.auction.utils.Constants;

public class BidPresenter {
	BidsDataHelper mBidsDataHelper;
	Context mContext;

	public BidPresenter(Context context) {
		mContext = context;
		mBidsDataHelper = new BidsDataHelper(mContext);
	}

	public long createBid(Bid bid) {
		return mBidsDataHelper.createBid(bid);
	}

	public long updateUserBid(Bid bid) {
		return mBidsDataHelper.updateUserBid(bid);
	}

	public Bid getBidWithId(Long bidId) {
		return mBidsDataHelper.getBidWithId(bidId);
	}

	public Bid getUserBidinAuction(Long userId, Long auctionId) {
		return mBidsDataHelper.getUserBidinAuction(userId, auctionId);
	}

	public ArrayList<Bid> getAllBids() {
		return mBidsDataHelper.getAllBids();
	}

	public ArrayList<Bid> getBidsListForAuction(Long auctionId) {
		return mBidsDataHelper.getBidsListForAuction(auctionId);
	}
}
