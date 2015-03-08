package com.me.auction;

import com.me.auction.model.Auction;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.utils.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddAuctionActivity extends Activity {
	EditText etItemTitle, etItemDescription;
	TextView tvStartDate;
	Long startDate, mOwnerId;
	String mItemTitlestr = "", mItemDescription = "", mUserName = "";
	Auction mNewAuction;
	AuctionPresenter mAuctionPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_auction);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(R.string.title_activity_add_auction);
		// Receive Date
		Bundle extras = getIntent().getExtras();
		if (null != extras) {
			mOwnerId = extras.getLong(Constants.KEY_OWNER_ID);
			mUserName = extras.getString(Constants.KEY_USER_NAME);
		}
		etItemTitle = (EditText) findViewById(R.id.etItemTitle);
		etItemDescription = (EditText) findViewById(R.id.etItemDescription);
		tvStartDate = (TextView) findViewById(R.id.tvAuctionStartDate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_auction, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save) {
			mItemTitlestr = etItemTitle.getText().toString();
			if (mItemTitlestr.trim().length() <= 0) {
				etItemTitle.setError(getString(R.string.error_required_field));
				return false;
			}
			mItemDescription = etItemDescription.getText().toString();
			if (mItemDescription.trim().length() <= 0) {
				etItemDescription
						.setError(getString(R.string.error_required_field));
				return false;
			}
			// Create Auction
			mNewAuction = new Auction();
			mNewAuction.setItemTitle(mItemTitlestr);
			mNewAuction.setItemDescription(mItemDescription);
			mNewAuction.setStartDate(startDate);
			mNewAuction.setItemOwnerId(mOwnerId);
			mNewAuction.setItemOwnerName(mUserName);
			// Add New Auction
			Long auctionId = mAuctionPresenter.createAuction(mNewAuction);
			if (auctionId > 0) {
				Toast.makeText(this, R.string.str_added_successfully,
						Toast.LENGTH_SHORT).show();
				this.finish();
			} else {
				Toast.makeText(this, R.string.str_auction_added_failure,
						Toast.LENGTH_LONG).show();
			}

			return true;
		} else if (id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
