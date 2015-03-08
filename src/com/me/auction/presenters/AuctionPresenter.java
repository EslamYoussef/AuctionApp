package com.me.auction.presenters;

import java.util.ArrayList;
import android.content.Context;
import com.me.auction.datahelpers.AuctionsDataHelper;
import com.me.auction.model.Auction;

public class AuctionPresenter {

	AuctionsDataHelper mAuctionsDataHelper;
	Context mContext;

	public AuctionPresenter(Context context) {
		mContext = context;
		mAuctionsDataHelper = new AuctionsDataHelper(mContext);
	}

	public long createAuction(Auction auction) {

		return mAuctionsDataHelper.createAuction(auction);
	}

	public Auction getAuctionWithId(Long auctionId) {

		return mAuctionsDataHelper.getAuctionWithId(auctionId);
	}

	public ArrayList<Auction> getAllAuctions() {

		return mAuctionsDataHelper.getAllAuctions();
	}

	public ArrayList<Auction> getUpComingAuctions() {

		return mAuctionsDataHelper.getUpComingAuctions();
	}

	public Auction getAuctionWithBids(Long auctionId) {

		return mAuctionsDataHelper.getAuctionWithBids(auctionId);
	}

	public long updateUser(Auction auction) {

		return mAuctionsDataHelper.updateUser(auction);
	}

	public ArrayList<Auction> getHapeeningNowAuctions() {

		return mAuctionsDataHelper.getHapeeningNowAuctions();
	}

	public ArrayList<Auction> getUserAuctions(Long userId) {

		return mAuctionsDataHelper.getUserAuctions(userId);
	}

	public ArrayList<Auction> getUserWonAuctions(Long userId) {

		return mAuctionsDataHelper.getUserWonAuctions(userId);
	}

	public ArrayList<Auction> getAuctionsList(Long[] usersIds) {

		return mAuctionsDataHelper.getAuctionsList(usersIds);
	}

	public ArrayList<Auction> getAuctionsListWithBids(Long[] auctionsIds) {

		return mAuctionsDataHelper.getAuctionsListWithBids(auctionsIds);
	}
}
