package com.bontsi.micproject.data;

import android.content.Context;
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

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "mvp_sample.db";

	public DBSchema(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// Tables
	public static final String TABLE_NOTES = "notes";
	public static final String TABLE_USER_DETAILS = "USERDETAILS";
	public static final String TABLE_USER_LOG_IN = "LOGINDETAILS";

	private static final String COMMA_SPACE = ", ";
	private static final String CREATE_TABLE = "CREATE TABLE ";
	private static final String PRIMARY_KEY = "PRIMARY KEY ";
	private static final String UNIQUE = "UNIQUE ";
	private static final String TYPE_TEXT = " TEXT ";
	private static final String TYPE_DATE = " DATETIME ";
	private static final String TYPE_INT = " INTEGER ";
	private static final String DEFAULT = "DEFAULT ";
	private static final String AUTOINCREMENT = "AUTOINCREMENT ";
	private static final String NOT_NULL = "NOT NULL ";
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

	public static final class TB_NOTES {
		public static final String ID = "_id";
		public static final String NOTE = "note";
		public static final String DATE = "date";

	}

	public static final class TB_USERDETAILS {
		public static final String USERID = "user_id";
		public static final String USERNAME = "user_name";
		public static final String FIRSTNAME = "First_name";
		public static final String SURNAME = "surname";
		public static final String ADDRESS = "address";
		public static final String PHONENUMER = "phone_number";
		public static final String DATE = "date";

	}

	public static final class TB_LOGINDETAILS {
		public static final String LOGINID = "login_id";
		public static final String USERNAME = "user_name";
		public static final String PASSWORDS = "passwords";
		public static final String DATE = "date";

	}

	private static final String CREATE_TABLE_NOTES = CREATE_TABLE + TABLE_NOTES
			+ " ( " + TB_NOTES.ID + TYPE_INT + NOT_NULL + PRIMARY_KEY
			+ COMMA_SPACE + TB_NOTES.NOTE + TYPE_DATE + NOT_NULL + COMMA_SPACE
			+ TB_NOTES.DATE + TYPE_TEXT + NOT_NULL + ")";

	private static final String CREATE_TABLE_USER_DETAILS = CREATE_TABLE
			+ TABLE_USER_DETAILS + " ( " + TB_USERDETAILS.USERID + TYPE_INT
			+ NOT_NULL + PRIMARY_KEY + COMMA_SPACE + TB_USERDETAILS.USERNAME
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_USERDETAILS.FIRSTNAME
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_USERDETAILS.SURNAME
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_USERDETAILS.ADDRESS
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_USERDETAILS.PHONENUMER
			+ TYPE_INT + NOT_NULL + COMMA_SPACE + TB_USERDETAILS.DATE
			+ TYPE_DATE + NOT_NULL + ")";
	private static final String CREATE_TABLE_USER_LOGIN = CREATE_TABLE
			+ TABLE_USER_LOG_IN + " ( " + TB_LOGINDETAILS.LOGINID + TYPE_INT
			+ NOT_NULL + PRIMARY_KEY + COMMA_SPACE + TB_LOGINDETAILS.USERNAME
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_LOGINDETAILS.PASSWORDS
			+ TYPE_TEXT + NOT_NULL + COMMA_SPACE + TB_LOGINDETAILS.DATE
			+ TYPE_DATE + NOT_NULL + ")";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_NOTES);
		db.execSQL(CREATE_TABLE_USER_DETAILS);
		db.execSQL(CREATE_TABLE_USER_LOGIN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(CREATE_TABLE);
	}
}
