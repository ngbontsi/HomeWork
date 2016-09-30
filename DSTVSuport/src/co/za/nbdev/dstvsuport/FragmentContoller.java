package co.za.nbdev.dstvsuport;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.za.nbdev.dstvsuport.data.DataControllerAdapter;
import co.za.nbdev.dstvsuport.event.ScreenGetDatasEvent;
import co.za.nbdev.dstvsuport.presenter.DataControlImp;

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
public class FragmentContoller extends Fragment implements
		IFragmentView<ContactInfo> {

	private String screenName;

	private DataControllerAdapter adapter;
	private IFragmentPresenter<ContactInfo> dataControlImpl;

	public static FragmentContoller newInstance(String screenName) {
		final FragmentContoller fragment = new FragmentContoller();
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

		// find view
		final RecyclerView recyclerView = (RecyclerView) view
				.findViewById(R.id.list_home);
		recyclerView.setHasFixedSize(true);

		// init
		final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
		llm.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(llm);
		dataControlImpl = new DataControlImp(this);
		adapter = new DataControllerAdapter(dataControlImpl, getContext());
		recyclerView.setAdapter(adapter);
		dataControlImpl.loadOders(new ContactInfo());
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemClick(int position) {

		final MyAlertDialogFragment editNameDialog = new MyAlertDialogFragment(
				adapter.getItem(position));
		editNameDialog.show(getFragmentManager(), "fragment_edit_name");

	}

	// EventBus Subscribe
	public void onEvent(ScreenGetDatasEvent getDatasEvent) {
		if (getDatasEvent != null) {
			adapter.setDatas(new ArrayList<ContactInfo>());
		}
	}

	@Override
	public void onLoadData(List<ContactInfo> datalist) {
		adapter.setDatas(datalist);
	}

}
