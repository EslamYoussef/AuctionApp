package com.me.auction.presenters;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.auction.datahelpers.UsersDataHelper;
import com.me.auction.model.User;
import com.me.auction.utils.Constants;

public class UserPresenter {
	UsersDataHelper mUsersDataHelper;
	Context mContext;

	public UserPresenter(Context context) {

		mContext = context;
		mUsersDataHelper = new UsersDataHelper(mContext);
	}

	public long register(User user) {
		return mUsersDataHelper.createUser(user);
	}

	public ArrayList<User> getAllUsers() {
		return mUsersDataHelper.getAllUsers();
	}

	public Integer getUsersCount() {

		return mUsersDataHelper.getUsersCount();
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
