package com.bontsi.ndiraphutcha.order.presenter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.dao.OrderDAO;
import com.bontsi.ndiraphutcha.dao.ProductDAO;
import com.bontsi.ndiraphutcha.dao.StockDAO;
import com.bontsi.ndiraphutcha.order.module.OrderModule;
import com.bontsi.ndiraphutcha.order.view.IOrderView;
import com.bontsi.ndiraphutcha.tables.OrderDetails;
import com.bontsi.ndiraphutcha.tables.ProductsDetails;
import com.bontsi.ndiraphutcha.tables.StockDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;

/**
 * Created by kaede on 2015/5/18.
 */
public class OrderPresenterCompl implements IOrderPresenter {
	private IOrderView iLoginView;
	private Handler handler;
	private ProductDAO productDAO;
	private StockDAO stockDAO;
	private OrderDAO orderDAO;
	private OrderModule orderModule;
	private final Context context;
	private boolean isLoginSuccess = true;
	private int code;

	public OrderPresenterCompl(IOrderView iLoginView, Context context) {
		this.iLoginView = iLoginView;
		this.context = context;
	}

	@Override
	public void clear() {
		iLoginView.onClearText();
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

	@Override
	public void toast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

	}

	@Override
	public ProductsDetails getProducts(String name) {
		// TODO Auto-generated method stub
		return getProductDAO().getProductDetail(name);
	}

	@Override
	public List<String> getStocks() {
		// TODO Auto-generated method stub
		final List<String> outcome = new ArrayList<String>();
		final List<StockDetails> list = getStockDAO().getAllStocks();
		if (list != null && !list.isEmpty()) {
			for (final StockDetails stockDetails : list) {
				outcome.add(stockDetails.getProductname());
			}
		}
		return outcome;
	}

	@Override
	public void doMakeAnOrder(Spinner product, Spinner source,
			EditText quantity, TextView price) {
		final String temprod = product.getSelectedItem().toString();
		final String temsour = source.getSelectedItem().toString();
		final String temquant = quantity.getText().toString();
		final String temprice = price.getText().toString();

		if (TextUtils.isEmpty(temprod)) {
			final TextView errorText = (TextView) product.getSelectedView();
			errorText.setError("");
			errorText.setTextColor(Color.RED);// just to highlight that this is
			errorText.setText("my actual error text");
			isLoginSuccess = false;

		}
		if (TextUtils.isEmpty(temsour) && isLoginSuccess) {
			final TextView errorText = (TextView) source.getSelectedView();
			errorText.setError("");
			errorText.setTextColor(Color.RED);// just to highlight that this is
			errorText.setText("my actual error text");
			isLoginSuccess = false;

		}
		if (TextUtils.isEmpty(temquant) && isLoginSuccess) {
			quantity.setError("Select");
			isLoginSuccess = false;

		}
		if (TextUtils.isEmpty(temprice) && isLoginSuccess) {
			price.setError("Select");
			isLoginSuccess = false;

		}

		final OrderDetails newOrder = new OrderDetails();
		if (isLoginSuccess) {

			newOrder.setQuantity(Integer.parseInt(temquant));
			newOrder.setPrice(Integer.parseInt(temprice));
			newOrder.setDate(DateUtils.getDate());
			getOrderDAO().insertUserDetails(newOrder);
		} else {
			toast(ApplicationConstants.Application_failure_message.getValue());
		}
		getOrderModule().createOrderHeader(newOrder);
		iLoginView.onMakeAnOrderResult(isLoginSuccess, code);

	}

	private ProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new ProductDAO(context);
		}
		return productDAO;
	}

	private OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO(context);
		}
		return orderDAO;
	}

	private StockDAO getStockDAO() {
		if (stockDAO == null) {
			stockDAO = new StockDAO(context);
		}
		return stockDAO;
	}

	private OrderModule getOrderModule() {
		if (orderModule == null) {
			orderModule = new OrderModule(context);
		}
		return orderModule;
	}

}
