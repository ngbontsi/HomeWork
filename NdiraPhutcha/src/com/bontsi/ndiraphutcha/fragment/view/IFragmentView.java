package com.bontsi.ndiraphutcha.fragment.view;

import android.app.Activity;

/**
 * Created by kaede on 2015/5/19.
 */
public interface IFragmentView {
	public Activity getActivity();

	public void onLoadOder(String screenName);

	public void onItemClick(int position);
}
