package co.za.nbdev.dstvsuport.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import co.za.nbdev.dstvsuport.event.ScreenGetDatasEvent;

import com.bontsi.ngdevelopmentframework.presenters.IHomePresenter;

/**
 * Created by kaede on 2015/5/19.
 */
public class HomeScreenHander implements IHomePresenter {
	public static HomeScreenImpl activityHolder;
	// static {
	// activityHolder = new ProjectActivityHolder();
	// }

	Context context;

	public HomeScreenHander(Context context) {
		this.context = context;
	}

	@Override
	public void loadDatas() {

		final Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				final ScreenGetDatasEvent getDatasEvent = new ScreenGetDatasEvent();
			}
		}, 1000);
	}

	@Override
	public void onItemClick(int position) {
		activityHolder.callScreen(position, context);
	}

	@Override
	public <TextView> void loadSearchDatas(TextView v) {
		// TODO Auto-generated method stub

	}

}
