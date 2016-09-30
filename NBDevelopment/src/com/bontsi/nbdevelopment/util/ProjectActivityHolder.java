package com.bontsi.nbdevelopment.util;

import android.content.Context;

import com.bontsi.nbdevelopment.MainActivity;
import com.bontsi.nbdevelopment.aboutus.AboutActivity;
import com.bontsi.nbdevelopment.contactus.ContactUsActivity;
import com.bontsi.nbdevelopment.ourwork.OurWorkActivity;
import com.bontsi.nbdevelopment.projects.ProjectsActivity;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.utils.ActivityHolder;
import com.bontsi.ngdevelopmentframework.utils.ActivityUtils;

public class ProjectActivityHolder extends ActivityHolder {

	public ProjectActivityHolder() {
		addActivity(ApplicationConstants.HomeScreen.getValue(),
				MainActivity.class);
		addActivity(ApplicationConstants.ServicesScreen.getValue(),
				ProjectsActivity.class);
		addActivity(ApplicationConstants.WorkScreen.getValue(),
				OurWorkActivity.class);
		addActivity(ApplicationConstants.AboutScreen.getValue(),
				AboutActivity.class);
		addActivity(ApplicationConstants.ContactScreen.getValue(),
				ContactUsActivity.class);
	}

	public void callScreen(int position, Context context) {

		final Class activity = getActivity(getNameList().get(position));
		if (activity != null) {
			ActivityUtils.startFromOriginalActivity(
					ActivityUtils.getActivity(context), activity);
		}

	}

}
