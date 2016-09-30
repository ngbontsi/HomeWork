package com.bontsi.micproject.models;

import android.content.ContentValues;

import com.bontsi.micproject.data.DBSchema;

public class LoginDetails {

	private int id = -1;
	private String password;
	private String username;
	private String mDate;

	public LoginDetails() {
	}

	public LoginDetails(int id, String username, String password, String mDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mDate = mDate;
	}

	public LoginDetails(String username, String password, String mDate) {
		this.username = username;
		this.password = password;
		this.mDate = mDate;
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		if (id != -1) {
			cv.put(DBSchema.TB_LOGINDETAILS.LOGINID, id);
		}
		cv.put(DBSchema.TB_LOGINDETAILS.USERNAME, username);
		cv.put(DBSchema.TB_LOGINDETAILS.PASSWORDS, password);
		cv.put(DBSchema.TB_LOGINDETAILS.DATE, mDate);
		return cv;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(String mDate) {
		this.mDate = mDate;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return mDate;
	}

	public String getUserName() {
		return username;
	}
}
