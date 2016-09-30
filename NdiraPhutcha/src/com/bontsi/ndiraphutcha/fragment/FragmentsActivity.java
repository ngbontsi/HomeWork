package com.bontsi.ndiraphutcha.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.fragment.presenter.ActivityPresenterCompl;
import com.bontsi.ndiraphutcha.fragment.presenter.IActivityPresenter;
import com.bontsi.ndiraphutcha.home.dialogfragment.MyDialogFragment;
import com.bontsi.ndiraphutcha.logging.ApplicationLogger;
import com.bontsi.ndiraphutcha.util.Footer;

public class FragmentsActivity extends AppCompatActivity implements
		MyDialogFragment.UserNameListener {
	private ApplicationLogger logger;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			logger = new ApplicationLogger();
			logger.activityStatrtUp(this.getClass());
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_fragments);
			final Footer footer = new Footer(this, R.id.frag_rec,
					R.id.frag_home);

			final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_fragments);
			final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_fragments);
			viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),
					getFragments()));
			tabLayout.setupWithViewPager(viewPager);

			final IActivityPresenter iActivityPresenter = new ActivityPresenterCompl(
					this);
			iActivityPresenter.loadDatas();
		} catch (final Exception e) {
			logger.loagError(e);
		}
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
		fList.add(DemoFragment.newInstance(ApplicationConstants.ActiveScreen
				.getValue()));
		fList.add(DemoFragment.newInstance(ApplicationConstants.FinishedScreen
				.getValue()));
		fList.add(DemoFragment.newInstance(ApplicationConstants.FindScreen
				.getValue()));
		return fList;

	}

	@Override
	public void onFinishUserDialog(String user) {
		Toast.makeText(this, "Hello, " + user, Toast.LENGTH_SHORT).show();

	}

}
