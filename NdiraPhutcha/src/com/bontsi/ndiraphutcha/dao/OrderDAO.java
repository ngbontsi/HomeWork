package com.bontsi.ndiraphutcha.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bontsi.ndiraphutcha.tables.OrderDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class OrderDAO {

	private final DBSchema mHelper;
	private final Context mContext;
	private final SimpleDateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy/");

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_ORDER.ORDERNUMBER
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_ORDER.ORDERNUMBER
			+ " DESC";

	public OrderDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public OrderDetails insertUserDetails(OrderDetails userDetails)
			throws SQLiteException {

		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.ORDER_TABLE, null,
				userDetails.getValues());
		final OrderDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public OrderDetails updateUserDetails(OrderDetails userDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long id = db
				.update(DBSchema.ORDER_TABLE, userDetails.getValues(),
						SELECT_ID_BASED, new String[] { Integer
								.toString(userDetails.getOrdernumber()) });
		final OrderDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long delete(OrderDetails userDetails) throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.ORDER_TABLE, SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getOrdernumber()) }

		);
		db.close();
		return res;
	}

	public ArrayList<OrderDetails> getAllUserDetails() throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.ORDER_TABLE, null, null, null, null,
				null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<OrderDetails> userDetailsList = new ArrayList<OrderDetails>();
			while (!c.isAfterLast()) {
				final OrderDetails userDetails = new OrderDetails();
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

	public OrderDetails getUserDetails(int id) throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.ORDER_TABLE, null, SELECT_ID_BASED,
				new String[] { Integer.toString(id) }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final OrderDetails userDetails = new OrderDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	private void populateData(final Cursor c, final OrderDetails stockDetails) {
		stockDetails.setOrdernumber(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDER.ORDERNUMBER)));
		stockDetails.setQuantity(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDER.QUANTITY)));
		stockDetails.setPrice(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDER.PRICE)));
		stockDetails.setDate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_ORDER.DATE)));
	}
}
