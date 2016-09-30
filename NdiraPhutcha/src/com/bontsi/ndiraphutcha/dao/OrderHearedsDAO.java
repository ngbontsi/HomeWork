package com.bontsi.ndiraphutcha.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bontsi.ndiraphutcha.tables.OrderHeaderDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class OrderHearedsDAO {

	private final DBSchema mHelper;
	private final Context mContext;
	private final SimpleDateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy/");

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_ORDERHEADERS.ORDERNUMBER
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_ORDERHEADERS.ORDERNUMBER
			+ " DESC";

	public OrderHearedsDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public OrderHeaderDetails insertUserDetails(OrderHeaderDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.ORDERHEADERS_TABLE, null,
				userDetails.getValues());
		final OrderHeaderDetails insertedDetails = getOrderHeaderDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public OrderHeaderDetails updateUserDetails(OrderHeaderDetails userDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.update(DBSchema.ORDERHEADERS_TABLE,
				userDetails.getValues(), SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getOrederid()) });
		final OrderHeaderDetails insertedDetails = getOrderHeaderDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long delete(OrderHeaderDetails userDetails) throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.ORDERHEADERS_TABLE,
				SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getOrederid()) }

		);
		db.close();
		return res;
	}

	public ArrayList<OrderHeaderDetails> getAllUserDetails()
			throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.ORDERHEADERS_TABLE, null, null,
				null, null, null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<OrderHeaderDetails> userDetailsList = new ArrayList<OrderHeaderDetails>();
			while (!c.isAfterLast()) {
				final OrderHeaderDetails userDetails = new OrderHeaderDetails();
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

	public OrderHeaderDetails getOrderHeaderDetails(int id) throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.ORDERHEADERS_TABLE, null,
				SELECT_ID_BASED, new String[] { Integer.toString(id) }, null,
				null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final OrderHeaderDetails userDetails = new OrderHeaderDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	private void populateData(final Cursor c,
			final OrderHeaderDetails stockDetails) {
		stockDetails.setOrdernumber(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.ORDERNUMBER)));
		stockDetails.setCustomerid(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.CUSTOMERID)));
		stockDetails.setOrderestatus(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.ORDERESTATUS)));
		stockDetails.setOredereddate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.OREDEREDDATE)));
		stockDetails.setTotalprice(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.TOTALPRICE)));
		stockDetails.setDeleiverdate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.DELEIVERDATE)));
		stockDetails.setDate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDERHEADERS.DATE)));
	}
}
