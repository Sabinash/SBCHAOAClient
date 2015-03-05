package com.sbchaoa_application.adminfragment;

import com.sbchaoa_application.R;
import com.sbchaoa_application.R.id;
import com.sbchaoa_application.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AdminDashBoardFragment extends Fragment implements OnClickListener {

	private Button crtNomCatagory, viewNom, viewRegUser, udtWaterbill,
			udtMaintBill, btnBroadcast;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_admindashboard,
				container, false);

		crtNomCatagory = (Button) rootView.findViewById(R.id.createnominationcatagory);
		viewNom = (Button) rootView.findViewById(R.id.viewnominationlist);
		viewRegUser = (Button) rootView.findViewById(R.id.viewregistereduser);
		udtMaintBill = (Button) rootView.findViewById(R.id.udtmaintenancebill);
		udtWaterbill = (Button) rootView.findViewById(R.id.udtwaterbill);
		btnBroadcast = (Button) rootView.findViewById(R.id.broadcast);

		crtNomCatagory.setOnClickListener(this);
		viewNom.setOnClickListener(this);
		viewRegUser.setOnClickListener(this);
		udtMaintBill.setOnClickListener(this);
		udtWaterbill.setOnClickListener(this);
		btnBroadcast.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.createnominationcatagory:
			getFragmentManager().beginTransaction().addToBackStack(null)
			.replace(R.id.container, new CreateNominationFragment())
			.commit();
			break;
		case R.id.viewnominationlist:

			break;
		case R.id.viewregistereduser:

			break;
		case R.id.udtmaintenancebill:

			break;
		case R.id.udtwaterbill:

			break;
		case R.id.broadcast:

			break;
		}
	}

}
