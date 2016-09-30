package com.bontsi.nbdevelopment.dialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.bontsi.nbdevelopment.dao.ProductDAO;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;

public class MyAlertDialogFragment extends DialogFragment {

	private final String screen;
	private final String selected;
	private ProductDAO productDAO;

	public MyAlertDialogFragment(String screen, String selected) {
		this.screen = screen;
		this.selected = selected;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
		// set dialog icon
				.setIcon(android.R.drawable.stat_notify_error)
				// set Dialog Title
				.setTitle("Project Update")
				// Set Dialog Message
				.setMessage("Are you sure you want to update: " + selected)

				// positive button
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						final ProjectDetails proj = getProductDAO()
								.getProductDetail(selected);

						if (screen.equals(ApplicationConstants.FindScreen
								.getValue())) {
							Toast.makeText(getActivity(),
									"Project Already Completed",
									Toast.LENGTH_SHORT).show();
						} else if (screen
								.equals(ApplicationConstants.FinishedScreen
										.getValue())) {
							Toast.makeText(getActivity(),
									"Project Already Completed",
									Toast.LENGTH_SHORT).show();

						} else if (screen
								.equals(ApplicationConstants.BusyScreen
										.getValue())) {
							proj.setProjectStatus(ApplicationConstants.CompletedStatus
									.getValue());

						} else if (screen
								.equals(ApplicationConstants.ActiveScreen
										.getValue())) {
							proj.setProjectStatus(ApplicationConstants.BusyStatus
									.getValue());

						}

						getProductDAO().updateMenuTypeData(proj);

					}
				})
				// negative button
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Toast.makeText(getActivity(), "Cancel",
										Toast.LENGTH_SHORT).show();
							}
						}).create();
	}

	private ProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new ProductDAO(getContext());
		}
		return productDAO;
	}
}
