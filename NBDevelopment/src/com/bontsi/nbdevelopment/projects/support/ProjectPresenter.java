package com.bontsi.nbdevelopment.projects.support;

import java.util.List;

import android.content.Context;

import com.bontsi.nbdevelopment.dao.ProductDAO;
import com.bontsi.nbdevelopment.tables.ProjectDetails;
import com.bontsi.ngdevelopmentframework.presenters.IBusinessLogicPresenter;
import com.bontsi.ngdevelopmentframework.view.IBusinessLogicView;

public class ProjectPresenter implements
		IBusinessLogicPresenter<ProjectDetails> {

	private final Context context;
	private final IBusinessLogicView view;

	public ProjectPresenter(IBusinessLogicView projectsActivity,
			Context applicationContext) {
		// TODO Auto-generated constructor stub
		view = projectsActivity;
		context = applicationContext;
	}

	@Override
	public void loadInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListData(List<ProjectDetails> T) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateListData(List<ProjectDetails> T) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteListData(List<ProjectDetails> T) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addData(ProjectDetails projectDetails) {
		// TODO Auto-generated method stub
		final ProductDAO projectDAO = new ProductDAO(context);
		projectDAO.insertMenuTypeData(projectDetails);
		view.addData(true);

	}

	@Override
	public void updateData(ProjectDetails object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteData(ProjectDetails object) {
		// TODO Auto-generated method stub

	}

}
