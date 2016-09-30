package com.bontsi.nbdevelopment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.bontsi.nbdevelopment.dialogfragment.MyDialogFragment;
import com.bontsi.nbdevelopment.presenter.ActivityPresenterCompl;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.nbdevelopment.view.Footer;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.TracerActivity;
import com.bontsi.ngdevelopmentframework.presenters.IHomePresenter;
import com.bontsi.ngdevelopmentframework.view.IHomeView;

public class MainActivity extends TracerActivity implements
		MyDialogFragment.UserNameListener, IHomeView<ProjectDetails> {

	private Footer footer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		footer = new Footer(this, R.id.frag_rec, R.id.frag_home);

		final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_fragments);
		final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_fragments);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),
				getFragments()));
		tabLayout.setupWithViewPager(viewPager);

		final IHomePresenter iActivityPresenter = new ActivityPresenterCompl(
				this);
		iActivityPresenter.loadDatas();

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
		fList.add(NavigationDrawerFragment
				.newInstance(ApplicationConstants.ActiveScreen.getValue()));
		fList.add(NavigationDrawerFragment
				.newInstance(ApplicationConstants.BusyScreen.getValue()));
		fList.add(NavigationDrawerFragment
				.newInstance(ApplicationConstants.FinishedScreen.getValue()));
		fList.add(NavigationDrawerFragment
				.newInstance(ApplicationConstants.FindScreen.getValue()));
		return fList;

	}

	@Override
	public void onFinishUserDialog(String user) {
		Toast.makeText(this, "Hello, " + user, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void toast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onGetDataList(List<ProjectDetails> datas) {

	}
}
