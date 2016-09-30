package com.bontsi.ndiraphutcha.tables;

import java.io.Serializable;

import android.content.ContentValues;

import com.bontsi.ndiraphutcha.dao.DBSchema;

public class CustomerDetails implements Serializable {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	private int phone;
	private int date;

	public CustomerDetails() {
	}

	public CustomerDetails(int id, String menuname, String menuType,
			String ingrediants, String image) {
		this.id = id;

	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_CUSTORMER.CUSTOMERNAME, name);
		cv.put(DBSchema.TB_CUSTORMER.CUSTOMERADDRESS, address);
		cv.put(DBSchema.TB_CUSTORMER.CUSTOMERNUMBER, phone);
		cv.put(DBSchema.TB_CUSTORMER.DATE, date);
		return cv;
	}

}
