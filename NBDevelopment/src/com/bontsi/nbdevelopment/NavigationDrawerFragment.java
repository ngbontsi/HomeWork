package com.bontsi.nbdevelopment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bontsi.nbdevelopment.adapter.MyFragmentAdapter;
import com.bontsi.nbdevelopment.dialogfragment.MyAlertDialogFragment;
import com.bontsi.nbdevelopment.event.FragmentGetDatasEvent;
import com.bontsi.nbdevelopment.presenter.DataControlImpl;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.presenters.IFragmentPresenter;
import com.bontsi.ngdevelopmentframework.view.IFragmentView;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragment extends Fragment implements
		IFragmentView<ProjectDetails> {

	private String screenName;

	private MyFragmentAdapter adapter;
	private IFragmentPresenter<String> iFragmentPresenter;

	public static NavigationDrawerFragment newInstance(String screenName) {
		final NavigationDrawerFragment fragment = new NavigationDrawerFragment();
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
		final View view = inflater.inflate(R.layout.fragment_main, container,
				false);

		// register

		// find view
		final RecyclerView recyclerView = (RecyclerView) view
				.findViewById(R.id.list_home);
		recyclerView.setHasFixedSize(true);

		// init
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		iFragmentPresenter = new DataControlImpl(this);
		adapter = new MyFragmentAdapter(iFragmentPresenter, getContext());
		recyclerView.setAdapter(adapter);
		iFragmentPresenter.loadOders(screenName);
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemClick(int position) {

		final MyAlertDialogFragment editNameDialog = new MyAlertDialogFragment(
				screenName, adapter.getItem(position));
		editNameDialog.show(getFragmentManager(), "fragment_edit_name");

	}

	// EventBus Subscribe
	public void onEvent(FragmentGetDatasEvent getDatasEvent) {
		if (getDatasEvent != null) {
			adapter.setDatas(new ArrayList<ProjectDetails>());
		}
	}

	@Override
	public void onLoadData(List<ProjectDetails> datalist) {
		adapter.setDatas(datalist);
	}

}
