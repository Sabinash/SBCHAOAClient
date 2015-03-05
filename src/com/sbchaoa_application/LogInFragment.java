package com.sbchaoa_application;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sbchaoa_application.common.Constant;
import com.sbchaoa_application.common.FileUtility;
import com.sbchaoa_application.common.NothingSelectedSpinnerAdapter;
import com.sbchaoa_application.model.LogIn;

public class LogInFragment extends Fragment {

	Button btnLogIn, btnSignUp;
	EditText editFlatNbr, editPswd;
	private Spinner spnType;
	String spinnerValue;
	ProgressDialog pDialog;
	private List<String> types;
	private ArrayAdapter<String> adapter, typesAdapter;
	String logInUrl = Constant.IP_ADDRESS + "/api/owner/login";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);

		btnLogIn = (Button) rootView.findViewById(R.id.btnlogIn);
		btnSignUp = (Button) rootView.findViewById(R.id.btnsignup);
		editFlatNbr = (EditText) rootView.findViewById(R.id.flatnbr);
		editPswd = (EditText) rootView.findViewById(R.id.mpassword);
		spnType = (Spinner) rootView.findViewById(R.id.sptype);

		types = new ArrayList<String>();
		types.add("Owner");
		types.add("Co-Owner");

		typesAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, types);
		typesAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnType.setAdapter(typesAdapter);
		spnType.setAdapter(new NothingSelectedSpinnerAdapter(typesAdapter,
				R.layout.contact_spinner_row_nothing_selected_types,
				getActivity()));

		spnType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position != 0) {
					spinnerValue = spnType.getSelectedItem().toString();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		btnLogIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new HttpAsyncTask().execute(logInUrl);
			}
		});
		btnSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getFragmentManager().beginTransaction().addToBackStack(null)
						.replace(R.id.container, new PlaceholderFragment())
						.commit();
			}
		});
		
		if (isConnected()) {
			Toast.makeText(getActivity(), "You are conncted", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(getActivity(),
					"You are NOT conncted yet,Please turn On network",
					Toast.LENGTH_LONG).show();
		}
		return rootView;
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			LogIn mLogIn = new LogIn();
			mLogIn.setType(spinnerValue);
			mLogIn.setFlatNbr(editFlatNbr.getText().toString());
			mLogIn.setPswd(editPswd.getText().toString());
			Gson gson = new Gson();
			return FileUtility.POST(urls[0], gson.toJson(mLogIn).toString());
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject jsonObject = new JSONObject(result);
				String message = jsonObject.getString("message");
				Toast.makeText(getActivity(), message, Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public boolean isConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) getActivity()
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}
}