package com.bontsi.nbdevelopment.ourwork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bontsi.nbdevelopment.R;

public class OurWorkFragment extends Fragment {

	public static Fragment newInstance(OurWorkActivity context, int pos,
			float scale) {
		final Bundle b = new Bundle();
		b.putInt("pos", pos);
		b.putFloat("scale", scale);

		return Fragment
				.instantiate(context, OurWorkFragment.class.getName(), b);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				400, 400);
		final LinearLayout fragmentLL = (LinearLayout) inflater.inflate(
				R.layout.mf, container, false);
		final int pos = getArguments().getInt("pos");
		final TextView tv = (TextView) fragmentLL.findViewById(R.id.imagetxt);

		tv.setText("Company Name " + pos);

		final ImageView iv = (ImageView) fragmentLL.findViewById(R.id.pagerImg);

		iv.setLayoutParams(layoutParams);
		iv.setImageResource(OurWorkActivity.mainActivityCtx.coverUrl[pos]);
		iv.setPadding(15, 15, 15, 15);

		final MyLinearLayout root = (MyLinearLayout) fragmentLL
				.findViewById(R.id.root);
		final float scale = getArguments().getFloat("scale");
		root.setScaleBoth(scale);

		return fragmentLL;
	}

}
