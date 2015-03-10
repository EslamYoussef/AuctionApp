package com.me.auction.fragments;

import com.me.auction.R;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.presenters.BidPresenter;
import com.me.auction.presenters.UserPresenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatisticsFragment extends Fragment {

	TextView tvUserscount, tvAuctionsCount, tvBidsCount;
	UserPresenter mUserPresenter;
	BidPresenter mBidsPresenter;
	AuctionPresenter mAuctionPresenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_statistics,
				container, false);
		tvUserscount = (TextView) rootView.findViewById(R.id.tvUsersCount);
		tvBidsCount = (TextView) rootView.findViewById(R.id.tvBidsCount);
		tvAuctionsCount = (TextView) rootView
				.findViewById(R.id.tvAuctionsCount);
		// Init Presenters
		mUserPresenter = new UserPresenter(getActivity());
		mBidsPresenter = new BidPresenter(getActivity());
		mAuctionPresenter = new AuctionPresenter(getActivity());
		tvUserscount.setText(mUserPresenter.getUsersCount().toString());
		tvBidsCount.setText(mBidsPresenter.getBidssCount().toString());
		tvAuctionsCount
				.setText(mAuctionPresenter.getAuctionsCount().toString());
		return rootView;
	}
}