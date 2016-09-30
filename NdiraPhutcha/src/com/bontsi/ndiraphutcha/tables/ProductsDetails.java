package com.bontsi.ndiraphutcha.tables;

import java.io.Serializable;

import android.content.ContentValues;

import com.bontsi.ndiraphutcha.dao.DBSchema;

public class ProductsDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productid;
	private String productname;
	private String desscription;
	private String sources;
	private int price;
	private int date;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductsDetails() {
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_PRODUCTS.PRODUCTNAME, productname);
		cv.put(DBSchema.TB_PRODUCTS.DESSCRIPTION, desscription);
		cv.put(DBSchema.TB_PRODUCTS.SOURCES, sources);
		cv.put(DBSchema.TB_PRODUCTS.PRICE, price);
		cv.put(DBSchema.TB_PRODUCTS.DATE, date);
		return cv;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDesscription() {
		return desscription;
	}

	public void setDesscription(String desscription) {
		this.desscription = desscription;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

}
