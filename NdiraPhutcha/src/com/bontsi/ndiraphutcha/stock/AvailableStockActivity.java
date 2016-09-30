package com.bontsi.ndiraphutcha.stock;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.adapter.AvailableStockPresenterCompl;
import com.bontsi.ndiraphutcha.addmenu.StockActivity;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.logging.ApplicationLogger;
import com.bontsi.ndiraphutcha.stock.adapter.StockAdapter;
import com.bontsi.ndiraphutcha.stock.presenter.IStockPresenter;
import com.bontsi.ndiraphutcha.stock.view.IStockView;
import com.bontsi.ndiraphutcha.tables.StockDetails;

public class AvailableStockActivity extends AppCompatActivity implements
		IStockView, AdapterView.OnItemClickListener {

	private static final String TAG = AvailableStockActivity.class
			.getSimpleName();
	private ListView listView;
	private IStockPresenter listAdapter;
	private StockAdapter iAdapterPresenter;
	private ApplicationLogger logger;
	private List<StockDetails> feedItems;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {

			logger = new ApplicationLogger();
			logger.activityStatrtUp(this.getClass());
			super.onCreate(savedInstanceState);
			setContentView(R.layout.available_stock);

			listView = (ListView) findViewById(R.id.list_of_stock);
			listView.setOnItemClickListener(this);
			feedItems = new ArrayList<StockDetails>();
			listView.setDivider(null);
			listAdapter = new AvailableStockPresenterCompl(this, feedItems);
			iAdapterPresenter = new StockAdapter(listAdapter, feedItems);

			listAdapter.loadMenus();
			listView.setAdapter(iAdapterPresenter);
		} catch (final Exception e) {
			logger.loagError(e);
		}

	}

	/**
	 * Parsing json reponse and passing the data to feed view list adapter
	 * */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {

		// setting custom layout to dialog
		final TextView name = (TextView) view
				.findViewById(R.id.feed_product_name);
		final AlertDialog.Builder alerDAlertDialog = new AlertDialog.Builder(
				this);

		alerDAlertDialog.setTitle("Menu Dailog");
		alerDAlertDialog.setMessage("Dou you want to edit Menu");
		alerDAlertDialog.setCancelable(false);
		alerDAlertDialog.setPositiveButton("Edit",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						listAdapter.updateStock(name.getText().toString());

					}
				});
		alerDAlertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

					}
				});

		final AlertDialog alertDialog = alerDAlertDialog.create();
		alertDialog.show();

	}

	@Override
	public void onGetList(List<StockDetails> list) {
		if (list != null && list.size() > 0) {
			feedItems.clear();
			feedItems.addAll(list);
			iAdapterPresenter.notifyDataSetChanged();
		}

	}

	@Override
	public void onEditStock(StockDetails userDetails) {
		if (userDetails != null) {
			final Intent intent = new Intent(this, StockActivity.class);
			final Bundle bundle = new Bundle();
			bundle.putSerializable(ApplicationConstants.MenuID.getValue(),
					userDetails);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	@Override
	public void toast(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Activity onGetActivity() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void onShowFooterView(Boolean isShow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeletMenu(boolean isSucces) {
		// TODO Auto-generated method stub

	}

}
