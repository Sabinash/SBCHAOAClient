package com.sbchaoa_application.userfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbchaoa_application.R;
import com.sbchaoa_application.common.SqliteController;

public class FinalVotingListFragment extends Fragment {
	TextView candidateName,catagoryName;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(
				R.layout.fragment_finalvoting, container,
				false);
		SqliteController sqliteController =new SqliteController(getActivity());
		candidateName = (TextView)rootView.findViewById(R.id.candidateName);
		candidateName.setText(sqliteController.getFlatNbr());
		catagoryName = (TextView)rootView.findViewById(R.id.catagoryName);
		//String catagory = getArguments().getString("selectedCatagory");
		//catagoryName.setText(catagory);
	
		return rootView;	
		
	}
}
