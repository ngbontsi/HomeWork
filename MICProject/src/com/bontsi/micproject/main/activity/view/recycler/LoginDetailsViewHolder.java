package com.bontsi.micproject.main.activity.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.bontsi.micproject.R;

public class LoginDetailsViewHolder extends RecyclerView.ViewHolder {
	public RelativeLayout container;
	public EditText username, password;
	public ImageButton loginbutton;

	public LoginDetailsViewHolder(View itemView) {
		super(itemView);

		setupViews(itemView);
	}

	private void setupViews(View view) {
		container = (RelativeLayout) view.findViewById(R.id.login_container);
		username = (EditText) view.findViewById(R.id.username);
		password = (EditText) view.findViewById(R.id.password);
		loginbutton = (ImageButton) view.findViewById(R.id.login_button);
	}

}
