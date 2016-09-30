package com.bontsi.ndiraphutcha.constants;

public enum ApplicationConstants {

	MenuID("MENUID", true), //
	Activity_call_message("Rendering........", true), //
	Data_update_message("Records Updated........", true), //
	Application_failure_message("Action failed........", true), //
	Mandotory_feilds_message("All fields are needed.......", true), //
	ScreenName("EXTRA_MESSAGE", true), //
	ActiveScreen("Active Orders", true), //
	FindScreen("Find Your Order", true), //
	FinishedScreen("Finished Orders", true), //
	MeniName("MENUNAME", true), Data_insert_message("New Record Added.....",
			true), NewStatus("NEW", true), CompletedStatus("COMP", true), CancelStatus(
			"CAN", true), BusyStatus("BUS", true), UnKnownStatus("UNK", true), Products(
			"Products", true), Stock("Stock", true), Order("Order", true), Search(
			"View Orders", true); //

	private String constant;
	private boolean valid = true;

	private ApplicationConstants(String constant, boolean valid) {
		this.constant = constant;
		this.valid = valid;
	}

	/**
	 * ruleReasonExist
	 * 
	 * @param ruleReason
	 * @return
	 */
	public static boolean ruleReasonExist(String ruleReason) {
		// determine if rule Reason in reasonsList
		final ApplicationConstants[] reasonsList = values();
		for (final ApplicationConstants reason : reasonsList) {
			if (reason.getValue().equals(ruleReason)) {
				return true;
			}
		}

		return false;
	}

	public String getValue() {
		return constant;
	}

	/**
	 * isValid
	 * 
	 * @return
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * equals
	 * 
	 * @param reason
	 * @return
	 */
	public boolean equals(ApplicationConstants reason) {
		return reason.getValue().equals(getValue());

	}
}
