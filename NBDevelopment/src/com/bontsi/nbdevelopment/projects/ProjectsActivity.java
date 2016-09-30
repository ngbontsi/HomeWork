package com.bontsi.nbdevelopment.projects;

import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.nbdevelopment.MainActivity;
import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.projects.support.ProjectPresenter;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.nbdevelopment.view.Footer;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.TracerActivity;
import com.bontsi.ngdevelopmentframework.presenters.IBusinessLogicPresenter;
import com.bontsi.ngdevelopmentframework.utils.ActivityUtils;
import com.bontsi.ngdevelopmentframework.utils.StringUtils;
import com.bontsi.ngdevelopmentframework.utils.ToolBarUtility;
import com.bontsi.ngdevelopmentframework.view.IBusinessLogicView;

public class ProjectsActivity extends TracerActivity implements
		IBusinessLogicView<ProjectDetails>, View.OnClickListener {

	private IBusinessLogicPresenter presenter;

	private TextView tvDisplayDate;
	private DatePicker dpResult;
	private Button btnChangeDate;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;

	private EditText product_name;
	private Button btnLogin;
	private Button btnClear;
	private ProjectDetails project;
	private SearchView mSearchView;
	private MenuItem searchMenuItem;
	private SearchView.OnQueryTextListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		final Footer footer = new Footer(this, R.id.prod_rec, R.id.prod_home);
		final ToolBarUtility toolBarUtility = new ToolBarUtility(this,
				R.id.project_coor, R.id.fab);
		// find view

		tvDisplayDate = (TextView) findViewById(R.id.tvDate);
		dpResult = (DatePicker) findViewById(R.id.dpResult);
		product_name = (EditText) findViewById(R.id.et_add_product_name);
		btnLogin = (Button) findViewById(R.id.btn_add_product);
		btnClear = (Button) findViewById(R.id.btn_clear_product);
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

		setCurrentDateOnView();

		listener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// newText is text entered by user to SearchView
				Toast.makeText(getApplicationContext(), newText,
						Toast.LENGTH_LONG).show();
				return false;
			}
		};

		// set listener
		btnLogin.setOnClickListener(this);
		btnClear.setOnClickListener(this);
		btnChangeDate.setOnClickListener(this);

		// init
		presenter = new ProjectPresenter(this, getApplicationContext());
	}

	// display current date
	public void setCurrentDateOnView() {

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		tvDisplayDate.setText(new StringBuilder()
				// Month is 0 based, just add 1
				.append(month + 1).append("/").append(day).append("/")
				.append(year).append(" "));

		// set current date into datepicker
		dpResult.init(year, month, day, null);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			tvDisplayDate.setText(new StringBuilder().append(month + 1)
					.append("/").append(day).append("/").append(year)
					.append(" "));

			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}

	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clear_product:
			break;
		case R.id.btnChangeDate:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.btn_add_product:
			btnLogin.setEnabled(false);
			btnClear.setEnabled(false);
			if (isValidData()) {

				presenter.addData(project);
				break;
			}
		}
	}

	private boolean isValidData() {
		// TODO Auto-generated method stub

		if (StringUtils.nullOrEmpty(tvDisplayDate.getText().toString())) {
			tvDisplayDate.setError("Please select Data");
			return false;
		}
		if (StringUtils.nullOrEmpty(product_name.getText().toString())) {
			product_name.setError("Please enter project name");

			return false;
		}

		project = new ProjectDetails();
		project.setProject(product_name.getText().toString());
		project.setProjectDate(tvDisplayDate.getText().toString());
		project.setProjectStatus(ApplicationConstants.NewStatus.getValue());
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		searchMenuItem = menu.findItem(R.id.action_search);
		mSearchView = (SearchView) searchMenuItem.getActionView();
		mSearchView.setOnQueryTextListener(listener);
		return true;
	}

	@Override
	public void onLoadInfo(List<ProjectDetails> t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListData(boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateListData(boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteListData(boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addData(boolean result) {
		ActivityUtils.startNewActivityAndFinish(this, MainActivity.class);

	}

	@Override
	public void updateData(boolean result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData(boolean result) {
		// TODO Auto-generated method stub

	}

}
