package com.bontsi.nbdevelopment.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.bontsi.nbdevelopment.event.FragmentGetDatasEvent;
import com.bontsi.nbdevelopment.util.ProjectActivityHolder;
import com.bontsi.ngdevelopmentframework.presenters.IHomePresenter;

/**
 * Created by kaede on 2015/5/19.
 */
public class ActivityPresenterCompl implements IHomePresenter {
	public static ProjectActivityHolder activityHolder;
	// static {
	// activityHolder = new ProjectActivityHolder();
	// }

	Context context;

	public ActivityPresenterCompl(Context context) {
		this.context = context;
	}

	@Override
	public void loadDatas() {

		final Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				final FragmentGetDatasEvent getDatasEvent = new FragmentGetDatasEvent();
			}
		}, 1000);
	}

	@Override
	public void onItemClick(int position) {
		activityHolder.callScreen(position, context);
	}

}
