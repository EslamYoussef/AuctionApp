package com.me.auction.model;

public class NavImageViewItem extends NavDrawerItem {
	private int mIconId;

	public int getIcon() {
		return mIconId;
	}

	public void setIcon(int mIcon) {
		this.mIconId = mIcon;
	}

	public NavImageViewItem(int iconId) {
		mIconId = iconId;
	}
}
