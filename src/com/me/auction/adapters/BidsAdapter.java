package com.me.auction.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.me.auction.R;
import com.me.auction.cells.AuctionCell;
import com.me.auction.cells.BidCell;
import com.me.auction.model.Auction;
import com.me.auction.model.Bid;
import com.me.auction.utils.Utils;

public class BidsAdapter extends ArrayAdapter<Bid> {
	Context mContext;
	List<Bid> mBidsList;

	public BidsAdapter(Context context, List<Bid> objects) {
		super(context, R.layout.cell_bid, objects);
		mContext = context;
		mBidsList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BidCell mBidCell = null;
		if (null == convertView) {
			convertView = ((Activity) (mContext)).getLayoutInflater().inflate(
					R.layout.cell_bid, parent, false);
			mBidCell = new BidCell();
			mBidCell.setTvUserName(((TextView) convertView
					.findViewById(R.id.tvBidUserName)));
			mBidCell.setTvBidValue((TextView) convertView
					.findViewById(R.id.tvBidValue));
			mBidCell.setTvDate((TextView) convertView
					.findViewById(R.id.tvBidDate));
			mBidCell.setTvIsWinner((TextView) convertView
					.findViewById(R.id.tvIsWinner));
			convertView.setTag(mBidCell);

		} else {
			mBidCell = (BidCell) convertView.getTag();
		}
		Bid currentBid = mBidsList.get(position);
		mBidCell.getTvUserName().setText(currentBid.getUserName());
		mBidCell.getTvBidValue().setText(currentBid.getBidValue());

		String dateStr = Utils.getDateStringfromDate(mContext,
				currentBid.getBidDate());
		mBidCell.getTvDate().setText(dateStr);
		if (currentBid.getIsWon() == 0) {
			mBidCell.getTvIsWinner().setVisibility(View.GONE);
		} else {
			mBidCell.getTvIsWinner().setVisibility(View.VISIBLE);

		}
		return convertView;
	}
}
