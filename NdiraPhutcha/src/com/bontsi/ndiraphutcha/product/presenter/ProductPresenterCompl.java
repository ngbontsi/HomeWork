package com.bontsi.ndiraphutcha.product.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseBooleanArray;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.dao.ProductDAO;
import com.bontsi.ndiraphutcha.product.view.IProductView;
import com.bontsi.ndiraphutcha.stock.module.StockModule;
import com.bontsi.ndiraphutcha.tables.ProductsDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;
import com.bontsi.ngdevelopmentframework.utils.StringUtils;

/**
 * Created by kaede on 2015/5/18.
 */
public class ProductPresenterCompl implements IProductPresenter {
	private IProductView iLoginView;
	private Handler handler;
	private ProductDAO productDAO;
	private StockModule stockModule;
	private final Context context;
	private boolean isLoginSuccess = true;
	private int code;

	public ProductPresenterCompl(IProductView iLoginView, Context context) {
		this.iLoginView = iLoginView;
		this.context = context;
	}

	@Override
	public void clear() {
		iLoginView.onClearText();
	}

	@Override
	public void doProduct(EditText productName, EditText description,
			ListView sourceSpinner, EditText price) {
		final String tempproductName = productName.getText().toString();
		final String tempdescription = description.getText().toString();
		final String tempprice = price.getText().toString();

		final SparseBooleanArray checked = sourceSpinner
				.getCheckedItemPositions();

		final StringBuilder csvList = new StringBuilder();

		try {
			if (StringUtils.nullOrEmpty(tempproductName)) {
				productName.setError("Can not be null");
				isLoginSuccess = false;
			}
			if (StringUtils.nullOrEmpty(tempdescription) && isLoginSuccess) {
				description.setError("Can not be null");
				isLoginSuccess = false;
			}
			if (StringUtils.nullOrEmpty(tempprice) && isLoginSuccess) {
				price.setError("Can not be null");
				isLoginSuccess = false;
			}

			if (checked.size() <= 0 && isLoginSuccess) {

				isLoginSuccess = false;
			} else {
				boolean first = true;
				for (int i = 0; i < checked.size(); i++) {
					final String value = (String) sourceSpinner
							.getItemAtPosition(checked.keyAt(i));
					if (first) {
						csvList.append(value);
						first = false;
					} else {
						csvList.append(",");
						csvList.append(value);
					}

				}

			}

			// create new user Details
			if (isLoginSuccess) {

				ProductsDetails existingData = getProductDAO()
						.getProductDetail(tempproductName);
				// create new login data for user
				if (existingData == null) {
					existingData = new ProductsDetails();
					existingData.setProductname(tempproductName);
					existingData.setDesscription(tempdescription);
					existingData.setSources(csvList.toString());
					existingData.setPrice(Integer.parseInt(tempprice));
					existingData.setDate(DateUtils.getDate());
					getProductDAO().insertMenuTypeData(existingData);
					toast(ApplicationConstants.Data_insert_message.getValue());
				} else {

					existingData.setProductname(tempproductName);
					existingData.setDesscription(tempdescription);
					existingData.setSources(csvList.toString());
					existingData.setPrice(Integer.parseInt(tempprice));
					existingData.setDate(DateUtils.getDate());
					getProductDAO().updateMenuTypeData(existingData);
					toast(ApplicationConstants.Data_update_message.getValue());
				}
				getStockModule().updateStcok(existingData);

			}

		} catch (final Exception e) {

			toast(ApplicationConstants.Application_failure_message.getValue()
					+ " an Error " + e.getMessage());
			isLoginSuccess = true;
		}

		getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (iLoginView != null) {
					iLoginView.onAddProductResult(isLoginSuccess, code);
				}
			}
		}, 5000);

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

	private ProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new ProductDAO(context);
		}
		return productDAO;
	}

	private StockModule getStockModule() {
		if (stockModule == null) {
			stockModule = new StockModule(context);
		}
		return stockModule;
	}

	@Override
	public void toast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

	}

}
