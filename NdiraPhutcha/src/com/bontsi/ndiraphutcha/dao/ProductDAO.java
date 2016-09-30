package com.bontsi.ndiraphutcha.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.bontsi.ndiraphutcha.tables.ProductsDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class ProductDAO {

	private final DBSchema mHelper;
	private final Context mContext;
	private final SimpleDateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy/");

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_PRODUCTS.PRODUCTID
			+ " = ? ";
	private static final String SELECT_USER_BASED = DBSchema.TB_PRODUCTS.PRODUCTNAME
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_PRODUCTS.PRODUCTID
			+ " DESC";

	public ProductDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public ProductsDetails insertMenuTypeData(ProductsDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.PRODUCTS, null,
				userDetails.getValues());
		final ProductsDetails insertedDetails = getMenuTypeData((int) id);
		db.close();
		return insertedDetails;
	}

	public ProductsDetails updateMenuTypeData(ProductsDetails userDetails)
			throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.update(DBSchema.PRODUCTS, userDetails.getValues(),
				SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getProductid()) });
		final ProductsDetails insertedDetails = getMenuTypeData((int) id);
		db.close();
		return insertedDetails;
	}

	public long delete(ProductsDetails menuTypeData) throws SQLiteException {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.PRODUCTS, SELECT_ID_BASED,
				new String[] { Integer.toString(menuTypeData.getProductid()) }

		);
		db.close();
		return res;
	}

	public ArrayList<ProductsDetails> getAllUserDetails()
			throws SQLiteException {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.PRODUCTS, null, null, null, null,
				null, SORT_ORDER_DEFAULT);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final ArrayList<ProductsDetails> menuTypeList = new ArrayList<ProductsDetails>();
			while (!c.isAfterLast()) {
				final ProductsDetails menuTypeData = new ProductsDetails();
				populateData(c, menuTypeData);
				menuTypeList.add(menuTypeData);
				c.moveToNext();
			}
			c.close();
			db.close();
			return menuTypeList;
		} else {
			return null;
		}
	}

	public ProductsDetails getMenuTypeData(int id) {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.PRODUCTS, null, SELECT_ID_BASED,
				new String[] { Integer.toString(id) }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final ProductsDetails menuTypeData = new ProductsDetails();
			populateData(c, menuTypeData);
			c.close();
			db.close();
			return menuTypeData;
		} else {
			return null;
		}
	}

	public ProductsDetails getProductDetail(String name) {
		if (name == null) {
			return null;
		}
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.PRODUCTS, null, SELECT_USER_BASED,
				new String[] { name }, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			final ProductsDetails menuTypeData = new ProductsDetails();
			populateData(c, menuTypeData);
			c.close();
			db.close();
			return menuTypeData;
		} else {
			return null;
		}
	}

	private void populateData(final Cursor c, final ProductsDetails product) {
		product.setProductid(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.PRODUCTID)));
		product.setProductname(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.PRODUCTNAME)));
		product.setDesscription(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.DESSCRIPTION)));
		product.setSources(c.getString(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.SOURCES)));
		product.setPrice(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.PRICE)));
		product.setDate(c.getInt(c
				.getColumnIndexOrThrow(DBSchema.TB_PRODUCTS.DATE)));
	}
}
