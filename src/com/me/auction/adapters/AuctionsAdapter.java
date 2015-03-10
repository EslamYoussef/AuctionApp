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
import com.me.auction.model.Auction;
import com.me.auction.utils.Utils;

public class AuctionsAdapter extends ArrayAdapter<Auction> {
	Context mContext;
	List<Auction> mAuctionsList;

	public AuctionsAdapter(Context context, List<Auction> objects) {
		super(context, R.layout.cell_auction, objects);
		mContext = context;
		mAuctionsList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AuctionCell mAuctionCell = null;
		if (null == convertView) {
			convertView = ((Activity) (mContext)).getLayoutInflater().inflate(
					R.layout.cell_auction, parent, false);
			mAuctionCell = new AuctionCell();
			mAuctionCell.setTvItemTitle((TextView) convertView
					.findViewById(R.id.tvItemTitle));
			mAuctionCell.setTvItemDescription((TextView) convertView
					.findViewById(R.id.tvItemDescription));
			mAuctionCell.setTvStartDate((TextView) convertView
					.findViewById(R.id.tvStartDate));
			mAuctionCell.setTvIsClosed((TextView) convertView
					.findViewById(R.id.tvIsClosed));
			mAuctionCell.setTvOwnerName((TextView) convertView
					.findViewById(R.id.tvItemOwner));
			convertView.setTag(mAuctionCell);

		} else {
			mAuctionCell = (AuctionCell) convertView.getTag();
		}
		Auction currentAuction = mAuctionsList.get(position);
		mAuctionCell.getTvItemTitle().setText(currentAuction.getItemTitle());
		mAuctionCell.getTvItemDescription().setText(
				currentAuction.getItemDescription());
		mAuctionCell.getTvOwnerName()
				.setText(currentAuction.getItemOwnerName());
		String dateStr = Utils.getDateStringfromDate(mContext,
				currentAuction.getStartDate());
		mAuctionCell.getTvStartDate().setText(dateStr);
		if (currentAuction.getIsClosed() == 0) {
			mAuctionCell.getTvIsClosed().setVisibility(View.GONE);
		} else {
			mAuctionCell.getTvIsClosed().setVisibility(View.VISIBLE);
		}
		return convertView;
	}
}
