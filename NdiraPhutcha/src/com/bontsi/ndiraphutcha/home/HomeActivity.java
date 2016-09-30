package com.bontsi.ndiraphutcha.home;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.common.StateMaintainer;
import com.bontsi.ndiraphutcha.home.presenter.HomePresenterCompl;
import com.bontsi.ndiraphutcha.home.view.GridViewAdapter;
import com.bontsi.ndiraphutcha.logging.ApplicationLogger;
import com.bontsi.ndiraphutcha.tables.ImageItem;
import com.bontsi.ndiraphutcha.util.Footer;
import com.bontsi.ngdevelopmentframework.presenters.IHomePresenter;
import com.bontsi.ngdevelopmentframework.view.IHomeView;

public class HomeActivity extends AppCompatActivity implements
		AdapterView.OnItemClickListener, IHomeView {

	private IHomePresenter homePresenter;
	private List<ImageItem> datas;
	private View loadingView;
	private ViewGroup viewGroup;
	private RelativeLayout.LayoutParams layoutParams;
	private ApplicationLogger logger;
	private GridView gridView;
	private GridViewAdapter gridAdapter;
	private ProgressBar progressBar;

	private MenuItem mSearchAction;
	private final boolean isSearchOpened = false;
	private EditText edtSeach;

	// Responsible to maintain the object's integrity
	// during configurations change
	private final StateMaintainer mStateMaintainer = new StateMaintainer(
			getFragmentManager(), HomeActivity.class.getName());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			logger = new ApplicationLogger();
			logger.activityStatrtUp(this.getClass());

			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_home);
			// get list view add put it on the state
			datas = new ArrayList<ImageItem>();
			gridView = (GridView) findViewById(R.id.list_home);
			progressBar = (ProgressBar) findViewById(R.id.progress_login);
			gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout,
					datas);
			final Footer footer = new Footer(this, R.id.home_rec,
					R.id.layout_home);
			gridView.setOnItemClickListener(this);
			gridView.setAdapter(gridAdapter);
			// get load view add put it on the state
			loadingView = LayoutInflater.from(this).inflate(
					R.layout.item_empty_view, null);

			// get Group view and put it on the state
			viewGroup = (ViewGroup) findViewById(R.id.layout_home);
			layoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
			// get Layout Parameters and put it on the state
			viewGroup.addView(loadingView, layoutParams);
			// get Group view and put it on the state

			homePresenter = new HomePresenterCompl(this, this);
			progressBar.setVisibility(View.INVISIBLE);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			logger.loagError(e);
		}
		// find view
	}

	@Override
	protected void onResume() {
		super.onResume();
		homePresenter.loadDatas();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		homePresenter.onItemClick(position);
	}

	@Override
	public <T> void onGetDataList(List<T> t) {
		final List<ImageItem> datas = (List<ImageItem>) t;
		if (datas != null && datas.size() > 0) {
			this.datas.clear();
			this.datas.addAll(datas);
			gridAdapter.notifyDataSetChanged();

		}
	}

	@Override
	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	// protected void handleMenuSearch() {
	// final ActionBar action = getSupportActionBar(); // get the actionbar
	//
	// if (isSearchOpened) { // test if the search is open
	//
	// action.setDisplayShowCustomEnabled(false); // disable a custom view
	// // inside the actionbar
	// action.setDisplayShowTitleEnabled(true); // show the title in the
	// // action bar
	//
	// // hides the keyboard
	// final InputMethodManager imm = (InputMethodManager)
	// getSystemService(Context.INPUT_METHOD_SERVICE);
	// imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);
	//
	// // add the search icon in the action bar
	// mSearchAction.setIcon(getResources()
	// .getDrawable(R.drawable.viewing));
	//
	// isSearchOpened = false;
	// } else { // open the search entry
	//
	// action.setDisplayShowCustomEnabled(true); // enable it to display a
	// // custom view in the action bar.
	// action.setCustomView(R.layout.search_bar);// add the custom view
	// action.setDisplayShowTitleEnabled(false); // hide the title
	//
	// edtSeach = (EditText) action.getCustomView().findViewById(
	// R.id.edtSearch); // the text editor
	//
	// // this is a listener to do a search when the user clicks on search
	// // button
	// edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener()
	// {
	// @Override
	// public boolean onEditorAction(TextView v, int actionId,
	// KeyEvent event) {
	// if (actionId == EditorInfo.IME_ACTION_SEARCH) {
	// doSearch();
	// return true;
	// }
	// return false;
	// }
	// });
	//
	// edtSeach.requestFocus();
	//
	// // open the keyboard focused in the edtSearch
	// final InputMethodManager imm = (InputMethodManager)
	// getSystemService(Context.INPUT_METHOD_SERVICE);
	// imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);
	//
	// // add the close icon
	// mSearchAction.setIcon(getResources().getDrawable(
	// R.drawable.ic_close));
	//
	// isSearchOpened = true;
	// }
	//
	// }

}
