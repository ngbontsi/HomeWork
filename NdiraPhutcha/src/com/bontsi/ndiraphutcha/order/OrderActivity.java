package com.bontsi.ndiraphutcha.order;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.home.HomeActivity;
import com.bontsi.ndiraphutcha.logging.ApplicationLogger;
import com.bontsi.ndiraphutcha.order.presenter.IOrderPresenter;
import com.bontsi.ndiraphutcha.order.presenter.OrderPresenterCompl;
import com.bontsi.ndiraphutcha.order.view.IOrderView;
import com.bontsi.ndiraphutcha.tables.ProductsDetails;
import com.bontsi.ndiraphutcha.util.Footer;

public class OrderActivity extends AppCompatActivity implements IOrderView,
		View.OnClickListener {

	private Spinner product, source;
	private EditText quantity;
	private TextView price;
	private Button btnLogin;
	private Button btnClear;
	private ApplicationLogger logger;
	private IOrderPresenter loginPresenter;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			logger = new ApplicationLogger();
			logger.activityStatrtUp(this.getClass());
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_order);
			// find view
			final Footer footer = new Footer(this, R.id.oreder_rec,
					R.id.order_home);
			product = (Spinner) findViewById(R.id.et_order_product_name);
			source = (Spinner) findViewById(R.id.et_order_source_desc);
			quantity = (EditText) findViewById(R.id.et_order_quantity);
			price = (TextView) findViewById(R.id.et_order_price);
			btnLogin = (Button) findViewById(R.id.btn_order_product);
			btnClear = (Button) findViewById(R.id.btn_clear_order);
			progressBar = (ProgressBar) findViewById(R.id.progress_login);

			loginPresenter = new OrderPresenterCompl(this,
					getApplicationContext());
			// sett up spinner
			final List<String> prodList = loginPresenter.getStocks();
			final ArrayAdapter<String> producList = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, prodList);
			producList
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			product.setAdapter(producList);
			final String[] sports = getResources().getStringArray(
					R.array.sources_list);
			final ArrayAdapter<String> sourceList = new ArrayAdapter<String>(
					this, android.R.layout.simple_spinner_item, sports);
			sourceList
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			source.setAdapter(sourceList);

			// set listener
			btnLogin.setOnClickListener(this);
			btnClear.setOnClickListener(this);
			quantity.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence c, int start,
						int before, int count) {
					// TODO Auto-generated method stub

				}

				@Override
				public void beforeTextChanged(CharSequence c, int start,
						int count, int after) {
					// TODO Auto-generated method stub

				}

				@Override
				public void afterTextChanged(Editable c) {
					// TODO Auto-generated method stub
					final String quality = c.toString();
					final int quanty = quality.equals("") ? 0 : Integer
							.valueOf(quality);
					final String selected = (String) product.getSelectedItem();
					final ProductsDetails productsDetails = loginPresenter
							.getProducts(selected);
					int pric = 10;
					if (productsDetails != null) {
						pric = productsDetails.getPrice();
					}
					final int total = pric * quanty;

					price.setText(Integer.toString(total));
					return;
				}
			});

			// init
			loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
		} catch (final Exception e) {
			logger.loagError(e);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clear_order:
			loginPresenter.clear();
			break;
		case R.id.btn_order_product:
			loginPresenter.setProgressBarVisiblity(View.VISIBLE);
			btnLogin.setEnabled(false);
			btnClear.setEnabled(false);
			loginPresenter.doMakeAnOrder(product, source, quantity, price);
			break;
		}
	}

	@Override
	public void onClearText() {
		quantity.setText("");
		product.setSelection(0);
		source.setSelection(0);
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

	@Override
	public void onMakeAnOrderResult(Boolean result, int code) {
		loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
		btnLogin.setEnabled(true);
		btnClear.setEnabled(true);
		if (result) {
			final Intent in = new Intent(this, HomeActivity.class);
			startActivity(in);
			toast(ApplicationConstants.Activity_call_message.getValue()
					+ HomeActivity.class.getSimpleName());
		} else {
			toast(ApplicationConstants.Application_failure_message.getValue());
		}

	}

}
