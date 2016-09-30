package com.bontsi.nbdevelopment.aboutus;

import android.os.Bundle;

import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.view.Footer;
import com.bontsi.ngdevelopmentframework.TracerActivity;
import com.bontsi.ngdevelopmentframework.utils.ToolBarUtility;

public class AboutActivity extends TracerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
		final Footer footer = new Footer(this, R.id.about_rec, R.id.about_home);
		final ToolBarUtility toolBarUtility = new ToolBarUtility(this,
				R.id.about_coor, R.id.fab);

	}

}
