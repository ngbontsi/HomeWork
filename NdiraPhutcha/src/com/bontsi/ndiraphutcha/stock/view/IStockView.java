package com.bontsi.ndiraphutcha.stock.view;

import java.util.List;

import android.app.Activity;

import com.bontsi.ndiraphutcha.tables.StockDetails;

/**
 * Created by kaede on 2015/5/19.
 */
public interface IStockView {

	public void onGetList(List<StockDetails> list);

	public void onEditStock(StockDetails userDetails);

	public void toast(String msg);

	public Activity onGetActivity();

	public void onShowFooterView(Boolean isShow);

	public void onDeletMenu(boolean isSucces);
}
