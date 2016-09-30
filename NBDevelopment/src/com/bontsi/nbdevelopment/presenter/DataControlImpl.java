package com.bontsi.nbdevelopment.presenter;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.bontsi.nbdevelopment.dao.ProductDAO;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.ngdevelopmentframework.ApplicationConstants;
import com.bontsi.ngdevelopmentframework.presenters.IFragmentPresenter;
import com.bontsi.ngdevelopmentframework.view.IFragmentView;

/**
 * Created by kaede on 2015/5/19.
 */
public class DataControlImpl implements IFragmentPresenter<String> {
	IFragmentView iFragmentView;
	private ProductDAO productDAO;

	public DataControlImpl(IFragmentView iFragmentView) {
		this.iFragmentView = iFragmentView;
	}

	@Override
	public void onItemClick(int position) {
		iFragmentView.onItemClick(position);

	}

	@Override
	public void loadOders(String screenName) {
		// TODO Auto-generated method stub
		List<ProjectDetails> projects = new ArrayList<ProjectDetails>();
		if (!TextUtils.isEmpty(screenName)) {
			if (screenName.equals(ApplicationConstants.FindScreen.getValue())) {
				projects = getProductDAO().getAllUserDetails();
			} else if (screenName.equals(ApplicationConstants.FinishedScreen
					.getValue())) {
				projects = getProductDAO().getProductDetailByStatus(
						ApplicationConstants.CompletedStatus.getValue());

			} else if (screenName.equals(ApplicationConstants.ActiveScreen
					.getValue())) {
				projects = getProductDAO().getProductDetailByStatus(
						ApplicationConstants.NewStatus.getValue());

			} else if (screenName.equals(ApplicationConstants.BusyScreen
					.getValue())) {
				projects = getProductDAO().getProductDetailByStatus(
						ApplicationConstants.BusyStatus.getValue());

			}

		}
		iFragmentView.onLoadData(projects);
	}

	private ProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new ProductDAO(iFragmentView.getActivity());
		}
		return productDAO;
	}

	@Override
	public void screenActionEvent(String screen, String object) {
		// TODO Auto-generated method stub

	}
}
