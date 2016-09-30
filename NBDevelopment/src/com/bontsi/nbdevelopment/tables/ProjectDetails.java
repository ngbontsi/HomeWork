package com.bontsi.nbdevelopment.tables;

import android.content.ContentValues;

import com.bontsi.nbdevelopment.dao.DBSchema;

public class ProjectDetails {

	private int id;
	private String project;
	private String projectDate;
	private String projectStatus;

	public ProjectDetails() {
		super();

	}

	public ProjectDetails(String project, String projectDate,
			String projectStatus) {
		super();
		this.project = project;
		this.projectDate = projectDate;
		this.projectStatus = projectStatus;
	}

	public ContentValues getValues() {
		final ContentValues cv = new ContentValues();
		cv.put(DBSchema.TB_PRODUCTS.PRODUCTNAME, project);
		cv.put(DBSchema.TB_PRODUCTS.PROJECTDATE, projectDate);
		cv.put(DBSchema.TB_PRODUCTS.STATUS, projectStatus);
		return cv;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProjectDate() {
		return projectDate;
	}

	public void setProjectDate(String projectDate) {
		this.projectDate = projectDate;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public void setProjectId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
