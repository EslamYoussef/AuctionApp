package com.me.auction.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.me.auction.R;
import com.me.auction.listeners.IBidValueListener;

public class AddBidFragmentDialog extends DialogFragment implements
		OnClickListener {

	EditText etBidValue;
	IBidValueListener mListener;
	Button bConfirm, bCancel;
	Dialog dialog;

	public void setBidListener(IBidValueListener listener) {
		mListener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		dialog = new Dialog(mListener.getCOntext());
		dialog.setContentView(R.layout.fragment_add_bid);
		dialog.setTitle("Add Bid");
		etBidValue = (EditText) dialog.findViewById(R.id.etBidValue);
		bCancel = (Button) dialog.findViewById(R.id.bCancel);
		bConfirm = (Button) dialog.findViewById(R.id.bConfirm);
		bCancel.setOnClickListener(this);
		bConfirm.setOnClickListener(this);
		dialog.show();
		return dialog;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bConfirm:
			String bidValue = etBidValue.getText().toString();
			if (bidValue.trim().length() > 0) {
				mListener.setBidValue(Integer.parseInt(bidValue));
				this.dismiss();
			} else
				Toast.makeText(mListener.getCOntext(),
						R.string.str_invalid_value, Toast.LENGTH_SHORT).show();
			break;
		case R.id.bCancel:

			this.dismiss();
			break;
		default:
			break;
		}

	}
}