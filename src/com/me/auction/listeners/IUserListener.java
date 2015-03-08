package com.me.auction.listeners;

import android.content.Context;

public interface IUserListener {

	Context getContext();

	void didRigesteredSuccessfully(Long userId);

	void didUpdatedUserSuccessfully();

	void didRegisteredFailure(Exception ex);

	void didUpdatedUserFailure(Exception ex);

}
