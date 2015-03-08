package com.me.auction.cells;

import android.widget.TextView;

public class AuctionCell {
	private TextView tvItemTitle;
	private TextView tvItemDescription;
	private TextView tvOwnerName;
	private TextView tvStartDate;
	private TextView tvIsClosed;

	public TextView getTvItemTitle() {
		return tvItemTitle;
	}

	public void setTvItemTitle(TextView tvItemTitle) {
		this.tvItemTitle = tvItemTitle;
	}

	public TextView getTvItemDescription() {
		return tvItemDescription;
	}

	public void setTvItemDescription(TextView tvItemDescription) {
		this.tvItemDescription = tvItemDescription;
	}

	public TextView getTvOwnerName() {
		return tvOwnerName;
	}

	public void setTvOwnerName(TextView tvOwnerName) {
		this.tvOwnerName = tvOwnerName;
	}

	public TextView getTvStartDate() {
		return tvStartDate;
	}

	public void setTvStartDate(TextView tvStartDate) {
		this.tvStartDate = tvStartDate;
	}

	public TextView getTvIsClosed() {
		return tvIsClosed;
	}

	public void setTvIsClosed(TextView tvIsClosed) {
		this.tvIsClosed = tvIsClosed;
	}
}
