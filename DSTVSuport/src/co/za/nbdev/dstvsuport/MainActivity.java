package co.za.nbdev.dstvsuport;

import java.util.ArrayList;
import java.util.List;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.view.IHomeView;

public class MainActivity extends AppCompatActivity implements
		IHomeView<String> {

	private MenuItem mSearchAction;
	private boolean isSearchOpened = false;
	private EditText edtSeach;
	private Toolbar actionBar;
	private TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = (Toolbar) findViewById(R.id.vi_toolbar);
		actionBar.setTitle("");
		setSupportActionBar(actionBar);
		final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_fragments);
		final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_fragments);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),
				getFragments()));
		tabLayout.setupWithViewPager(viewPager);

	}

	public class MyAdapter extends FragmentStatePagerAdapter {

		private final List<Fragment> fragments;

		public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);

			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {

			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return fragments
					.get(position)
					.getArguments()
					.getCharSequence(ApplicationConstants.ScreenName.getValue());
		}
	}

	private List<Fragment> getFragments() {

		final List<Fragment> fList = new ArrayList<Fragment>();
		fList.add(FragmentContoller.newInstance(ApplicationConstants.HomeScreen
				.getValue()));
		return fList;

	}

	@Override
	public void toast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onGetDataList(List<String> datas) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		// Retrieve the SearchView and plug it into SearchManager
		final SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(menu.findItem(R.id.action_search));
		final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int id = item.getItemId();

		switch (id) {
		case R.id.action_settings:
			return true;
		case R.id.action_search:
			handleMenuSearch();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	protected void handleMenuSearch() {
		final ActionBar action = getSupportActionBar(); // get the actionbar

		if (isSearchOpened) { // test if the search is open

			action.setDisplayShowCustomEnabled(false); // disable a custom view
														// inside the actionbar
			action.setDisplayShowTitleEnabled(true); // show the title in the
														// action bar

			// hides the keyboard
			final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

			// add the search icon in the action bar
			mSearchAction.setIcon(getResources().getDrawable(
					R.drawable.ic_open_search));

			isSearchOpened = false;
		} else { // open the search entry

			action.setDisplayShowCustomEnabled(true); // enable it to display a
			// custom view in the action bar.
			action.setCustomView(R.layout.search_cont_bar);// add the custom
															// view
			action.setDisplayShowTitleEnabled(false); // hide the title

			edtSeach = (EditText) action.getCustomView().findViewById(
					R.id.edtSearch); // the text editor

			// this is a listener to do a search when the user clicks on search
			// button
			edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId,
						KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_SEARCH) {

						return true;
					}
					return false;
				}
			});

			edtSeach.requestFocus();

			// open the keyboard focused in the edtSearch
			final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);

			// add the close icon
			mSearchAction.setIcon(getResources().getDrawable(
					R.drawable.ic_close_search));

			isSearchOpened = true;
		}
	}

	@Override
	public void onBackPressed() {
		if (isSearchOpened) {
			handleMenuSearch();
			return;
		}
		super.onBackPressed();
	}

}
