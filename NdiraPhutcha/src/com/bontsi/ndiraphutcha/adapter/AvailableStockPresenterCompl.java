package com.bontsi.ndiraphutcha.adapter;

import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import com.android.volley.toolbox.ImageLoader;
import com.bontsi.ndiraphutcha.dao.StockDAO;
import com.bontsi.ndiraphutcha.stock.presenter.IStockPresenter;
import com.bontsi.ndiraphutcha.stock.view.IStockView;
import com.bontsi.ndiraphutcha.tables.StockDetails;

public class AvailableStockPresenterCompl implements IStockPresenter {

	private final Activity activity;
	private LayoutInflater inflater;
	private final IStockView iAdapterView;
	private List<StockDetails> user;
	private StockDAO stockDAO;
	private Handler handler;
	private boolean isSucces;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public AvailableStockPresenterCompl(IStockView iAdapterView, List<StockDetails> list) {
		this.iAdapterView = iAdapterView;
		activity = iAdapterView.onGetActivity();
		user = list;

	}

	@Override
	public void loadMenus() {

		user = getStockDAO().getAllStocks();
		getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				iAdapterView.onGetList(user);
			}
		}, 2000);

	}

	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return activity;
	}

	@Override
	public void showFooterView(Boolean isShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStock(String name) {

		final StockDetails data = getStockDAO().getStockDetails(name);
		getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				iAdapterView.onEditStock(data);
			}
		}, 2000);

	}

	@Override
	public void delete(String position) {
		final StockDetails data = getStockDAO().getStockDetails(position);
		if (data != null) {
			getStockDAO().delete(data);
			isSucces = true;
		}

		getHandler().postDelayed(new Runnable() {

			@Override
			public void run() {

				iAdapterView.onDeletMenu(isSucces);
			}
		}, 2000);

	}

	private Handler getHandler() {
		if (handler == null) {
			handler = new Handler(Looper.getMainLooper());
		}
		return handler;
	}

	private StockDAO getStockDAO() {
		if (stockDAO == null) {
			stockDAO = new StockDAO(iAdapterView.onGetActivity()
					.getApplicationContext());
		}
		return stockDAO;
	}
}
