package com.bontsi.ndiraphutcha.product.presenter;

import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by kaede on 2015/10/12.
 */
public interface IProductPresenter {
	void clear();

	void doProduct(EditText menuType, EditText description, ListView selected,
			EditText price);

	void setProgressBarVisiblity(int visiblity);

	void toast(String msg);

	void onDestroy();
}
