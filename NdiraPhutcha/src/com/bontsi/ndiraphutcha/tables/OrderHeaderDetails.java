package com.bontsi.ndiraphutcha.tables;

import java.io.Serializable;

import android.content.ContentValues;

import com.bontsi.ndiraphutcha.dao.DBSchema;

public class OrderHeaderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orederid;
	private int ordernumber;
	private int customerid;
	private int oredereddate;
	private String orderestatus;
	private int deleiverdate;
	private int totalprice;
	private int date;

	public OrderHeaderDetails() {
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_ORDERHEADERS.CUSTOMERID, customerid);
		cv.put(DBSchema.TB_ORDERHEADERS.ORDERNUMBER, ordernumber);
		cv.put(DBSchema.TB_ORDERHEADERS.OREDEREDDATE, oredereddate);
		cv.put(DBSchema.TB_ORDERHEADERS.ORDERESTATUS, orderestatus);
		cv.put(DBSchema.TB_ORDERHEADERS.DELEIVERDATE, deleiverdate);
		cv.put(DBSchema.TB_ORDERHEADERS.TOTALPRICE, totalprice);
		cv.put(DBSchema.TB_ORDERHEADERS.DATE, date);
		return cv;
	}

	public int getOrederid() {
		return orederid;
	}

	public void setOrederid(int orederid) {
		this.orederid = orederid;
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getOredereddate() {
		return oredereddate;
	}

	public void setOredereddate(int oredereddate) {
		this.oredereddate = oredereddate;
	}

	public String getOrderestatus() {
		return orderestatus;
	}

	public void setOrderestatus(String orderestatus) {
		this.orderestatus = orderestatus;
	}

	public int getDeleiverdate() {
		return deleiverdate;
	}

	public void setDeleiverdate(int deleiverdate) {
		this.deleiverdate = deleiverdate;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}
