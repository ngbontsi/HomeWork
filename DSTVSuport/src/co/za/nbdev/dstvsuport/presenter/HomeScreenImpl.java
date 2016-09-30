package co.za.nbdev.dstvsuport.presenter;

import android.content.Context;
import co.za.nbdev.dstvsuport.MainActivity;

import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.utils.ActivityHolder;
import com.bontsi.ngdevelopmentframework.utils.ActivityUtils;

public class HomeScreenImpl extends ActivityHolder {

	public HomeScreenImpl() {
		addActivity(ApplicationConstants.HomeScreen.getValue(),
				MainActivity.class);
		addActivity(ApplicationConstants.ServicesScreen.getValue(),
				MainActivity.class);
		addActivity(ApplicationConstants.WorkScreen.getValue(),
				MainActivity.class);
		addActivity(ApplicationConstants.AboutScreen.getValue(),
				MainActivity.class);
		addActivity(ApplicationConstants.ContactScreen.getValue(),
				MainActivity.class);
	}

	public void callScreen(int position, Context context) {

		final Class activity = getActivity(getNameList().get(position));
		if (activity != null) {
			ActivityUtils.startFromOriginalActivity(
					ActivityUtils.getActivity(context), activity);
		}

	}

}
