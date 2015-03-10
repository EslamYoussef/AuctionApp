package com.me.auction.fragments;

import com.me.auction.AddAuctionActivity;
import com.me.auction.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AuctionsFragment extends Fragment {

	SectionsPagerAdapter mSectionsPagerAdapter;
	private ActionBar mActionBar;
	private ViewPager mViewPager;

	PagerTabStrip mPagerStrip;
	private String[] mEventsSwipeTitles;
	ProgressDialog mLoadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View frament = inflater.inflate(R.layout.fragment_auctions, container,
				false);

		mViewPager = (ViewPager) frament.findViewById(R.id.pager);

		mEventsSwipeTitles = getResources().getStringArray(
				R.array.events_swipe_titles);
		mActionBar = ((ActionBarActivity) (getActivity()))
				.getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		this.setHasOptionsMenu(true);
		loadSwipeFragments(0);
		return frament;
	}

	@Override
	public void onResume() {
		loadSwipeFragments(0);
		super.onResume();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.menu_add_auction, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			startActivity(new Intent(getActivity(), AddAuctionActivity.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void loadSwipeFragments(int currentItem) {
		mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity()
				.getSupportFragmentManager());
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setCurrentItem(currentItem, true);
		mSectionsPagerAdapter.notifyDataSetChanged();
	}

	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;

			switch (position) {
			// Hepenning Now

			case 0: {
				fragment = new AllAuctionsFragment();
				break;
			}
			// Won
			case 1: {
				fragment = new WonAuctionsFragment();
				break;
			}
			// My Actions
			case 2: {
				fragment = new MyAuctionsFragment();
				break;
			}

			}

			return fragment;
		}

		@Override
		public int getCount() {

			return mEventsSwipeTitles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return mEventsSwipeTitles[position];
		}
	}
}