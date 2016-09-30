package com.bontsi.micproject.main.activity.model;

import java.util.ArrayList;

import com.bontsi.micproject.data.LoginDetailsDAO;
import com.bontsi.micproject.data.NotesDAO;
import com.bontsi.micproject.data.UserDetailsDAO;
import com.bontsi.micproject.main.activity.MVP_Main;
import com.bontsi.micproject.models.LoginDetails;
import com.bontsi.micproject.models.Note;
import com.bontsi.micproject.models.UserDetails;

/**
 * Model layer on Model View Presenter Pattern
 * 
 * --------------------------------------------------- Created by Tin Megali on
 * 18/03/16. Project: tuts+mvp_sample
 * --------------------------------------------------- <a
 * href="http://www.tinmegali.com">tinmegali.com</a> <a
 * href="http://www.github.com/tinmegali>github</a>
 * ---------------------------------------------------
 */
public class MainModel implements MVP_Main.ProvidedModelOps {

	// Presenter reference
	private MVP_Main.RequiredPresenterOps mPresenter;
	private NotesDAO notesDAO;
	private UserDetailsDAO userDetailsDAO;
	private LoginDetailsDAO loginDetailsDAO;
	// Recycler data
	public ArrayList<Note> notesList;
	public ArrayList<UserDetails> userDetailsList;
	public ArrayList<LoginDetails> loginDetailsList;

	/**
	 * Main constructor, called by Activity during MVP setup
	 * 
	 * @param presenter
	 *            Presenter instance
	 */
	public MainModel(MVP_Main.RequiredPresenterOps presenter) {
		mPresenter = presenter;
		notesDAO = new NotesDAO(mPresenter.getAppContext());
	}

	public MainModel(MVP_Main.RequiredPresenterOps presenter, NotesDAO dao) {
		mPresenter = presenter;
		notesDAO = dao;
	}

	/**
	 * Called by Presenter when View is destroyed
	 * 
	 * @param isChangingConfiguration
	 *            true configuration is changing
	 */
	@Override
	public void onDestroy(boolean isChangingConfiguration) {
		if (!isChangingConfiguration) {
			mPresenter = null;
			notesDAO = null;
			notesList = null;

		}
	}

	/**
	 * Loads all Data, getting notes from DB
	 * 
	 * @return true with success
	 */
	@Override
	public boolean loadData() {
		notesList = notesDAO.getAllNotes();
		return notesList != null || userDetailsList != null
				|| loginDetailsList != null;
	}

	/**
	 * Get a specific note from notes list using its array postion
	 * 
	 * @param position
	 *            Array position
	 * @return Note from list
	 */
	@Override
	public Note getNote(int position) {
		return notesList.get(position);
	}

	/**
	 * Get Note's positon on ArrayList
	 * 
	 * @param note
	 *            Note to check
	 * @return Positon on ArrayList
	 */
	public int getNotePosition(Note note) {
		for (int i = 0; i < notesList.size(); i++) {
			if (note.getId() == notesList.get(i).getId()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Delete a given Note form DB and ArrayList
	 * 
	 * @param note
	 *            Note to be deleted
	 * @param adapterPos
	 *            Position on array
	 * @return true when success
	 */
	@Override
	public boolean deleteNote(Note note, int adapterPos) {
		final long res = notesDAO.deleteNote(note);
		if (res > 0) {
			notesList.remove(adapterPos);
			return true;
		}
		return false;
	}

	/**
	 * Insert a note on DB
	 * 
	 * @param note
	 *            Note to insert
	 * @return Note's position on ArrayList
	 */
	@Override
	public int insertNote(Note note) {
		final Note insertedNote = notesDAO.insertNote(note);
		if (insertedNote != null) {
			loadData();
			return getNotePosition(insertedNote);
		}
		return -1;
	}

	/**
	 * Get ArrayList size
	 * 
	 * @return ArrayList size
	 */
	@Override
	public int getNotesCount() {
		if (notesList != null) {
			return notesList.size();
		}
		return 0;
	}

}
