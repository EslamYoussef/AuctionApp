package com.me.auction.fragments;

import com.me.auction.R;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
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
		return frament;
	}

	private void startLoading() {
		mLoadingDialog = new ProgressDialog(getActivity());
		mLoadingDialog.setMessage(getResources()
				.getString(R.string.str_loading));
		mLoadingDialog.setCanceledOnTouchOutside(false);
		mLoadingDialog.show();
	}

	private void endLoading() {
		if (null != mLoadingDialog) {
			mLoadingDialog.cancel();
			mLoadingDialog.dismiss();
		}
	}

	public void loadSwipeFragments(int currentItem) {
		mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity()
				.getSupportFragmentManager());
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(currentItem, true);

	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;

			switch (position) {
			// Hepenning Now
			case 0: {

				break;
			}
			// Category
			case 1: {

				break;
			}
			// Age Group
			case 2: {

				break;
			}
			// Date
			case 3: {

				break;
			}
			// Advanced Search

			case 4:

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