package com.me.auction.fragments;

import com.me.auction.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class WonAuctionsFragment extends Fragment {

	ImageView ivImage;
	Drawable mImageResource;

	public void setImage(Drawable imageRes) {
		this.mImageResource = imageRes;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_image, container,
				false);
		ivImage = (ImageView) rootView.findViewById(R.id.ivImage);
		if (null != mImageResource)
			ivImage.setImageDrawable(mImageResource);
		return rootView;
	}
}