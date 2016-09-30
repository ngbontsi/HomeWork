package com.bontsi.ndiraphutcha.fragment.presenter;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.bontsi.ndiraphutcha.dao.OrderDAO;
import com.bontsi.ndiraphutcha.fragment.event.FragmentGetDatasEvent;
import com.bontsi.ndiraphutcha.tables.OrderDetails;

import de.greenrobot.event.EventBus;

/**
 * Created by kaede on 2015/5/19.
 */
public class ActivityPresenterCompl implements IActivityPresenter {
	Context context;
	private List<OrderDetails> orderDetails;
	private OrderDAO orderDAO;

	public ActivityPresenterCompl(Context context) {
		this.context = context;
	}

	@Override
	public void loadDatas() {

		orderDetails = getOrderDAO().getAllUserDetails();

		final Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				final FragmentGetDatasEvent getDatasEvent = new FragmentGetDatasEvent(
						orderDetails);
				EventBus.getDefault().post(getDatasEvent);
			}
		}, 1000);
	}

	private OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO(context);
		}
		return orderDAO;
	}
}
