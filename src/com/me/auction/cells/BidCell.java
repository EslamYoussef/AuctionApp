package com.me.auction.cells;

import android.widget.TextView;

public class BidCell {
	private TextView tvUserName;
	private TextView tvBidValue;
	private TextView tvDate;
	private TextView tvIsWinner;

	public TextView getTvUserName() {
		return tvUserName;
	}

	public void setTvUserName(TextView tvUserName) {
		this.tvUserName = tvUserName;
	}

	public TextView getTvBidValue() {
		return tvBidValue;
	}

	public void setTvBidValue(TextView tvBidValue) {
		this.tvBidValue = tvBidValue;
	}

	public TextView getTvDate() {
		return tvDate;
	}

	public void setTvDate(TextView tvDate) {
		this.tvDate = tvDate;
	}

	public TextView getTvIsWinner() {
		return tvIsWinner;
	}

	public void setTvIsWinner(TextView tvIsWinner) {
		this.tvIsWinner = tvIsWinner;
	}

}
