package com.bontsi.nbdevelopment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class DBSchema extends SQLiteOpenHelper {
	private static final int DB_VERSION = 2;
	private static final String DB_NAME = "mvp_nbdev.db";
	private static final String TAG = "DBSchema";

	public DBSchema(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// Declaration of Tables
	public static final String PRODUCTS = "PRODUCTS";

	// Sqlite syntax values
	private static final String COMMA_SPACE = ", ";
	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
	private static final String PRIMARY_KEY = "PRIMARY KEY ";
	private static final String UNIQUE = "UNIQUE ";
	private static final String TYPE_TEXT = " TEXT ";
	private static final String TYPE_IMAGE = " BLOB ";
	private static final String TYPE_DATE = " DATETIME ";
	private static final String TYPE_INT = " INTEGER ";
	private static final String DEFAULT = "DEFAULT ";
	private static final String AUTOINCREMENT = "AUTOINCREMENT ";
	private static final String NOT_NULL = "NOT NULL ";
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

	// START defining Tables
	public static final class TB_PRODUCTS {
		public static final String PRODUCTID = "product_id";
		public static final String PRODUCTNAME = "product_name";
		public static final String PROJECTDATE = "prod_desrcitpion";
		public static final String STATUS = "status";

	}

	// END defining Tables

	// this is a create statement for products
	private static final String CREATE_PRODUCTS_STATEMENT = CREATE_TABLE
			+ PRODUCTS + " ( " + TB_PRODUCTS.PRODUCTID + TYPE_INT + NOT_NULL
			+ PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE
			+ TB_PRODUCTS.PRODUCTNAME + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.PROJECTDATE + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.STATUS + TYPE_TEXT + NOT_NULL + ")";
	private static final String DROP_PRODUCTS_STATEMENT = DROP_TABLE + PRODUCTS;

	// this is a create statement for stock

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PRODUCTS_STATEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (isTableExists(PRODUCTS, db)) {

			db.execSQL(DROP_PRODUCTS_STATEMENT);
		}

		db.execSQL(CREATE_PRODUCTS_STATEMENT);
	}

	public boolean isTableExists(String tableName, SQLiteDatabase db) {

		final Cursor cursor = db.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ tableName + "'", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.close();
				return true;
			}
			cursor.close();
		}
		return false;
	}
}
