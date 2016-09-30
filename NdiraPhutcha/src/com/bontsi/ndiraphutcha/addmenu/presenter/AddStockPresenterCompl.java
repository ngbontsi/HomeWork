package com.bontsi.ndiraphutcha.addmenu.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.addmenu.view.IStockView;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.dao.StockDAO;
import com.bontsi.ndiraphutcha.tables.StockDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;
import com.bontsi.ngdevelopmentframework.utils.StringUtils;

/**
 * Created by kaede on 2015/5/18.
 */
public class AddStockPresenterCompl implements IStockPresenter {
	private IStockView iLoginView;
	private Handler handler;
	private StockDAO stockDAO;
	private final Context context;
	private boolean isLoginSuccess = true;
	private StockDetails userDetailsFromScreen;
	private int code;

	public AddStockPresenterCompl(IStockView iLoginView, Context context,
			StockDetails userDetailsFromScreen) {
		this.iLoginView = iLoginView;
		this.context = context;
		this.userDetailsFromScreen = userDetailsFromScreen;
	}

	@Override
	public void clear() {
		iLoginView.onClearText();
	}

	@Override
	public void doAddStock(Spinner productName, TextView remaningStock,
			TextView usedStock, EditText incommingTotal) {

		final String tempProd = productName.getSelectedItem().toString();
		final String tempRemStock = remaningStock.getText().toString();
		final String tempincomi = incommingTotal.getText().toString();

		if (StringUtils.nullOrEmpty(tempRemStock)) {
			remaningStock.setError("Can noot be null");
			isLoginSuccess = false;
		}
		if (StringUtils.nullOrEmpty(tempincomi)) {
			incommingTotal.setError("Can noot be null");
			isLoginSuccess = false;
		}

		// create new user Details
		try {

			if (isLoginSuccess) {

				StockDetails existingData = getStockDAO().getStockDetails(
						tempProd);
				if (existingData == null) {
					existingData = new StockDetails();
					existingData.setProductname(tempProd);
					existingData.setRemainigstock(Integer.parseInt(tempincomi));
					existingData.setUsedstock(0);
					existingData.setDate(DateUtils.getDate());
					getStockDAO().insertStockDetail(existingData);
					toast(ApplicationConstants.Data_insert_message.getValue());
				} else {

					existingData = new StockDetails();
					existingData.setProductname(tempProd);
					existingData.setRemainigstock(getTotal(tempRemStock,
							tempincomi));
					existingData.setUsedstock(0);
					existingData.setDate(DateUtils.getDate());
					getStockDAO().updateStockDetails(existingData);
					toast(ApplicationConstants.Data_insert_message.getValue());

				}
				userDetailsFromScreen = existingData;

			}

		} catch (final Exception e) {
			toast(ApplicationConstants.Application_failure_message.getValue()
					+ "this is your error " + e.getMessage());
			isLoginSuccess = true;
		}

		getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (iLoginView != null) {
					iLoginView.onAddMenuResult(isLoginSuccess, code);
				}
			}
		}, 5000);

	}

	private int getTotal(String tempRemStock, String tempincomi) {
		// TODO Auto-generated method stub
		final int rem = Integer.parseInt(tempRemStock);
		final int incom = Integer.parseInt(tempincomi);

		return rem + incom;
	}

	@Override
	public void setProgressBarVisiblity(int visiblity) {
		iLoginView.onSetProgressBarVisibility(visiblity);
	}

	@Override
	public void onDestroy() {
		iLoginView = null;
	}

	private Handler getHandler() {
		if (handler == null) {
			handler = new Handler(Looper.getMainLooper());
		}
		return handler;
	}

	private StockDAO getStockDAO() {
		if (stockDAO == null) {
			stockDAO = new StockDAO(context);
		}
		return stockDAO;
	}

	@Override
	public void populateStock(final StockDetails data) {
		if (data != null) {
			userDetailsFromScreen = data;
		}
		getHandler().postDelayed(new Runnable() {

			@Override
			public void run() {
				iLoginView.onLoadStock(userDetailsFromScreen);

			}
		}, 2000);

	}

	@Override
	public void toast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

	}

	@Override
	public List<String> getStockList() {
		final List<String> outcome = new ArrayList<String>();
		final List<StockDetails> list = getStockDAO().getAllStocks();
		if (list != null && !list.isEmpty()) {
			for (final StockDetails stockDetail : list) {
				outcome.add(stockDetail.getProductname());
			}
		}
		return outcome;
	}
}
