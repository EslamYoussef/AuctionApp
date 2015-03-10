package com.me.auction.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.auction.R;
import com.me.auction.cells.IconTextCell;
import com.me.auction.cells.ImageCell;
import com.me.auction.model.NavDrawerItem;
import com.me.auction.model.NavImageViewItem;
import com.me.auction.model.NavTextIconItem;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<NavDrawerItem> navDrawerItems;

	public NavDrawerListAdapter(Context context,
			ArrayList<NavDrawerItem> navDrawerItems) {
		this.mContext = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView)
			convertView = ((Activity) (mContext)).getLayoutInflater().inflate(
					R.layout.cell_icon_text, parent, false);
		IconTextCell iconTextCell = new IconTextCell();
		iconTextCell.setIvImage((ImageView) convertView
				.findViewById(R.id.ivImage));
		iconTextCell
				.setTvText((TextView) convertView.findViewById(R.id.tvText));
		//
		NavTextIconItem item = (NavTextIconItem) navDrawerItems.get(position);
		iconTextCell.getIvImage().setImageResource(item.getIcon());
		iconTextCell.getTvText().setText(item.getTitle());

		return convertView;
	}

}
