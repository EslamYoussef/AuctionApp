package com.me.auction;

import java.util.ArrayList;

import android.app.ActionBar;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.me.auction.adapters.NavDrawerListAdapter;
import com.me.auction.fragments.AuctionsFragment;
import com.me.auction.fragments.ProfileFragment;
import com.me.auction.fragments.StatisticsFragment;
import com.me.auction.model.NavDrawerItem;
import com.me.auction.model.NavImageViewItem;
import com.me.auction.model.NavTextIconItem;

public class MainActivity extends FragmentActivity implements
		OnItemClickListener {
	private DrawerLayout mDrawerLayout;
	ActionBar mActionBar;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	private CharSequence mTitle, mDrawerTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// Drawer Preparation
		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Katara Image View
		navDrawerItems.add(new NavImageViewItem(R.drawable.ic_launcher));
		// Menu Items
		for (int i = 0; i < navMenuTitles.length; i++) {
			navDrawerItems.add(new NavTextIconItem(navMenuTitles[i],
					navMenuIcons.getResourceId(i, -1)));
		}

		fillDrawerList();

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.drawable.ic_launcher) {
			public void onDrawerClosed(View view) {
				mActionBar.setTitle(mTitle);
				mDrawerToggle.syncState();
				// calling onPrepareOptionsMenu() to show action bar icons
				// invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				mDrawerToggle.syncState();
				fillDrawerList();
				// End of Drawer Refrshment
				mActionBar.setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				// invalidateOptionsMenu();
			}
		};
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		// enabling action bar app icon and behaving it as toggle button
		mActionBar = getActionBar();

		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);

	}

	// You need to do the Play Services APK check here too.
	@Override
	protected void onResume() {
		super.onResume();

	}

	private void fillDrawerList() {
		// Recycle the typed array
		navMenuIcons.recycle();
		// // setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(this, navDrawerItems);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		displayView(position);

	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// Refresh Drawer

		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:

			break;
		case 1:
			fragment = new AuctionsFragment();
			break;
		case 2:
			fragment = new StatisticsFragment();
		case 3:
			fragment = new ProfileFragment();
			break;
		case 4:
			// TODO Show Logout Dialog
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = (FragmentManager) getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			// ft.addToBackStack(fragment.getTag());
			ft.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
}
