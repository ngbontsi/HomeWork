package co.za.nbdev.dstvsuport;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.bontsi.ngdevelopmentframework.utils.CommunicationUtil;
import com.bontsi.ngdevelopmentframework.utils.StringUtils;

public class MyAlertDialogFragment extends DialogFragment {

	private final ContactInfo screen;

	public MyAlertDialogFragment(ContactInfo screen) {
		this.screen = screen;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				// set dialog icon
				.setIcon(android.R.drawable.stat_notify_error)
				// set Dialog Title
				.setTitle("Contact: " + screen.getServiceProvider())
				// Set Dialog Message
				.setMessage(
						"Do you wish to Contact " + screen.getServiceProvider())
				.setNeutralButton("Call",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								final String test = StringUtils.replace(
										screen.getMobileNumber(), " ", "");
								CommunicationUtil.makeAcall(getActivity(),
										Integer.parseInt(StringUtils.replace(
												screen.getMobileNumber(), " ",
												"")));
								dialog.dismiss();

							}
						})
				// positive button
				.setPositiveButton("Email",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								CommunicationUtil.sendEmail(getActivity(),
										screen.getEmail());
								dialog.dismiss();

							}
						})
				// negative button
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						}).create();
	}

}
