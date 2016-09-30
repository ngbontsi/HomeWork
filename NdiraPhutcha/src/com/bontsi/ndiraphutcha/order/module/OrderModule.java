package com.bontsi.ndiraphutcha.order.module;

import android.content.Context;

import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.dao.OrderDAO;
import com.bontsi.ndiraphutcha.dao.OrderHearedsDAO;
import com.bontsi.ndiraphutcha.tables.OrderDetails;
import com.bontsi.ndiraphutcha.tables.OrderHeaderDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;

public class OrderModule {
	private Context context;
	private OrderDAO orderDAO;
	private OrderHearedsDAO orderHearedsDAO;

	public OrderModule() {

	}

	public OrderModule(Context context) {
		this.context = context;
	}

	public void createOrderHeader(OrderDetails orderDetails) {

		if (orderDetails != null) {
			final OrderHeaderDetails headerDetails = new OrderHeaderDetails();
			headerDetails.setOrdernumber(orderDetails.getOrdernumber());
			headerDetails.setDeleiverdate(0);
			headerDetails.setOredereddate(DateUtils.getDate());
			headerDetails.setTotalprice(orderDetails.getPrice());
			headerDetails.setOrderestatus(ApplicationConstants.NewStatus
					.getValue());
			headerDetails.setDate(DateUtils.getDate());
			headerDetails.setCustomerid(12);
			getOrderHearedsDAO().insertUserDetails(headerDetails);
		}
	}

	public String getStatusDesc(int ornumber) {

		if (ornumber > 0) {
			final OrderHeaderDetails headerDetails = getOrderHearedsDAO()
					.getOrderHeaderDetails(ornumber);
			if (headerDetails == null) {
				return ApplicationConstants.UnKnownStatus.getValue();
			} else {
				return headerDetails.getOrderestatus();
			}

		}

		return ApplicationConstants.UnKnownStatus.getValue();

	}

	private OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO(context);
		}
		return orderDAO;
	}

	private OrderHearedsDAO getOrderHearedsDAO() {
		if (orderHearedsDAO == null) {
			orderHearedsDAO = new OrderHearedsDAO(context);
		}
		return orderHearedsDAO;
	}
}
