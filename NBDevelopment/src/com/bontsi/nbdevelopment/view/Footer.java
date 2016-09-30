package com.bontsi.nbdevelopment.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.util.ProjectActivityHolder;
import com.bontsi.ngdevelopmentframework.utils.ActivityUtils;
import com.bontsi.ngdevelopmentframework.utils.FooterSetings;

public class Footer {
	private static String TITLES[] = { "Home", "Add Project", "Our work",
			"About Us", "Contact Us" };
	private static int ICONS[] = { R.drawable.ic_home, R.drawable.ic_services,
			R.drawable.ic_our_work, R.drawable.ic_aboutus, R.drawable.ic_mail };
	private static String NAME = "Ndimphiwe Given Bontsi";
	private static String EMAIL = "ngbdevelopment@gmail.com";
	private final FooterSetings footerSettings;
	private int possion;
	private ProjectActivityHolder projectActivityHolder;
	private final Context context;

	public Footer(Context context, int recycleId, int drawerId) {
		this.context = context;
		footerSettings = new FooterSetings(ActivityUtils.getActivity(context),
				recycleId, drawerId);
		footerSettings.setTITLES(TITLES);
		footerSettings.setEMAIL(EMAIL);
		footerSettings.setICONS(ICONS);
		footerSettings.setNAME(NAME);
		footerSettings.setEMAIL(EMAIL);
		footerSettings.createSingleTapView();
		eventListen();
	}

	private void eventListen() {
		getRecycler().addOnItemTouchListener(
				new RecyclerView.SimpleOnItemTouchListener() {
					@Override
					public boolean onInterceptTouchEvent(
							RecyclerView recyclerView, MotionEvent motionEvent) {
						// TODO Auto-generated method stub

						final View child = recyclerView.findChildViewUnder(
								motionEvent.getX(), motionEvent.getY());

						if (child != null
								&& getStructureDectector().onTouchEvent(
										motionEvent)) {
							possion = recyclerView.getChildPosition(child) - 1;
							callsreen();
							getDrawer().closeDrawers();
							return true;
						}
						return false;
					}
				});
	}

	private void callsreen() {
		projectActivityHolder = new ProjectActivityHolder();
		projectActivityHolder.callScreen(getPosition(), context);
	}

	public RecyclerView getRecycler() {
		return footerSettings.getRecylerView();
	}

	public int getPosition() {
		return possion;
	}

	public GestureDetector getStructureDectector() {
		return footerSettings.getStructureDectector();
	}

	public DrawerLayout getDrawer() {
		return footerSettings.getDrawer();
	}
}
