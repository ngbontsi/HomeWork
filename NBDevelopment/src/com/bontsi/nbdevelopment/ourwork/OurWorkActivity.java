package com.bontsi.nbdevelopment.ourwork;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.view.Footer;
import com.bontsi.ngdevelopmentframework.TracerActivity;
import com.bontsi.ngdevelopmentframework.utils.ToolBarUtility;

public class OurWorkActivity extends TracerActivity {

	public final static int LOOPS = 1000;
	public static int FIRST_PAGE; // = count * LOOPS / 2;
	public final static float BIG_SCALE = 1.0f;
	public final static float SMALL_SCALE = 0.7f;
	public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
	public MyPagerAdapter adapter;
	public ViewPager pager;
	/*** variables for the View */
	public int coverUrl[];
	public static int count;
	public static OurWorkActivity mainActivityCtx;
	private Footer footer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ourwork);
		footer = new Footer(this, R.id.work_rec, R.id.work_home);
		final ToolBarUtility toolBarUtility = new ToolBarUtility(this,
				R.id.ourwork_coor, R.id.fab);
		coverUrl = new int[] { R.drawable.ic_aboutus,
				R.drawable.ic_contat_home, R.drawable.ic_home,
				R.drawable.ic_home_number, R.drawable.ic_location,
				R.drawable.ic_logo, R.drawable.ic_mail, R.drawable.ic_office,
				R.drawable.ic_oficce_number, R.drawable.ic_our_work };

		mainActivityCtx = this;
		pager = (ViewPager) findViewById(R.id.myviewpager);
		count = coverUrl.length;
		final DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int pageMargin = 0;
		pageMargin = metrics.widthPixels / 4 * 2;
		pager.setPageMargin(-pageMargin);

		try {
			adapter = new MyPagerAdapter(this, getSupportFragmentManager());
			pager.setAdapter(adapter);
			adapter.notifyDataSetChanged();

			FIRST_PAGE = count * LOOPS / 2;

			pager.setOnPageChangeListener(adapter);
			// Set current item to the middle page so we can fling to both
			// directions left and right
			pager.setCurrentItem(FIRST_PAGE); // FIRST_PAGE
			// pager.setFocusableInTouchMode(true);
			pager.setOffscreenPageLimit(3);
			// Set margin for pages as a negative number, so a part of next and
			// previous pages will be showed

		} catch (final Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
