package com.bontsi.nbdevelopment.contactus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.view.Footer;
import com.bontsi.ngdevelopmentframework.EmailDetails;
import com.bontsi.ngdevelopmentframework.TracerActivity;
import com.bontsi.ngdevelopmentframework.utils.CommunicationUtil;
import com.bontsi.ngdevelopmentframework.utils.ToolBarUtility;

public class ContactUsActivity extends TracerActivity implements
		View.OnClickListener {

	private Footer footer;
	private boolean bRequiresResponse;
	private CheckBox responseCheckbox;
	private String feedbackType;
	private EditText nameField;
	private String name;
	private EditText emailField;
	private String email;
	private EditText feedbackField;
	private String feedback;
	private Spinner feedbackSpinner;
	private Button ButtonSendFeedback;
	private EmailDetails emailDetails;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conactus);
		nameField = (EditText) findViewById(R.id.EditTextName);
		name = nameField.getText().toString();

		emailField = (EditText) findViewById(R.id.EditTextEmail);
		email = emailField.getText().toString();

		feedbackField = (EditText) findViewById(R.id.EditTextFeedbackBody);
		feedback = feedbackField.getText().toString();

		feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
		feedbackType = feedbackSpinner.getSelectedItem().toString();

		responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);
		bRequiresResponse = responseCheckbox.isChecked();
		footer = new Footer(this, R.id.contact_rec, R.id.contact_home);
		final ToolBarUtility toolBarUtility = new ToolBarUtility(this,
				R.id.contact_coor, R.id.fab);
		ButtonSendFeedback = (Button) findViewById(R.id.ButtonSendFeedback);
		ButtonSendFeedback.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonSendFeedback:
			emailDetails = new EmailDetails("nbdevelopment@gmail.com",
					feedbackType, feedback);
			CommunicationUtil.sendEmail(this, emailDetails);
			break;

		}

	}
}
