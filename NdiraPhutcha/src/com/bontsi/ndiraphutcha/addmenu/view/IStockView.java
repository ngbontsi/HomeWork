package com.bontsi.ndiraphutcha.addmenu.view;

import com.bontsi.ndiraphutcha.tables.StockDetails;

/**
 * Created by kaede on 2015/5/18.
 */
public interface IStockView {
	public void onClearText();

	public void onAddMenuResult(Boolean result, int code);

	public void onSetProgressBarVisibility(int visibility);

	public void onLoadStock(StockDetails data);

	public void toast(String msg);
}
