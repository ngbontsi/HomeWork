package com.bontsi.ndiraphutcha.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bontsi.ndiraphutcha.R;
import com.bontsi.ndiraphutcha.adapter.MyAdapter;
import com.bontsi.ndiraphutcha.constants.ApplicationConstants;
import com.bontsi.ndiraphutcha.dao.OrderDAO;
import com.bontsi.ndiraphutcha.fragment.event.FragmentGetDatasEvent;
import com.bontsi.ndiraphutcha.fragment.presenter.FragmentPresenterCompl;
import com.bontsi.ndiraphutcha.fragment.presenter.IFragmentPresenter;
import com.bontsi.ndiraphutcha.fragment.view.IFragmentView;
import com.bontsi.ndiraphutcha.home.dialogfragment.MyDialogFragment;

import de.greenrobot.event.EventBus;

public class DemoFragment extends Fragment implements IFragmentView {

	private String screenName;
	private OrderDAO orderDAO;

	private MyAdapter adapter;
	private IFragmentPresenter iFragmentPresenter;

	public static DemoFragment newInstance(String screenName) {
		final DemoFragment fragment = new DemoFragment();
		final Bundle args = new Bundle();
		args.putString(ApplicationConstants.ScreenName.getValue(), screenName);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			screenName = getArguments().getString(
					ApplicationConstants.ScreenName.getValue());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_demo, container,
				false);

		// register
		EventBus.getDefault().register(this);

		// find view
		final RecyclerView recyclerView = (RecyclerView) view
				.findViewById(R.id.list_home);

		// init
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		iFragmentPresenter = new FragmentPresenterCompl(this);
		adapter = new MyAdapter(iFragmentPresenter, getContext());
		recyclerView.setAdapter(adapter);
		iFragmentPresenter.loadOders(screenName);
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onItemClick(int position) {

		final MyDialogFragment editNameDialog = new MyDialogFragment();
		editNameDialog.show(getFragmentManager(), "fragment_edit_name");

	}

	// EventBus Subscribe
	public void onEvent(FragmentGetDatasEvent getDatasEvent) {
		if (getDatasEvent != null && getDatasEvent.getDatas() != null
				&& getDatasEvent.getDatas().size() > 0) {
			adapter.setDatas(getDatasEvent.getDatas());
		}
	}

	@Override
	public void onLoadOder(String screenName) {

		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(screenName)) {
			if (screenName.equals(ApplicationConstants.FindScreen.getValue())) {
				adapter.setDatas(getOrderDAO().getAllUserDetails());
			} else if (screenName.equals(ApplicationConstants.FinishedScreen
					.getValue())) {
				adapter.setDatas(getOrderDAO().getAllUserDetails());

			} else if (screenName.equals(ApplicationConstants.ActiveScreen
					.getValue())) {
				adapter.setDatas(getOrderDAO().getAllUserDetails());

			}

		}
	}

	private OrderDAO getOrderDAO() {
		if (orderDAO == null) {
			orderDAO = new OrderDAO(getContext());
		}
		return orderDAO;
	}
}
