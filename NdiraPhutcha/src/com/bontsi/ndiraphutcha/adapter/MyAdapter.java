package com.bontsi.ndiraphutcha.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.fragment.presenter.IFragmentPresenter;
import com.bontsi.ndiraphutcha.order.module.OrderModule;
import com.bontsi.ndiraphutcha.tables.OrderDetails;
import com.bontsi.ngdevelopmentframework.utils.DateUtils;

/**
 * Created by kaede on 2015/10/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	List<OrderDetails> datas;
	IFragmentPresenter iFragmentPresenter;
	OrderModule orderModule;
	Context context;

	public MyAdapter(IFragmentPresenter iFragmentPresenter, Context context) {
		this.iFragmentPresenter = iFragmentPresenter;
		this.context = context;
		datas = new ArrayList<OrderDetails>();
	}

	public void setDatas(List<OrderDetails> datas) {
		if (datas != null && datas.size() > 0) {
			this.datas.clear();
			this.datas.addAll(datas);
			notifyDataSetChanged();
		}
	}

	public String getItem(int position) {
		return Integer.toString(datas.get(position).getOrdernumber());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_recycleview, parent, false);
		final ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		try {
			holder.status.setText(getOrderModule().getStatusDesc(
					datas.get(position).getOrdernumber()));
			holder.ordernumber.setText("Order No. "
					+ datas.get(position).getOrdernumber());
			holder.orderdate.setText("Order Date "
					+ DateUtils.getDate(datas.get(position).getDate()));
			holder.orderprice.setText("Order price R "
					+ datas.get(position).getPrice());
			holder.imageViewIcon.setDefaultImageResId(R.drawable.ic_order);
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					iFragmentPresenter.onItemClick(position);
				}
			});

		} catch (final Exception e) {
			e.getStackTrace();
		}

	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		public TextView status;
		public TextView ordernumber;
		public TextView orderdate;
		public TextView orderprice;
		public NetworkImageView imageViewIcon;

		public ViewHolder(View v) {
			super(v);
			status = (TextView) itemView.findViewById(R.id.tv_order_status);
			ordernumber = (TextView) itemView.findViewById(R.id.tv_ordernumber);
			orderdate = (TextView) itemView.findViewById(R.id.tv_orededate);
			orderprice = (TextView) itemView.findViewById(R.id.tv_orderprice);
			imageViewIcon = (NetworkImageView) itemView
					.findViewById(R.id.imageView);
		}
	}

	private OrderModule getOrderModule() {
		if (orderModule == null) {
			orderModule = new OrderModule(context);
		}
		return orderModule;
	}
}
