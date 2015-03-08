package com.me.auction.presenters;

import android.content.Context;

import com.me.auction.datahelpers.UsersDataHelper;
import com.me.auction.model.User;

public class UserPresenter {
	UsersDataHelper mUsersDataHelper;
	Context mContext;

	public UserPresenter(Context context) {
		mContext = context;
	}

	public long register(User user) {
		return mUsersDataHelper.createUser(user);
	}

	public User getUserWithId(Long userId) {
		return mUsersDataHelper.getUserWithId(userId);
	}

	public User getUserForUserNameAndPassword(String userName, String password) {
		return mUsersDataHelper.getUserForUserNameAndPassword(userName,
				password);
	}

	public boolean editUser(User user) {

		int affectedRows = mUsersDataHelper.updateUser(user);
		if (affectedRows > 0) {
			return true;
		} else {
			return false;
		}
	}

}
