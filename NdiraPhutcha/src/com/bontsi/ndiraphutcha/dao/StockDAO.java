package com.bontsi.ndiraphutcha.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bontsi.ndiraphutcha.tables.StockDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class StockDAO {

	private final DBSchema mHelper;
	private final Context mContext;
	private final SimpleDateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy/");

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_STOCK.STOCKID
			+ " = ? ";
	private static final String SELECT_USER_BASED = DBSchema.TB_STOCK.PRODUCTNAME
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_STOCK.STOCKID
			+ " DESC";

	public StockDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public StockDetails insertStockDetail(StockDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.STOCK_TABLE, null,
				userDetails.getValues());
		final StockDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public StockDetails updateStockDetails(StockDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.update(DBSchema.STOCK_TABLE,
				userDetails.getValues(), SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getStockid()) });
		final StockDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long delete(StockDetails userDetails) throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.STOCK_TABLE, SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getStockid()) }

		);
		db.close();
		return res;
	}

	public ArrayList<StockDetails> getAllStocks() throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.STOCK_TABLE, null, null, null, null,
				null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<StockDetails> userDetailsList = new ArrayList<StockDetails>();
			while (!c.isAfterLast()) {
				final StockDetails userDetails = new StockDetails();
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

	public StockDetails getUserDetails(int id) throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.STOCK_TABLE, null, SELECT_ID_BASED,
				new String[] { Integer.toString(id) }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final StockDetails userDetails = new StockDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	public StockDetails getStockDetails(String name) throws SQLiteException {
		if (name == null) {
			return null;
		}
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.STOCK_TABLE, null,
				SELECT_USER_BASED, new String[] { name }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final StockDetails userDetails = new StockDetails();
			populateData(c, userDetails);
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

	private void populateData(final Cursor c, final StockDetails stockDetails) {
		stockDetails.setStockid(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_STOCK.STOCKID)));
		stockDetails.setProductname(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_STOCK.PRODUCTNAME)));
		stockDetails.setRemainigstock(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_STOCK.REMAINIGSTOCK)));
		stockDetails.setUsedstock(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_STOCK.USEDSTOCK)));
		stockDetails.setDate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_STOCK.DATE)));
	}
}
