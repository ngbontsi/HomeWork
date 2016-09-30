package com.bontsi.nbdevelopment.ourwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bontsi.nbdevelopment.R;

public class MyPagerAdapter extends FragmentPagerAdapter implements
		ViewPager.OnPageChangeListener {

	private MyLinearLayout cur = null;
	private MyLinearLayout next = null;
	private final OurWorkActivity context;
	private final FragmentManager fm;
	private float scale;
	int pCount = 0;

	public MyPagerAdapter(OurWorkActivity context, FragmentManager fm) {
		super(fm);
		this.fm = fm;
		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		// make the first pager bigger than others
		try {
			if (position == OurWorkActivity.FIRST_PAGE) {
				scale = OurWorkActivity.BIG_SCALE;
			} else {
				scale = OurWorkActivity.SMALL_SCALE;
			}

			position = position % OurWorkActivity.count;

		} catch (final Exception e) {
			// TODO: handle exception
		}
		return OurWorkFragment.newInstance(context, position, scale);
	}

	@Override
	public int getCount() {
		int count = 0;
		try {
			count = OurWorkActivity.count * OurWorkActivity.LOOPS;
		} catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		try {
			if (positionOffset >= 0f && positionOffset <= 1f) {
				cur = getRootView(position);
				next = getRootView(position + 1);

				cur.setScaleBoth(OurWorkActivity.BIG_SCALE
						- OurWorkActivity.DIFF_SCALE * positionOffset);
				next.setScaleBoth(OurWorkActivity.SMALL_SCALE
						+ OurWorkActivity.DIFF_SCALE * positionOffset);

				pCount++;
			}
		} catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	private MyLinearLayout getRootView(int position) {
		return (MyLinearLayout) fm.findFragmentByTag(getFragmentTag(position))
				.getView().findViewById(R.id.root);
	}

	private String getFragmentTag(int position) {
		return "android:switcher:" + context.pager.getId() + ":" + position;
	}

}
