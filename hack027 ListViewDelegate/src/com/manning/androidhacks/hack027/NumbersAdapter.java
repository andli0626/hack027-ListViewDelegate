package com.manning.androidhacks.hack027;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NumbersAdapter extends ArrayAdapter<Integer> {

	// 开放外部接口：Delegate
	public static interface NumbersAdapterDelegate {
		// 删除指定项
		void removeItem(Integer value);
	}

	private LayoutInflater 			mInflator;
	private NumbersAdapterDelegate 	mDelegate;

	public NumbersAdapter(Context context, List<Integer> objects) {
		super(context, 0, objects);
		mInflator = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View cv, ViewGroup parent) {

		if (null == cv) {
			cv = mInflator.inflate(R.layout.number_row, parent, false);
		}

		final Integer value = getItem(position);
		TextView tv 		= (TextView) cv.findViewById(R.id.numbers_row_text);
		tv.setText(value.toString());

		View button = cv.findViewById(R.id.numbers_row_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != mDelegate) {
					mDelegate.removeItem(value);
				}
			}
		});

		return cv;
	}

	// 设置Delegate
	public void setDelegate(NumbersAdapterDelegate delegate) {
		mDelegate = delegate;
	}

}
