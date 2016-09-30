package com.bontsi.ndiraphutcha.tables;

import java.io.Serializable;

import android.content.ContentValues;

import com.bontsi.ndiraphutcha.dao.DBSchema;

public class StockDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stockid;
	private String productname;
	private int remainigstock;
	private int usedstock;
	private int date;

	public StockDetails() {
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_STOCK.PRODUCTNAME, productname);
		cv.put(DBSchema.TB_STOCK.REMAINIGSTOCK, remainigstock);
		cv.put(DBSchema.TB_STOCK.USEDSTOCK, usedstock);
		cv.put(DBSchema.TB_STOCK.DATE, date);
		return cv;
	}

	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getRemainigstock() {
		return remainigstock;
	}

	public void setRemainigstock(int remainigstock) {
		this.remainigstock = remainigstock;
	}

	public int getUsedstock() {
		return usedstock;
	}

	public void setUsedstock(int usedstock) {
		this.usedstock = usedstock;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

}
