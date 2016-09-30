package com.bontsi.ndiraphutcha.util;

import android.support.v7.app.AppCompatActivity;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ngdevelopmentframework.utils.FooterSetings;

public class Footer {

	private static String TITLES[] = { "Home", "Services", "Our work",
			"About Us", "Contact Us" };
	private static int ICONS[] = { R.drawable.ic_home, R.drawable.ic_services,
			R.drawable.ic_our_work, R.drawable.ic_aboutus, R.drawable.ic_mail };
	private static String NAME = "Ndimphiwe Given Bontsi";
	private static String EMAIL = "ngbdevelopment@gmail.com";
	private final FooterSetings footerSettings;

	public Footer(final AppCompatActivity context, int recycleId, int drawerId) {
		footerSettings = new FooterSetings(context, recycleId, drawerId);
		footerSettings.setEMAIL(EMAIL);
		footerSettings.setICONS(ICONS);
		footerSettings.setNAME(NAME);
		footerSettings.setEMAIL(EMAIL);
		footerSettings.createSingleTapView();
	}
}
