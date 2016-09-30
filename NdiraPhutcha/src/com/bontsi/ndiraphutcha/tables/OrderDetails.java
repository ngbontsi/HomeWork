package com.bontsi.ndiraphutcha.tables;

import java.io.Serializable;

import android.content.ContentValues;

import com.bontsi.ndiraphutcha.dao.DBSchema;

public class OrderDetails implements Serializable {

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ordernumber;
	private int quantity;
	private int price;
	private int date;

	public OrderDetails() {
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_ORDER.QUANTITY, quantity);
		cv.put(DBSchema.TB_ORDER.PRICE, price);
		cv.put(DBSchema.TB_ORDER.DATE, date);
		return cv;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}
