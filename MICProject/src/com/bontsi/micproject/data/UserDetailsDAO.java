package com.bontsi.micproject.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bontsi.micproject.models.UserDetails;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class UserDetailsDAO {

	private final DBSchema mHelper;
	private final Context mContext;

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_USERDETAILS.USERID
			+ " = ? ";
	private static final String SELECT_USER_BASED = DBSchema.TB_USERDETAILS.USERNAME
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_USERDETAILS.USERID
			+ " DESC";

	public UserDetailsDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public UserDetails insertUserDetails(UserDetails userDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.TABLE_USER_DETAILS, null,
				userDetails.getValues());
		final UserDetails insertedDetails = getUserDetails((int) id);
		db.close();
		return insertedDetails;
	}

	public long deleteNote(UserDetails userDetails) {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.TABLE_USER_DETAILS,
				SELECT_ID_BASED,
				new String[] { Integer.toString(userDetails.getId()) }

		);
		db.close();
		return res;
	}

	public ArrayList<UserDetails> getAllUserDetails() {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.TABLE_USER_DETAILS, null, null,
				null, null, null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<UserDetails> userDetailsList = new ArrayList<UserDetails>();
			while (!c.isAfterLast()) {
				final UserDetails userDetails = new UserDetails();
				userDetails
						.setId(c.getInt(c
								.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.USERID)));
				userDetails
						.setUsername(c.getString(c
								.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.USERNAME)));
				userDetails
						.setFirstname(c.getString(c
								.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.FIRSTNAME)));
				userDetails
						.setSurname(c.getString(c
								.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.SURNAME)));
				userDetails
						.setAddress(c.getString(c
								.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.ADDRESS)));
				userDetails.setDate(c.getString(c
						.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.DATE)));
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

	public UserDetails getUserDetails(int id) {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.TABLE_USER_DETAILS, null,
				SELECT_ID_BASED, new String[] { Integer.toString(id) }, null,
				null, null);
		if (c != null) {
			c.moveToFirst();
			final UserDetails userDetails = new UserDetails();
			userDetails.setId(c.getInt(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.USERID)));
			userDetails.setUsername(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.USERNAME)));
			userDetails.setFirstname(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.FIRSTNAME)));
			userDetails.setSurname(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.SURNAME)));
			userDetails.setAddress(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.ADDRESS)));
			userDetails.setDate(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_USERDETAILS.DATE)));
			c.close();
			db.close();
			return userDetails;
		} else {
			return null;
		}
	}

}
