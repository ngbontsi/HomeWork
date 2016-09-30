package com.bontsi.ndiraphutcha.stock.module;

import android.content.Context;
import android.text.TextUtils;

import com.bontsi.ndiraphutcha.dao.StockDAO;
import com.bontsi.ndiraphutcha.tables.ProductsDetails;
import com.bontsi.ndiraphutcha.tables.StockDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;

public class StockModule {

	private Context context;
	private StockDAO stockDAO;

	public StockModule() {

	}

	public StockModule(Context context) {
		this.context = context;
	}

	public void updateStcok(ProductsDetails productsDetails) {

		if (productsDetails == null) {
			return;
		}
		if (productsDetails.getProductname() == null
				|| TextUtils.isEmpty(productsDetails.getProductname())) {
			return;
		}
		StockDetails existingData = getStockDAO().getStockDetails(
				productsDetails.getProductname());
		if (existingData == null) {
			existingData = new StockDetails();
			existingData.setProductname(productsDetails.getProductname());
			existingData.setUsedstock(0);
			existingData.setRemainigstock(0);
			existingData.setDate(DateUtils.getDate());
			getStockDAO().insertStockDetail(existingData);
		}

	}

	private StockDAO getStockDAO() {
		if (stockDAO == null) {
			stockDAO = new StockDAO(context);
		}
		return stockDAO;
	}
}
