package com.bontsi.ndiraphutcha.order.presenter;

import java.util.List;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bontsi.ndiraphutcha.tables.ProductsDetails;

/**
 * Created by kaede on 2015/10/12.
 */
public interface IOrderPresenter {
	void clear();

	ProductsDetails getProducts(String selected);

	List<String> getStocks();

	void doMakeAnOrder(Spinner product, Spinner source, EditText quantity,
			TextView price);

	void setProgressBarVisiblity(int visiblity);

	void toast(String msg);

	void onDestroy();
}
