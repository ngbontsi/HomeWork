package com.bontsi.micproject.models;

import java.math.BigDecimal;

import android.content.ContentValues;

import com.bontsi.micproject.data.DBSchema;

public class UserDetails {

	private int id = -1;
	private String username;
	private String firstname;
	private String surname;
	private String address;
	private BigDecimal phoneNumber;
	private String date;

	public UserDetails() {
	}

	public UserDetails(int id, String username, String firstname,
			String surname, String address, BigDecimal phoneNumber) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;

	}

	public UserDetails(String username, String firstname, String surname,
			String address, BigDecimal phoneNumber) {
		this.username = username;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
		this.phoneNumber = phoneNumber;

	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		if (id != -1) {
			cv.put(DBSchema.TB_USERDETAILS.USERID, id);
		}
		cv.put(DBSchema.TB_USERDETAILS.USERNAME, username);
		cv.put(DBSchema.TB_USERDETAILS.FIRSTNAME, firstname);
		cv.put(DBSchema.TB_USERDETAILS.SURNAME, surname);
		cv.put(DBSchema.TB_USERDETAILS.ADDRESS, address);
		cv.put(DBSchema.TB_USERDETAILS.PHONENUMER, phoneNumber.toString());
		return cv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPhonenumer() {
		return phoneNumber;
	}

	public void setPhonenumer(BigDecimal phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
