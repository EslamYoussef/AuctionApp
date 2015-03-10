package com.me.auction;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.me.auction.model.Auction;
import com.me.auction.model.User;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.utils.Utils;

public class AddAuctionActivity extends ActionBarActivity implements
		OnClickListener, OnDateSetListener {
	EditText etItemTitle, etItemDescription;
	TextView tvStartDate;
	Long startDate, mOwnerId;
	String mItemTitlestr = "", mItemDescription = "", mUserName = "";
	Auction mNewAuction;
	AuctionPresenter mAuctionPresenter;
	User mUser;
	RelativeLayout rlAuctionStartDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_auction);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(R.string.title_activity_add_auction);
		mAuctionPresenter = new AuctionPresenter(this);
		mUser = Utils.getCurrentUser(this);
		// Receive Date

		if (null != mUser) {
			mOwnerId = mUser.getId();
			mUserName = mUser.getDisplayName();
		}
		etItemTitle = (EditText) findViewById(R.id.etItemTitle);
		etItemDescription = (EditText) findViewById(R.id.etItemDescription);
		tvStartDate = (TextView) findViewById(R.id.tvAuctionStartDate);
		rlAuctionStartDate = (RelativeLayout) findViewById(R.id.rlAuctionStartDate);
		rlAuctionStartDate.setOnClickListener(this);
		// Show Now Date
		startDate = Calendar.getInstance().getTimeInMillis();
		tvStartDate.setText(Utils.getDateStringfromDate(this, startDate));
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
			mNewAuction.setIsClosed(0);
			mNewAuction.setDurationInHours(4);
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

	@Override
	public void onClick(View v) {
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(startDate);
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH);
		int day = calender.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dpdFromDate = new DatePickerDialog(this, this, year,
				month, day);
		dpdFromDate.show();

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		Long now = cal.getTimeInMillis();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, monthOfYear);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		Long selectedDate = cal.getTimeInMillis();
		if (selectedDate < now) {
			Toast.makeText(this, R.string.str_passed_date, Toast.LENGTH_LONG)
					.show();
		} else {
			startDate = selectedDate;
			tvStartDate.setText(Utils.getDateStringfromDate(this, startDate));
		}

	}
}
