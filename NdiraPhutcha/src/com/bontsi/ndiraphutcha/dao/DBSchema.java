package com.bontsi.ndiraphutcha.dao;

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
	private static final int DB_VERSION = 12;
	private static final String DB_NAME = "mvp_putcha.db";
	private static final String TAG = "DBSchema";

	public DBSchema(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// Declaration of Tables
	public static final String PRODUCTS = "PRODUCTS";
	public static final String STOCK_TABLE = "STOCK";
	public static final String CUSTOMER_TABLE = "CUSTOMER";
	public static final String ORDERHEADERS_TABLE = "ORDERHEADERS";
	public static final String ORDER_TABLE = "ORDERTABLE";

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
		public static final String DESSCRIPTION = "desrcitpion";
		public static final String SOURCES = "source";
		public static final String PRICE = "price";
		public static final String DATE = "date";

	}

	public static final class TB_STOCK {
		public static final String STOCKID = "stock_id";
		public static final String PRODUCTNAME = "product_name";
		public static final String REMAINIGSTOCK = "remaing_stock";
		public static final String USEDSTOCK = "used_stock";
		public static final String DATE = "date";

	}

	public static final class TB_CUSTORMER {
		public static final String CUSTOMERID = "cust_id";
		public static final String CUSTOMERNAME = "customer_name";
		public static final String CUSTOMERADDRESS = "customer_address";
		public static final String CUSTOMERNUMBER = "customer_number";
		public static final String DATE = "date";

	}

	public static final class TB_ORDERHEADERS {
		public static final String ORDERNUMBER = "order_number";
		public static final String CUSTOMERID = "cust_id";
		public static final String OREDEREDDATE = "order_date";
		public static final String ORDERESTATUS = "order_status";
		public static final String DELEIVERDATE = "delivery_date";
		public static final String TOTALPRICE = "total_price";
		public static final String DATE = "date";

	}

	public static final class TB_ORDER {
		public static final String ORDERNUMBER = "product_id";
		public static final String QUANTITY = "quantity";
		public static final String PRICE = "price";
		public static final String DATE = "date";

	}

	// END defining Tables

	// this is a create statement for products
	private static final String CREATE_PRODUCTS_STATEMENT = CREATE_TABLE
			+ PRODUCTS + " ( " + TB_PRODUCTS.PRODUCTID + TYPE_INT + NOT_NULL
			+ PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE
			+ TB_PRODUCTS.PRODUCTNAME + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.DESSCRIPTION + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.SOURCES + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.PRICE + TYPE_INT + NOT_NULL + COMMA_SPACE
			+ TB_PRODUCTS.DATE + TYPE_DATE + NOT_NULL + ")";
	private static final String DROP_PRODUCTS_STATEMENT = DROP_TABLE + PRODUCTS;
	// this is a create statement for stock
	private static final String CREATE_STOCK_STATEMENT = CREATE_TABLE
			+ STOCK_TABLE + " ( " + TB_STOCK.STOCKID + TYPE_INT + NOT_NULL
			+ PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE + TB_STOCK.PRODUCTNAME
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_STOCK.REMAINIGSTOCK
			+ TYPE_INT + NOT_NULL + COMMA_SPACE + TB_STOCK.USEDSTOCK + TYPE_INT
			+ NOT_NULL + COMMA_SPACE + TB_STOCK.DATE + TYPE_DATE + NOT_NULL
			+ ")";
	private static final String DROP_STOCK_STATEMENT = DROP_TABLE + STOCK_TABLE;
	// this is a create statement for customer
	private static final String CREATE_CUSTOMER_STATEMENT = CREATE_TABLE
			+ CUSTOMER_TABLE + " ( " + TB_CUSTORMER.CUSTOMERID + TYPE_INT
			+ NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE
			+ TB_CUSTORMER.CUSTOMERNAME + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_CUSTORMER.CUSTOMERNUMBER + TYPE_INT + NOT_NULL + COMMA_SPACE
			+ TB_CUSTORMER.CUSTOMERADDRESS + TYPE_TEXT + NOT_NULL + COMMA_SPACE
			+ TB_CUSTORMER.DATE + TYPE_DATE + NOT_NULL + ")";
	private static final String DROP_CUSTOMER_STATEMENT = DROP_TABLE
			+ CUSTOMER_TABLE;
	private static final String CREATE_ORDERHEADERS_STATEMENT = CREATE_TABLE
			+ ORDERHEADERS_TABLE + " ( " + TB_ORDERHEADERS.ORDERESTATUS
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_ORDERHEADERS.ORDERNUMBER
			+ TYPE_DATE + NOT_NULL + COMMA_SPACE + TB_ORDERHEADERS.OREDEREDDATE
			+ TYPE_DATE + NOT_NULL + COMMA_SPACE + TB_ORDERHEADERS.DELEIVERDATE
			+ TYPE_DATE + NOT_NULL + COMMA_SPACE + TB_ORDERHEADERS.CUSTOMERID
			+ TYPE_INT + COMMA_SPACE + TB_ORDERHEADERS.TOTALPRICE + TYPE_INT
			+ NOT_NULL + COMMA_SPACE + TB_ORDERHEADERS.DATE + TYPE_DATE
			+ NOT_NULL + ")";
	private static final String DROP_ORDERHEADERS_STATEMENT = DROP_TABLE
			+ ORDERHEADERS_TABLE;
	private static final String CREATE_ORDER_STATEMENT = CREATE_TABLE
			+ ORDER_TABLE + " ( " + TB_ORDER.ORDERNUMBER + TYPE_INT + NOT_NULL
			+ PRIMARY_KEY + AUTOINCREMENT + COMMA_SPACE + TB_ORDER.QUANTITY
			+ TYPE_INT + NOT_NULL + COMMA_SPACE + TB_ORDER.PRICE + TYPE_DATE
			+ NOT_NULL + COMMA_SPACE + TB_ORDER.DATE + TYPE_DATE + NOT_NULL
			+ ")";
	private static final String DROP_ORDER_STATEMENT = DROP_TABLE + ORDER_TABLE;

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CUSTOMER_STATEMENT);
		db.execSQL(CREATE_ORDER_STATEMENT);
		db.execSQL(CREATE_ORDERHEADERS_STATEMENT);
		db.execSQL(CREATE_PRODUCTS_STATEMENT);
		db.execSQL(CREATE_STOCK_STATEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (isTableExists(CUSTOMER_TABLE, db)) {

			db.execSQL(DROP_CUSTOMER_STATEMENT);
		}
		if (isTableExists(ORDER_TABLE, db)) {

			db.execSQL(DROP_ORDER_STATEMENT);
		}
		if (isTableExists(ORDERHEADERS_TABLE, db)) {

			db.execSQL(DROP_ORDERHEADERS_STATEMENT);
		}
		if (isTableExists(PRODUCTS, db)) {

			db.execSQL(DROP_PRODUCTS_STATEMENT);
		}
		if (isTableExists(STOCK_TABLE, db)) {

			db.execSQL(DROP_STOCK_STATEMENT);
		}

		db.execSQL(CREATE_CUSTOMER_STATEMENT);
		db.execSQL(CREATE_ORDER_STATEMENT);
		db.execSQL(CREATE_ORDERHEADERS_STATEMENT);
		db.execSQL(CREATE_PRODUCTS_STATEMENT);
		db.execSQL(CREATE_STOCK_STATEMENT);
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
