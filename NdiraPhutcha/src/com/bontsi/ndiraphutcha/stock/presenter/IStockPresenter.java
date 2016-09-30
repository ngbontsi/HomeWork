package com.bontsi.ndiraphutcha.stock.presenter;

import android.app.Activity;

/**
 * Created by kaede on 2015/5/19.
 */
public interface IStockPresenter {
	public void loadMenus();

	public void updateStock(String name);

	public Activity getActivity();

	public void showFooterView(Boolean isShow);

	public void delete(String position);
}
