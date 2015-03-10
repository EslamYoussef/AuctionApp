package com.me.auction;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
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
import com.me.auction.utils.Utils;

public class MainActivity extends ActionBarActivity implements
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
		mActionBar = getSupportActionBar();

		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
		displayView(0);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		return mDrawerToggle.onOptionsItemSelected(item);
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
			fragment = new AuctionsFragment();
			break;
		case 1:
			fragment = new StatisticsFragment();
			break;
		case 2:
			fragment = new ProfileFragment();
			break;
		case 3:
			showLogoutDialog();

			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = (FragmentManager) getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();

			ft.replace(R.id.frame_container, fragment)
					.commitAllowingStateLoss();
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

	@Override
	protected void onResume() {
		mDrawerLayout.invalidate();
		displayView(0);
		super.onResume();

	}

	private void showLogoutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.str_logout);
		builder.setMessage(getResources().getString(R.string.str_are_you_sure));
		builder.setPositiveButton(R.string.str_confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Utils.saveUserId(MainActivity.this, (long) 0);

				// Start Welcome activity
				startActivity(new Intent(MainActivity.this,
						WelcomeActivity.class));
				//
				MainActivity.this.finish();

			}
		});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.setCancelable(true);
		builder.create().show();
	}
}
