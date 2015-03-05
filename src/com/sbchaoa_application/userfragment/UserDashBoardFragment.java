package com.sbchaoa_application.userfragment;

import com.sbchaoa_application.HelpFragment;
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
import android.widget.Toast;

public class UserDashBoardFragment extends Fragment {

	Button btnVote, btnMntSts, btnWtrSts, btnHelp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_userdashboard,
				container, false);
		btnVote = (Button) rootView.findViewById(R.id.vote);
		btnMntSts = (Button) rootView.findViewById(R.id.maintenancests);
		btnWtrSts = (Button) rootView.findViewById(R.id.wtrbillsts);
		btnHelp = (Button) rootView.findViewById(R.id.helpline);

		btnMntSts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
						.replace(R.id.container, new ViewMntStatusFragment())
						.commit();
			}
		});
		btnWtrSts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new ViewWtrBillStatusfrgament())
				.commit();
			}
		});
		btnHelp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new HelpFragment())
				.commit();
			}
		});
		
		btnVote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new VotingPagefragment())
				.commit();
			}
		});

		return rootView;
	}
}
