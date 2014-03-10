package com.dslplatform.examples.android.utilities;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ListView;

import com.dslplatform.examples.android.R;

public class ListDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final List<String> listEmails = getArguments().getStringArrayList(
				"reviewed");
		final List<String> listComments = getArguments().getStringArrayList(
				"comments");
		final List<Integer> listImages = getArguments().getIntegerArrayList(
				"images");

		final ReviewArrayAdapter adapter = new ReviewArrayAdapter(
				getActivity(), R.layout.review_layout, listImages, listEmails,
				listComments);

		final ListView listView = new ListView(getActivity());
		listView.setAdapter(adapter);

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		builder.setTitle(getArguments().getString("title")).setView(listView)
				.setNegativeButton(R.string.close, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		return builder.create();
	}
}
