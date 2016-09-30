package com.bontsi.micproject.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bontsi.micproject.models.LoginDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class LoginDetailsDAO {

	private final DBSchema mHelper;
	private final Context mContext;

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_LOGINDETAILS.LOGINID
			+ " = ? ";
	private static final String SELECT_USER_BASED = DBSchema.TB_LOGINDETAILS.USERNAME
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_LOGINDETAILS.LOGINID
			+ " DESC";

	public LoginDetailsDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public LoginDetails insertLoginDetails(LoginDetails userDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.TABLE_USER_LOG_IN, null,
				userDetails.getValues());
		final LoginDetails insertedDetails = getLoginDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long deleteLoginDetails(LoginDetails loginDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.TABLE_USER_LOG_IN, SELECT_ID_BASED,
				new String[] { Integer.toString(loginDetails.getId()) }

		);
		db.close();
		return res;
	}

	public ArrayList<LoginDetails> getAllLoginDetails() {
		final SQLiteDatabase db = getReadDB();
		final Cursor rawData = db.query(DBSchema.TABLE_USER_LOG_IN, null, null,
				null, null, null, SORT_ORDER_DEFAULT);
		if (rawData != null) {
			rawData.moveToFirst();
			final ArrayList<LoginDetails> loginList = new ArrayList<LoginDetails>();
			while (!rawData.isAfterLast()) {
				final LoginDetails loginDetails = new LoginDetails();
				loginDetails
						.setId(rawData.getInt(rawData
								.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.LOGINID)));
				loginDetails
						.setUserName(rawData.getString(rawData
								.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.USERNAME)));
				loginDetails
						.setPassword(rawData.getString(rawData
								.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.PASSWORDS)));
				loginDetails.setDate(rawData.getString(rawData
						.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.DATE)));
				loginList.add(loginDetails);
				rawData.moveToNext();
			}
			rawData.close();
			db.close();
			return loginList;
		} else {
			return null;
		}
	}

	public LoginDetails getLoginDetails(int id) {
		final SQLiteDatabase db = getReadDB();
		final Cursor rawData = db.query(DBSchema.TABLE_USER_LOG_IN, null,
				SELECT_ID_BASED, new String[] { Integer.toString(id) }, null,
				null, null);
		if (rawData != null) {
			rawData.moveToFirst();
			final LoginDetails loginDetails = new LoginDetails();
			loginDetails.setId(rawData.getInt(rawData
					.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.LOGINID)));
			loginDetails.setUserName(rawData.getString(rawData
					.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.USERNAME)));
			loginDetails.setPassword(rawData.getString(rawData
					.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.USERNAME)));
			loginDetails.setDate(rawData.getString(rawData
					.getColumnIndexOrThrow(DBSchema.TB_LOGINDETAILS.DATE)));
			rawData.close();
			db.close();
			return loginDetails;
		} else {
			return null;
		}
	}

}
