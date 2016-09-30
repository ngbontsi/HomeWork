package com.bontsi.micproject.data;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bontsi.micproject.models.Note;

/**
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class NotesDAO {

	private final DBSchema mHelper;
	private final Context mContext;

	// SELECTIONS
	private static final String SELECT_ID_BASED = DBSchema.TB_NOTES.ID
			+ " = ? ";
	private static final String PROJECTION_ALL = " * ";
	public static final String SORT_ORDER_DEFAULT = DBSchema.TB_NOTES.ID
			+ " DESC";

	public NotesDAO(Context context) {
		mContext = context;
		mHelper = new DBSchema(mContext);
	}

	private SQLiteDatabase getReadDB() {
		return mHelper.getReadableDatabase();
	}

	private SQLiteDatabase getWriteDB() {
		return mHelper.getWritableDatabase();
	}

	public Note insertNote(Note note) {
		final SQLiteDatabase db = getWriteDB();
		final long id = db.insert(DBSchema.TABLE_NOTES, null, note.getValues());
		final Note insertedNote = getNote((int) id);
		db.close();
		return insertedNote;
	}

	public long deleteNote(Note note) {
		final SQLiteDatabase db = getWriteDB();
		final long res = db.delete(DBSchema.TABLE_NOTES, SELECT_ID_BASED,
				new String[] { Integer.toString(note.getId()) }

		);
		db.close();
		return res;
	}

	public ArrayList<Note> getAllNotes() {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.TABLE_NOTES, null, null, null, null,
				null, SORT_ORDER_DEFAULT);
		if (c != null) {
			c.moveToFirst();
			final ArrayList<Note> notes = new ArrayList<Note>();
			while (!c.isAfterLast()) {
				final Note note = new Note();
				note.setId(c.getInt(c
						.getColumnIndexOrThrow(DBSchema.TB_NOTES.ID)));
				note.setText(c.getString(c
						.getColumnIndexOrThrow(DBSchema.TB_NOTES.NOTE)));
				note.setDate(c.getString(c
						.getColumnIndexOrThrow(DBSchema.TB_NOTES.DATE)));
				notes.add(note);
				c.moveToNext();
			}
			c.close();
			db.close();
			return notes;
		} else {
			return null;
		}
	}

	public Note getNote(int id) {
		final SQLiteDatabase db = getReadDB();
		final Cursor c = db.query(DBSchema.TABLE_NOTES, null, SELECT_ID_BASED,
				new String[] { Integer.toString(id) }, null, null, null);
		if (c != null) {
			c.moveToFirst();
			final Note note = new Note();
			note.setId(c.getInt(c.getColumnIndexOrThrow(DBSchema.TB_NOTES.ID)));
			note.setText(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_NOTES.NOTE)));
			note.setDate(c.getString(c
					.getColumnIndexOrThrow(DBSchema.TB_NOTES.DATE)));
			c.close();
			db.close();
			return note;
		} else {
			return null;
		}
	}

}
