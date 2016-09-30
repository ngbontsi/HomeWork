package com.bontsi.ndiraphutcha.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.logging.ApplicationLogger;
import com.bontsi.ndiraphutcha.product.presenter.IProductPresenter;
import com.bontsi.ndiraphutcha.product.presenter.ProductPresenterCompl;
import com.bontsi.ndiraphutcha.product.view.IProductView;
import com.bontsi.ndiraphutcha.util.Footer;
import com.bontsi.ngdevelopmentframework.utils.NonScrollListView;

public class ProductActivity extends AppCompatActivity implements IProductView,
		View.OnClickListener {

	private EditText product_name, product_description, price;
	private NonScrollListView sourceSpinner;
	private Button btnLogin;
	private Button btnClear;
	private ApplicationLogger logger;
	private IProductPresenter loginPresenter;
	private ProgressBar progressBar;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {

			logger = new ApplicationLogger();
			logger.activityStatrtUp(this.getClass());
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_addproduct);
			final Footer footer = new Footer(this, R.id.prod_rec,
					R.id.prod_home);
			// find view
			sourceSpinner = (NonScrollListView) findViewById(R.id.source_list);
			final String[] sports = getResources().getStringArray(
					R.array.sources_list);
			adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_multiple_choice, sports);
			sourceSpinner.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			sourceSpinner.setAdapter(adapter);

			product_name = (EditText) findViewById(R.id.et_add_product_name);
			product_description = (EditText) findViewById(R.id.et_add_product_desc);
			price = (EditText) findViewById(R.id.et_add_product_cost);
			btnLogin = (Button) findViewById(R.id.btn_add_product);
			btnClear = (Button) findViewById(R.id.btn_clear_product);
			progressBar = (ProgressBar) findViewById(R.id.progress_login);

			// set listener
			btnLogin.setOnClickListener(this);
			btnClear.setOnClickListener(this);

			// init
			loginPresenter = new ProductPresenterCompl(this,
					getApplicationContext());
			loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
		} catch (final Exception e) {
			logger.loagError(e);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clear_product:
			loginPresenter.clear();
			break;
		case R.id.btn_add_product:
			loginPresenter.setProgressBarVisiblity(View.VISIBLE);
			btnLogin.setEnabled(false);
			btnClear.setEnabled(false);
			loginPresenter.doProduct(product_name, product_description,
					sourceSpinner, price);
			break;
		}
	}

	@Override
	public void onClearText() {
		product_name.setText("");
		product_description.setText("");
		price.setText("");
		sourceSpinner.clearChoices();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onAddProductResult(Boolean result, int code) {
		loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
		btnLogin.setEnabled(true);
		btnClear.setEnabled(true);
		if (result) {
			loginPresenter.clear();
		} else {
			toast(ApplicationConstants.Application_failure_message.getValue());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		loginPresenter.onDestroy();
	}

	@Override
	public void onSetProgressBarVisibility(int visibility) {
		progressBar.setVisibility(visibility);
	}

	@Override
	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	}

}
