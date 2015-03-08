package com.me.auction;

import com.me.auction.model.User;
import com.me.auction.presenters.UserPresenter;
import com.me.auction.utils.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterationActivity extends Activity {

	EditText etFirstName, etLastName, etDisplayName, etPassword,
			etConfirmPassword;
	String mUserFirstNameStr = "", mUserLastNameStr = "",
			mUserDisplayNameStr = "", mPasswordStr = "",
			mConfirmPasswordStr = "";
	User mNewUser;
	UserPresenter mUserPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeration);
		getActionBar().setTitle(R.string.title_activity_registeration);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		mUserPresenter = new UserPresenter(this);
		// Init Views
		etFirstName = (EditText) findViewById(R.id.etFirstName_Reg);
		etLastName = (EditText) findViewById(R.id.etLastName_Reg);
		etPassword = (EditText) findViewById(R.id.etPassword_Reg);
		etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword_Reg);
		etDisplayName = (EditText) findViewById(R.id.etDisplayName_Reg);
	}

	public void signin(View v) {
		startActivity(new Intent(this, LoginActivity.class));
		this.finish();
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

	public void signUp(View v) {
		mUserFirstNameStr = etFirstName.getText().toString();
		if (mUserFirstNameStr.trim().length() <= 0) {
			etFirstName.setError(getString(R.string.error_required_field));
			return;
		}
		mUserLastNameStr = etLastName.getText().toString();
		if (mUserLastNameStr.trim().length() <= 0) {
			etLastName.setError(getString(R.string.error_required_field));
			return;
		}
		mUserDisplayNameStr = etDisplayName.getText().toString();
		if (mUserDisplayNameStr.trim().length() <= 0) {
			etDisplayName.setError(getString(R.string.error_required_field));
			return;
		}
		mPasswordStr = etPassword.getText().toString();
		if (mPasswordStr.trim().length() <= 0) {
			etPassword.setError(getString(R.string.error_required_field));
			return;
		}
		mConfirmPasswordStr = etConfirmPassword.getText().toString();
		if (mConfirmPasswordStr.trim().length() <= 0) {
			etConfirmPassword
					.setError(getString(R.string.error_required_field));
			return;
		}

		if (!mPasswordStr.equals(mConfirmPasswordStr)) {
			etPassword.setError(getString(R.string.str_password_mismatch));
			etConfirmPassword
					.setError(getString(R.string.str_password_mismatch));
			return;
		}
		// Create User
		mNewUser = new User();
		mNewUser.setfName(mUserFirstNameStr);
		mNewUser.setlName(mUserLastNameStr);
		mNewUser.setDisplayName(mUserDisplayNameStr);
		mNewUser.setPassword(mPasswordStr);
		// Create New User
		Long id = mUserPresenter.register(mNewUser);
		if (id > 0) {// Say welcome
			Toast.makeText(
					this,
					getResources().getString(R.string.str_welcome) + " "
							+ mNewUser.getDisplayName(), Toast.LENGTH_SHORT)
					.show();
			// Save user credentials
			Utils.saveUserId(this, id);
			// Open main activity
			startMainActivity();
			// Close Welcome Activity
			setResult(RESULT_OK);
		} else {
			Toast.makeText(this, R.string.error_sign_up_failed,
					Toast.LENGTH_SHORT).show();
		}
	}

	public void cancelRegisteration(View v) {
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

	private void startMainActivity() {
		startActivity(new Intent(this, MainActivity.class));

	}

}