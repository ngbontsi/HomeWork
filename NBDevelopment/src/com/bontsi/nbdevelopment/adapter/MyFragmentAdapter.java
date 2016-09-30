package com.bontsi.nbdevelopment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.bontsi.nbdevelopment.R;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.presenters.IFragmentPresenter;
import com.bontsi.ngdevelopmentframework.utils.StringUtils;

/**
 * Created by kaede on 2015/10/15.
 */
public class MyFragmentAdapter extends
		RecyclerView.Adapter<MyFragmentAdapter.ViewHolder> {
	IFragmentPresenter iFragmentPresenter;
	List<ProjectDetails> datas = new ArrayList<ProjectDetails>();
	Context context;

	public MyFragmentAdapter(IFragmentPresenter iFragmentPresenter,
			Context context) {
		this.iFragmentPresenter = iFragmentPresenter;
		this.context = context;
	}

	public void setDatas(List<ProjectDetails> datas) {
		if (datas != null && datas.size() > 0) {
			this.datas.clear();
			this.datas.addAll(datas);
			notifyDataSetChanged();
		}
	}

	public String getItem(int position) {
		return datas.get(position).getProject();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.fragment_navigation_drawer, parent, false);
		final ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		try {
			holder.status.setText(getStatus(datas.get(position)
					.getProjectStatus()));
			holder.ordernumber.setText(datas.get(position).getProject());
			holder.orderdate.setText(datas.get(position).getProjectDate());
			holder.imageViewIcon.setDefaultImageResId(R.drawable.ic_services);
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

	private String getStatus(String item) {
		// TODO Auto-generated method stub
		if (StringUtils.nullOrEmpty(item)) {
			return "Status Uknown";
		}
		if (item.equals(ApplicationConstants.BusyStatus.getValue())) {
			return "In Proggress";
		}
		if (item.equals(ApplicationConstants.CompletedStatus.getValue())) {
			return " Completed ";
		}
		if (item.equals(ApplicationConstants.CancelStatus.getValue())) {
			return " Cancelled ";
		}
		if (item.equals(ApplicationConstants.NewStatus.getValue())) {
			return " New";
		}

		return null;
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

}
