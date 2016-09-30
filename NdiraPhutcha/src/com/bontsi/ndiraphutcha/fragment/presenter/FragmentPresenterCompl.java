package com.bontsi.ndiraphutcha.fragment.presenter;

import java.util.List;

import com.bontsi.ndiraphutcha.fragment.view.IFragmentView;
import com.bontsi.ndiraphutcha.tables.OrderDetails;

/**
 * Created by kaede on 2015/5/19.
 */
public class FragmentPresenterCompl implements IFragmentPresenter {
	IFragmentView iFragmentView;

	public FragmentPresenterCompl(IFragmentView iFragmentView) {
		this.iFragmentView = iFragmentView;
	}

	@Override
	public void onItemClick(int position) {
		iFragmentView.onItemClick(position);
	}

	@Override
	public List<OrderDetails> loadOders(String screenName) {
		// TODO Auto-generated method stub
		iFragmentView.onLoadOder(screenName);
		return null;
	}
}
