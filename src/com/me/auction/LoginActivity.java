package com.me.auction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.me.auction.model.User;
import com.me.auction.presenters.UserPresenter;
import com.me.auction.utils.Constants;
import com.me.auction.utils.Utils;

public class LoginActivity extends Activity {

	EditText etUserName, etPassword;
	String mUserNameStr = "", mPasswordStr = "";
	User mNewUser;
	UserPresenter mUserPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(R.string.title_activity_login);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

		etUserName = (EditText) findViewById(R.id.etUserName_Login);
		etPassword = (EditText) findViewById(R.id.etPassword_Login);
		mUserPresenter = new UserPresenter(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home: {
			finish();
			break;
		}
		}
		return true;
	}

	public void login(View v) {
		mUserNameStr = etUserName.getText().toString();
		if (mUserNameStr.trim().length() <= 0) {
			etUserName.setError(getString(R.string.error_required_field));
			return;
		}
		mPasswordStr = etPassword.getText().toString();
		if (mPasswordStr.trim().length() <= 0) {
			etPassword.setError(getString(R.string.error_required_field));
			return;
		}
		mNewUser = mUserPresenter.getUserForUserNameAndPassword(mUserNameStr,
				mPasswordStr);
		if (null != mNewUser && mNewUser.getId() > 0) {
			// Say welcome
			Toast.makeText(
					this,
					getResources().getString(R.string.str_welcome) + " "
							+ mNewUser.getDisplayName(), Toast.LENGTH_SHORT)
					.show();
			// Save user credentials
			Utils.saveUserId(this, mNewUser.getId());
			// Open main activity
			openMainActivity();
			// Close Welcome Activity
			setResult(RESULT_OK);
		} else {
			Toast.makeText(this, R.string.str_invalid_account,
					Toast.LENGTH_SHORT).show();
		}
	}

	public void cancelLogin(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.str_exit_title);
		builder.setMessage(R.string.str_are_you_sure);
		builder.setPositiveButton(R.string.str_confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						finish();
					}
				});
		builder.setNegativeButton(R.string.str_cancel, null);
		builder.create().show();

	}

	private void openMainActivity() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);

	}

	public void signup(View v) {
		Intent i = new Intent(this, RegisterationActivity.class);
		i.putExtra(Constants.KEY_MODE, Constants.EMAIL_REG_MODE);
		startActivity(i);
		this.finish();
	}
}
