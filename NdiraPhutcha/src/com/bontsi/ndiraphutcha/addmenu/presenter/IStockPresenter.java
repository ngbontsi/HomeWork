package com.bontsi.ndiraphutcha.addmenu.presenter;

import java.util.List;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bontsi.ndiraphutcha.tables.StockDetails;

/**
 * Created by kaede on 2015/10/12.
 */
public interface IStockPresenter {
	void clear();

	void populateStock(StockDetails name);

	List<String> getStockList();

	void doAddStock(Spinner productName, TextView remaining_stock,
			TextView used_stock, EditText inCommingStock);

	void setProgressBarVisiblity(int visiblity);

	void toast(String msg);

	void onDestroy();
}
