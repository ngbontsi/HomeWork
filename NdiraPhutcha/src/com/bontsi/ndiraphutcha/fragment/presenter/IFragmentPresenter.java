package com.bontsi.ndiraphutcha.fragment.presenter;

import java.util.List;

import com.bontsi.ndiraphutcha.tables.OrderDetails;

/**
 * Created by kaede on 2015/5/19.
 */
public interface IFragmentPresenter {
	public List<OrderDetails> loadOders(String screenName);

	public void onItemClick(int position);
}
