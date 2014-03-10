package com.dslplatform.examples.android.utilities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dslplatform.examples.android.R;

public class ReviewArrayAdapter extends ArrayAdapter<String> {

	private List<Integer> images;
	private List<String> reviewedBy;
	private List<String> comments;

	public ReviewArrayAdapter(Activity context, int resource,
			List<Integer> images, List<String> reviewed, List<String> comments) {
		super(context, resource, comments);
		this.images = images;
		this.reviewedBy = reviewed;
		this.comments = comments;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View v = view;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.review_layout, null);
		}

		ImageView rating = (ImageView) v.findViewById(R.id.image_review);
		TextView email = (TextView) v.findViewById(R.id.text_email_review);
		TextView comment = (TextView) v.findViewById(R.id.text_comment_review);

		switch (images.get(position)) {
		case 1:
			rating.setImageResource(R.drawable.one_star);
			break;
		case 2:
			rating.setImageResource(R.drawable.two_star);
			break;
		case 3:
			rating.setImageResource(R.drawable.three_star);
			break;
		case 4:
			rating.setImageResource(R.drawable.four_star);
			break;
		case 5:
			rating.setImageResource(R.drawable.five_star);
			break;
		default:
			rating.setImageResource(R.drawable.five_star);
			break;
		}
		email.setText("by " + reviewedBy.get(position));
		comment.setText(comments.get(position));

		return v;
	}

}
