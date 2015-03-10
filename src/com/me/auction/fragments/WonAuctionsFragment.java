package com.me.auction.fragments;

import java.util.ArrayList;

import com.me.auction.AuctionDetailsActivity;
import com.me.auction.R;
import com.me.auction.adapters.AuctionsAdapter;
import com.me.auction.model.Auction;
import com.me.auction.model.User;
import com.me.auction.presenters.AuctionPresenter;
import com.me.auction.utils.Constants;
import com.me.auction.utils.Utils;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WonAuctionsFragment extends Fragment implements
		OnItemClickListener {

	ListView lvAuctionsList;
	ArrayList<Auction> mAllAuctions;
	AuctionPresenter mAuctionPresenter;
	TextView tvNoAuctions;
	User mUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_list_auctions,
				container, false);
		lvAuctionsList = (ListView) rootView.findViewById(R.id.lvAuctionsList);
		tvNoAuctions = (TextView) rootView.findViewById(R.id.tvNoAuctions);
		mAuctionPresenter = new AuctionPresenter(getActivity());
		// Get Current User
		mUser = Utils.getCurrentUser(getActivity());
		if (null != mUser && null != mUser.getId())
			mAllAuctions = mAuctionPresenter.getUserWonAuctions(mUser.getId());
		if (null != mAllAuctions && mAllAuctions.size() > 0) {
			tvNoAuctions.setVisibility(View.GONE);
			lvAuctionsList.setVisibility(View.VISIBLE);
			AuctionsAdapter adapter = new AuctionsAdapter(getActivity(),
					mAllAuctions);
			lvAuctionsList.setAdapter(adapter);
			lvAuctionsList.setOnItemClickListener(this);
		} else {
			lvAuctionsList.setVisibility(View.GONE);
			tvNoAuctions.setVisibility(View.VISIBLE);
		}
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent i = new Intent(getActivity(), AuctionDetailsActivity.class);
		i.putExtra(Constants.KEY_AUCTION_ID, mAllAuctions.get(arg2).getId());
		startActivity(i);

	}
}