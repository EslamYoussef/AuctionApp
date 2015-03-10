package com.me.auction;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.me.auction.fragments.ImageFragment;
import com.me.auction.utils.Constants;
import com.me.auction.utils.Utils;

public class WelcomeActivity extends FragmentActivity {
	ViewPager vpWelcomeImages;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ArrayList<Integer> welcomeImagesList;
	ProgressDialog mProgressDialog;

	// CirclePageIndicator mCircleIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
		// startLoading();
		// Parse.initialize(this, "vvIFEVKHztE3l8CZrECjn09T3j8cjB3y0E3VxCN8",
		// "skHvUZEUu1GqdD0LbqMyhzutGCwjIm5fUcWV6Ddj");
		// endLoading();
		// Check if loogged in user

		if (isUserLoggedIn()) {

			startMainActivity();
			this.finish();
		} else {

			// getActionBar().setTitle(R.string.title_activity_welcome);
			vpWelcomeImages = (ViewPager) findViewById(R.id.vpWelcomeImages);
			// mCircleIndicator = (CirclePageIndicator)
			// findViewById(R.id.titles);
			welcomeImagesList = Utils.getWelcomeImagesResourceList();

			loadSwipeFragments(0);
			
		}
	}

	private void startMainActivity() {
		startActivity(new Intent(this, MainActivity.class));

	}

	private boolean isUserLoggedIn() {
		Long userId = Utils.getSavedUserId(this);
		if (userId > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void loadSwipeFragments(int currentItem) {
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		vpWelcomeImages.setAdapter(mSectionsPagerAdapter);
		// Bind the title indicator to the adapter
		// mCircleIndicator.setViewPager(vpWelcomeImages);

		vpWelcomeImages.setCurrentItem(currentItem, true);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			ImageFragment fragment = new ImageFragment();
			fragment.setImage(getResources().getDrawable(
					welcomeImagesList.get(position)));

			return fragment;
		}

		@Override
		public int getCount() {

			return welcomeImagesList.size();
		}
	}

	public void registeration(View v) {
		Intent i = new Intent(this, RegisterationActivity.class);
		i.putExtra(Constants.KEY_MODE, Constants.EMAIL_REG_MODE);
		startActivityForResult(i, 0);

	}

	public void login(View v) {
		startActivityForResult(new Intent(this, LoginActivity.class), 0);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			this.finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
