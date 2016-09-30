package com.bontsi.ndiraphutcha.addmenu;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.addmenu.presenter.AddStockPresenterCompl;
import com.bontsi.ndiraphutcha.addmenu.presenter.IStockPresenter;
import com.bontsi.ndiraphutcha.addmenu.view.IStockView;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.tables.StockDetails;
import com.bontsi.ndiraphutcha.util.Footer;

public class StockActivity extends AppCompatActivity implements IStockView,
		View.OnClickListener {

	private TextView remaining_stock, used_stock;
	private EditText quantity;
	private Spinner product_name;
	private Button btnLogin;
	private Button btnClear;
	private IStockPresenter stockPresenter;
	private ProgressBar progressBar;
	private StockDetails userDetailsFromScreen;
	private Footer footer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock);
		// find view
		remaining_stock = (TextView) findViewById(R.id.et_remaining_stock);
		used_stock = (TextView) findViewById(R.id.et_used_stock);
		quantity = (EditText) findViewById(R.id.et_new_stock);

		product_name = (Spinner) findViewById(R.id.et_product_name);
		footer = new Footer(this, R.id.stock_rec, R.id.stock_home);
		btnLogin = (Button) findViewById(R.id.btn_add_stock);
		btnClear = (Button) findViewById(R.id.btn_clear_stock);
		progressBar = (ProgressBar) findViewById(R.id.progress_login);
		userDetailsFromScreen = (StockDetails) getIntent()
				.getSerializableExtra(ApplicationConstants.MenuID.getValue());
		// set listener
		btnLogin.setOnClickListener(this);
		btnClear.setOnClickListener(this);

		// init
		stockPresenter = new AddStockPresenterCompl(this,
				getApplicationContext(), userDetailsFromScreen);
		// sett up spinner
		final List<String> list = stockPresenter.getStockList();
		final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		product_name.setAdapter(dataAdapter);

		stockPresenter.setProgressBarVisiblity(View.INVISIBLE);
		stockPresenter.populateStock(userDetailsFromScreen);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_clear_stock:
			stockPresenter.clear();
			break;
		case R.id.btn_add_stock:
			stockPresenter.setProgressBarVisiblity(View.VISIBLE);
			btnLogin.setEnabled(false);
			btnClear.setEnabled(false);
			stockPresenter.doAddStock(product_name, remaining_stock,
					used_stock, quantity);
			break;
		}
	}

	@Override
	public void onClearText() {

	}

	@Override
	public void onAddMenuResult(Boolean result, int code) {
		stockPresenter.setProgressBarVisiblity(View.INVISIBLE);
		btnLogin.setEnabled(true);
		btnClear.setEnabled(true);
		if (result) {

			stockPresenter.populateStock(userDetailsFromScreen);
			toast(ApplicationConstants.Data_insert_message.getValue());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stockPresenter.onDestroy();
	}

	@Override
	public void onSetProgressBarVisibility(int visibility) {
		progressBar.setVisibility(visibility);
	}

	@Override
	public void onLoadStock(StockDetails data) {
		if (data != null) {

			remaining_stock.setText(Integer.toString(data.getRemainigstock()));
			used_stock.setText(Integer.toString(data.getUsedstock()));

		} else {

			remaining_stock.setText(Integer.toString(0));
			used_stock.setText(Integer.toString(0));

		}

	}

	@Override
	public void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

	}

}
