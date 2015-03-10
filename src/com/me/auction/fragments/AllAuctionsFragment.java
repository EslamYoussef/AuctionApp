package com.me.auction.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.me.auction.AuctionDetailsActivity;
import com.me.auction.R;
import com.me.auction.adapters.AuctionsAdapter;
import com.me.auction.model.Auction;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.utils.Constants;

public class AllAuctionsFragment extends Fragment implements
		OnItemClickListener {

	ListView lvStartedAuctionsList, lvUpComingAuctionList;
	ArrayList<Auction> mStartedAuctions, mUpComingAuction;
	AuctionPresenter mAuctionPresenter;
	TextView tvNoStartedAuctions, tvNoUpComingAuctions;
	AuctionsAdapter mStartedAdapter, mUpComingdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_divided_auctions,
				container, false);
		lvStartedAuctionsList = (ListView) rootView
				.findViewById(R.id.lvStartedAuctionsList);
		tvNoStartedAuctions = (TextView) rootView
				.findViewById(R.id.tvNoStartedAuctions);
		lvUpComingAuctionList = (ListView) rootView
				.findViewById(R.id.lvUpComingAuctionsList);
		tvNoUpComingAuctions = (TextView) rootView
				.findViewById(R.id.tvNoUpComingAuctions);
		mAuctionPresenter = new AuctionPresenter(getActivity());
		mStartedAuctions = mAuctionPresenter.getHapeeningNowAuctions();
		mUpComingAuction = mAuctionPresenter.getUpComingAuctions();
		if (null != mStartedAuctions && mStartedAuctions.size() > 0) {
			tvNoStartedAuctions.setVisibility(View.GONE);
			lvStartedAuctionsList.setVisibility(View.VISIBLE);
			mStartedAdapter = new AuctionsAdapter(getActivity(),
					mStartedAuctions);
			lvStartedAuctionsList.setAdapter(mStartedAdapter);
			lvStartedAuctionsList.setOnItemClickListener(this);
		} else {
			lvStartedAuctionsList.setVisibility(View.GONE);
			tvNoStartedAuctions.setVisibility(View.VISIBLE);
		}
		if (null != mUpComingAuction && mUpComingAuction.size() > 0) {
			tvNoUpComingAuctions.setVisibility(View.GONE);
			lvUpComingAuctionList.setVisibility(View.VISIBLE);
			mUpComingdapter = new AuctionsAdapter(getActivity(),
					mUpComingAuction);
			lvUpComingAuctionList.setAdapter(mUpComingdapter);
			lvUpComingAuctionList.setOnItemClickListener(this);
		} else {
			lvUpComingAuctionList.setVisibility(View.GONE);
			tvNoUpComingAuctions.setVisibility(View.VISIBLE);
		}
		return rootView;
	}

	@Override
	public void onResume() {

		super.onResume();

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch (arg0.getId()) {
		case R.id.lvUpComingAuctionsList:
			Intent i = new Intent(getActivity(), AuctionDetailsActivity.class);
			i.putExtra(Constants.KEY_AUCTION_ID, mUpComingAuction.get(arg2)
					.getId());
			startActivity(i);
			break;
		case R.id.lvStartedAuctionsList:
			Intent intent = new Intent(getActivity(),
					AuctionDetailsActivity.class);
			intent.putExtra(Constants.KEY_AUCTION_ID, mStartedAuctions
					.get(arg2).getId());
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}