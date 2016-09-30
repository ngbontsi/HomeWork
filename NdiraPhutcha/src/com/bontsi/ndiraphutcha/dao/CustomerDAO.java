package com.bontsi.ndiraphutcha.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bontsi.ndiraphutcha.tables.CustomerDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class CustomerDAO {

	private final DBSchema mHelper;
	private final Context mContext;
	private final SimpleDateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy/");

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_CUSTORMER.CUSTOMERID
			+ " = ? ";
	private static final String SELECT_USER_BASED = DBSchema.TB_CUSTORMER.CUSTOMERNAME
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_CUSTORMER.CUSTOMERID
			+ " DESC";

	public CustomerDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public CustomerDetails insertUserDetails(CustomerDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.CUSTOMER_TABLE, null,
				userDetails.getValues());
		final CustomerDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public CustomerDetails updateUserDetails(CustomerDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.update(DBSchema.CUSTOMER_TABLE,
				userDetails.getValues(), SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getId()) });
		final CustomerDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long delete(CustomerDetails userDetails) throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.CUSTOMER_TABLE, SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getId()) }

		);
		db.close();
		return res;
	}

	public ArrayList<CustomerDetails> getAllUserDetails()
			throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.CUSTOMER_TABLE, null, null, null,
				null, null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<CustomerDetails> userDetailsList = new ArrayList<CustomerDetails>();
			while (!c.isAfterLast()) {
				final CustomerDetails userDetails = new CustomerDetails();
				populateData(c, userDetails);
				userDetailsList.add(userDetails);
				c.moveToNext();
			}
			c.close();
			db.close();
			return userDetailsList;
		} else {
			return null;
		}
	}

	public CustomerDetails getUserDetails(int id) throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.CUSTOMER_TABLE, null,
				SELECT_ID_BASED, new String[] { Integer.toString(id) }, null,
				null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final CustomerDetails userDetails = new CustomerDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	public CustomerDetails getUserDetails(String name) {
		if (name == null) {
			return null;
		}
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.CUSTOMER_TABLE, null,
				SELECT_USER_BASED, new String[] { name }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final CustomerDetails userDetails = new CustomerDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	private void populateData(final Cursor c, final CustomerDetails stockDetails) {
		stockDetails.setId(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_CUSTORMER.CUSTOMERID)));
		stockDetails.setName(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_CUSTORMER.CUSTOMERNAME)));
		stockDetails.setAddress(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_CUSTORMER.CUSTOMERADDRESS)));
		stockDetails.setPhone(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_CUSTORMER.CUSTOMERNAME)));
		stockDetails.setDate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_CUSTORMER.DATE)));
	}
}
