package com.me.auction.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.auction.R;
import com.me.auction.model.User;
import com.me.auction.utils.Utils;

public class ProfileFragment extends Fragment {

	User mUser;
	TextView tvDisplayName, tvFirstName, tvLastName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profile, container,
				false);
		tvDisplayName = (TextView) rootView.findViewById(R.id.tvDisplayName);
		tvFirstName = (TextView) rootView.findViewById(R.id.tvFirstName);
		tvLastName = (TextView) rootView.findViewById(R.id.tvLastName);
		// get current user
		mUser = Utils.getCurrentUser(getActivity());
		if (null != mUser) {
			tvDisplayName.setText(mUser.getDisplayName());
			tvFirstName.setText(mUser.getfName());
			tvLastName.setText(mUser.getlName());
		}
		return rootView;
	}
}