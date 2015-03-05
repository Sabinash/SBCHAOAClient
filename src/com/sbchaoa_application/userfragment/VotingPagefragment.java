package com.sbchaoa_application.userfragment;

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

public class VotingPagefragment extends Fragment {

	private Button btnNominateme, btnCastVote, btnPollRslt;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_votingpage,
				container, false);
		btnNominateme = (Button) rootView.findViewById(R.id.nominatemyself);
		btnCastVote = (Button) rootView.findViewById(R.id.casturvote);
		btnPollRslt = (Button) rootView.findViewById(R.id.pollresult);

		btnNominateme.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new VotingNominateMeFrgament())
				.commit();
			}
		});
		btnCastVote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new VotingCastFragment())
				.commit();
			}
		});
		btnPollRslt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "PollResult", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new VotingPollingresult())
				.commit();
			}
		});
		return rootView;
	}

}
