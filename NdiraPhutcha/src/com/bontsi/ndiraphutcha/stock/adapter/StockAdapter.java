package com.bontsi.ndiraphutcha.stock.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.adapter.AppController;
import com.bontsi.ndiraphutcha.stock.presenter.IStockPresenter;
import com.bontsi.ndiraphutcha.tables.StockDetails;

/**
 * Created by kaede on 2015/10/13.
 */
public class StockAdapter extends BaseAdapter {
	private final IStockPresenter iAdapterPresenter;
	private final List<StockDetails> menuList;
	private final Activity activity;
	private LayoutInflater inflater;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public StockAdapter(IStockPresenter iAdapterPresenter,
			List<StockDetails> menuList) {
		this.iAdapterPresenter = iAdapterPresenter;
		this.menuList = menuList;
		activity = iAdapterPresenter.getActivity();
	}

	public void removeStock(int position) {
		if (position >= 0 && position < menuList.size()) {
			menuList.remove(position);
			notifyDataSetChanged();
		}
	}

	public void setStockList(List<StockDetails> meatList) {
		if (meatList != null && meatList.size() > 0) {
			menuList.clear();
			menuList.addAll(meatList);
			notifyDataSetChanged();
		}
	}

	public void addStock(StockDetails stock) {
		menuList.add(stock);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return menuList.size();
	}

	@Override
	public String getItem(int position) {

		return menuList.get(position).getProductname();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		if (inflater == null) {
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.feed_item, null);
		}

		if (imageLoader == null) {
			imageLoader = AppController.getInstance().getImageLoader();
		}

		final TextView productName = (TextView) convertView
				.findViewById(R.id.feed_product_name);
		final TextView feedTimeStamp = (TextView) convertView
				.findViewById(R.id.feed_timestamp);
		final TextView feedRemainingStock = (TextView) convertView
				.findViewById(R.id.feed_remaining_stock);
		final TextView feedUsedStock = (TextView) convertView
				.findViewById(R.id.feed_used_stock);
		final NetworkImageView profilePic = (NetworkImageView) convertView
				.findViewById(R.id.profilePic);

		final StockDetails item = menuList.get(position);

		productName.setText(item.getProductname());

		// Converting timestamp into x ago format
		// final CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
		// Long.parseLong(item.getTimeStamp()),
		// System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
		// timestamp.setText(timeAgo);

		// Chcek for empty status message
		if (item.getRemainigstock() > 0) {
			feedRemainingStock.setText(item.getRemainigstock());
			feedRemainingStock.setVisibility(View.VISIBLE);
		} else {
			// status is empty, remove from view
			feedRemainingStock.setVisibility(View.GONE);
		}

		// Checking for null feed url
		if (item.getUsedstock() > 0) {
			feedUsedStock.setText(item.getUsedstock());
			feedUsedStock.setVisibility(View.VISIBLE);
		} else {
			// url is null, remove from the view
			feedUsedStock.setVisibility(View.GONE);
		}
		if (item.getDate() > 0) {
			feedTimeStamp.setText(item.getDate());
			feedTimeStamp.setVisibility(View.VISIBLE);
		} else {
			// url is null, remove from the view
			feedTimeStamp.setVisibility(View.GONE);
		}

		// user profile pic
		profilePic.setDefaultImageResId(R.drawable.user_icon);

		return convertView;
	}
}
