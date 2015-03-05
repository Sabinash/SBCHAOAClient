package com.sbchaoa_application;


import com.sbchaoa_application.adminfragment.AdminDashBoardFragment;
import com.sbchaoa_application.userfragment.UserDashBoardFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class DashBoardFragment extends Fragment implements OnClickListener {

	private Button btnUser, btnAdmin;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_dashboard,
				container, false);
		btnUser = (Button) rootView.findViewById(R.id.userbutton);
		btnAdmin = (Button) rootView.findViewById(R.id.adminbutton);
		btnUser.setOnClickListener(this);
		btnAdmin.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.userbutton:
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.container, new UserDashBoardFragment())
					.commit();
			break;
		case R.id.adminbutton:
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.container, new AdminDashBoardFragment())
					.commit();
			break;

		}
	}

}
