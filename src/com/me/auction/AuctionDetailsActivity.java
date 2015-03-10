package com.me.auction;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.me.auction.adapters.BidsAdapter;
import com.me.auction.fragments.AddBidFragmentDialog;
import com.me.auction.listeners.IBidValueListener;
import com.me.auction.model.Auction;
import com.me.auction.model.Bid;
import com.me.auction.model.User;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.presenters.BidPresenter;
import com.me.auction.presenters.UserPresenter;
import com.me.auction.utils.Constants;
import com.me.auction.utils.Utils;

public class AuctionDetailsActivity extends ActionBarActivity implements
		IBidValueListener {
	Long auctionId;
	Auction mAuction;
	AuctionPresenter mAuctionPresenter;
	TextView tvItemTitle, tvItemDescription, tvStartDate, tvIsClosed,
			tvItemOwner, tvNotStarted;
	Button bAddBid;
	ArrayList<Bid> mAuctionBidsList;
	LinearLayout llBids;
	ListView lvAuctionBids;
	Integer mBidValue;
	BidPresenter mBidPresenter;
	User mUser;
	UserPresenter mUserPresenter;
	ArrayList<User> mUsersList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auction_details);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// Init Views
		tvItemTitle = (TextView) findViewById(R.id.tvItemTitle);
		tvItemDescription = (TextView) findViewById(R.id.tvItemDescription);
		tvStartDate = (TextView) findViewById(R.id.tvAuctionStartDate);
		tvItemOwner = (TextView) findViewById(R.id.tvItemOwner);
		tvIsClosed = (TextView) findViewById(R.id.tvIsClosed);
		tvNotStarted = (TextView) findViewById(R.id.tvNotStarted);
		llBids = (LinearLayout) findViewById(R.id.llBids);
		bAddBid = (Button) findViewById(R.id.bAddBid);
		lvAuctionBids = (ListView) findViewById(R.id.lvAuctionBids);
		// Receive Auction Id
		auctionId = getIntent().getExtras().getLong(Constants.KEY_AUCTION_ID);
		// Get Current User
		mUser = Utils.getCurrentUser(this);
		mBidPresenter = new BidPresenter(this);
		mUserPresenter = new UserPresenter(this);
		mAuctionPresenter = new AuctionPresenter(this);
		mAuction = mAuctionPresenter.getAuctionWithBids(auctionId);
		fillViews();

	}

	private void fillViews() {
		if (null != mAuction) {
			tvItemTitle.setText(mAuction.getItemTitle());
			tvItemDescription.setText(mAuction.getItemDescription());
			tvItemOwner.setText(mAuction.getItemOwnerName());
			tvStartDate.setText(Utils.getDateStringfromDate(this,
					mAuction.getStartDate()));
			// Check if Closed
			if (mAuction.getIsClosed() == 0) {
				tvIsClosed.setVisibility(View.GONE);

			} else {
				tvIsClosed.setVisibility(View.VISIBLE);

			}
			// Check if not started
			if (mAuction.getStartDate() > Calendar.getInstance()
					.getTimeInMillis()) {

				tvNotStarted.setVisibility(View.VISIBLE);
			} else {
				tvNotStarted.setVisibility(View.GONE);
			}
			// Fill Bids List
			mAuctionBidsList = mAuction.getBidsList();
			if (null != mAuctionBidsList && mAuctionBidsList.size() > 0) {
				llBids.setVisibility(View.VISIBLE);
				lvAuctionBids.setVisibility(View.VISIBLE);
				BidsAdapter adapter = new BidsAdapter(this, mAuctionBidsList);
				lvAuctionBids.setAdapter(adapter);
			} else {
				llBids.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auction_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void addBid(View v) {
		if (null != mAuction) {
			if (mAuction.getIsClosed() == 1) {
				Toast.makeText(this, R.string.str_auction_is_closed,
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (mAuction.getStartDate() > Calendar.getInstance()
					.getTimeInMillis()) {
				Toast.makeText(this, R.string.str_auction_not_started,
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		AddBidFragmentDialog bidFragmentDialog = new AddBidFragmentDialog();
		bidFragmentDialog.setBidListener(this);
		bidFragmentDialog.show(getSupportFragmentManager(), "");
	}

	@Override
	public void setBidValue(Integer bidValue) {
		mBidValue = bidValue;
		// Add Bid to Auction
		Bid mBid = new Bid();
		mBid.setAuctionId(mAuction.getId());
		mBid.setBidDate(Calendar.getInstance().getTimeInMillis());
		mBid.setBidValue(mBidValue);

		if (null != mUser) {
			mBid.setUserId(mUser.getId());
			mBid.setUserName(mUser.getDisplayName());
		}
		mBidPresenter.createBid(mBid);
		// Create Random Bids for all users
		mUsersList = mUserPresenter.getAllUsers();
		if (null != mUsersList && mUsersList.size() > 0) {
			for (User user : mUsersList) {
				// Execlude Logged In user
				if (user.getId() == mUser.getId()) {
					continue;
				}
				Bid randomBid = new Bid();
				randomBid.setAuctionId(mAuction.getId());
				randomBid.setBidDate(Calendar.getInstance().getTimeInMillis());
				randomBid.setBidValue(Utils.randInt(1, 1000));
				randomBid.setUserId(user.getId());
				randomBid.setUserName(user.getDisplayName());
				mBidPresenter.createBid(randomBid);
			}
		}
		mAuction = mAuctionPresenter.getAuctionWithBids(mAuction.getId());
		// Find the Winner Bid
		mAuctionBidsList = mAuction.getBidsList();
		Bid winnerBid = null;
		int maxBid = -1;

		if (null != mAuctionBidsList && mAuctionBidsList.size() > 0)
			for (Bid b : mAuctionBidsList) {
				if (b.getBidValue() > maxBid) {
					winnerBid = b;
					maxBid = b.getBidValue();
				}
			}
		if (null != winnerBid) {
			// Save Winner Bid
			winnerBid.setIsWon(1);
			mBidPresenter.updateUserBid(winnerBid);
			// Update Auction with winner
			mAuction.setWinnerUserId(winnerBid.getUserId());
			mAuction.setIsClosed(1);
			mAuctionPresenter.updateUser(mAuction);

		}
		//
		Toast.makeText(this, R.string.str_bids_added_randomly,
				Toast.LENGTH_LONG).show();
		fillViews();

	}

	@Override
	public Context getCOntext() {
		return this;
	}

}
