package com.sbchaoa_application;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import com.sbchaoa_application.common.SqliteController;
import com.sbchaoa_application.model.Owner;

public class PlaceholderFragment extends Fragment implements OnClickListener {

	public PlaceholderFragment() {
	}

	private Spinner spinner1, spinner2, spinner3, spinner4;
	private Button btnRegister;
	private ArrayAdapter<String> adapter, typesAdapter;
	private List<String> flats, oneFlat, twoFlats, threeFlats;
	String selectedvalue, data, floorNbr, flatnbr;
	private EditText mName, mPassword;
	private List<String> blocks;
	private List<String> floors;
	private List<String> types;
	private String type;
	private String logInUrl = Constant.IP_ADDRESS + "/api/owner/registration";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

		spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
		spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
		spinner3 = (Spinner) rootView.findViewById(R.id.spinner3);
		spinner4 = (Spinner) rootView.findViewById(R.id.spinner4);

		mName = (EditText) rootView.findViewById(R.id.name);
		mPassword = (EditText) rootView.findViewById(R.id.mpassword);
		btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

		types = new ArrayList<String>();
		types.add("Owner");
		types.add("Co-Owner");

		blocks = new ArrayList<String>();
		blocks.add("A");
		blocks.add("B");
		blocks.add("C");

		floors = new ArrayList<String>();
		for (int i = 2; i <= 11; i++) {
			floors.add(String.valueOf(i));
		}

		flats = new ArrayList<String>();
		for (int i = 1; i <= 4; i++) {
			flats.add(String.valueOf(i));
		}
		oneFlat = new ArrayList<String>();
		oneFlat.add("1");
		twoFlats = new ArrayList<String>();
		for (int i = 1; i <= 2; i++) {
			twoFlats.add(String.valueOf(i));
		}
		threeFlats = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			threeFlats.add(String.valueOf(i));
		}

		typesAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, types);
		typesAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(typesAdapter);
		spinner1.setAdapter(new NothingSelectedSpinnerAdapter(typesAdapter,
				R.layout.contact_spinner_row_nothing_selected_types,
				getActivity()));

		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if (position != 0) {
					type = spinner1.getSelectedItem().toString();
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedvalue = spinner2.getSelectedItem().toString();
				data = spinner3.getSelectedItem().toString();
				if ("B".equalsIgnoreCase(selectedvalue)) {
					if ("11".equalsIgnoreCase(data)) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, twoFlats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					} else {

						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, flats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}

				}

				if ("A".equalsIgnoreCase(selectedvalue)
						|| "C".equalsIgnoreCase(selectedvalue)) {
					if ("11".equalsIgnoreCase(data)) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, oneFlat);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}
				}
				if ("A".equalsIgnoreCase(selectedvalue)
						|| "C".equalsIgnoreCase(selectedvalue)) {
					if ("10".equalsIgnoreCase(data)) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item,
								threeFlats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		spinner3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				data = spinner3.getSelectedItem().toString();

				if ("A".equalsIgnoreCase(selectedvalue)
						|| "C".equalsIgnoreCase(selectedvalue)) {
					// adapter.notifyDataSetChanged();
					if (("10".equalsIgnoreCase(data))) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item,
								threeFlats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					} else

					if (("11".equalsIgnoreCase(data) && "A"
							.equalsIgnoreCase(selectedvalue))) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, oneFlat);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					} else {

						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, flats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}

				}
				adapter.notifyDataSetChanged();
				if ("B".equalsIgnoreCase(selectedvalue)) {
					adapter.notifyDataSetChanged();
					if (("11".equalsIgnoreCase(data))) {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, twoFlats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();

					} else {
						adapter.notifyDataSetChanged();
						adapter = new ArrayAdapter<String>(getActivity(),
								android.R.layout.simple_spinner_item, flats);

						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						adapter.notifyDataSetChanged();
						spinner4.setAdapter(adapter);
						adapter.notifyDataSetChanged();
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		spinner4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				flatnbr = spinner4.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		
		btnRegister.setOnClickListener(this);

		return rootView;
	}

	

	@Override
	public void onClick(View arg0) {

		new HttpAsyncTask().execute(logInUrl);

	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			Owner owner = new Owner();
			owner.setType(type);
			owner.setName(mName.getText().toString());
			owner.setPswd(mPassword.getText().toString());
			owner.setFlatNbr(selectedvalue + data + flatnbr);
			owner.setIsAdmin("0");
			Gson gson = new Gson();
			return FileUtility.POST(urls[0], gson.toJson(owner).toString());
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			String status = "";
			try {
				JSONObject Obj = new JSONObject(result);
				status = Obj.getString("status");		
			} catch (JSONException e) {
				e.printStackTrace();
			}

			if (status.equalsIgnoreCase("EXIST")) {
				Toast.makeText(getActivity(), "Owner/Co-Owner Already EXIST",
						Toast.LENGTH_LONG).show();
			} else {
				try {
					String[] jsonarray= status.split(",");
					for (int i = 0; i < jsonarray.length; i++) {
						
						String type = jsonarray[1];
						String flatnbrr = jsonarray[0].substring(1);
						String name = jsonarray[2];
						SqliteController sqliteController = new SqliteController(getActivity());
						sqliteController.insertRecords(type, flatnbrr, name);
						sqliteController.insertFlag("true");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				getFragmentManager().beginTransaction().addToBackStack(null)
				.replace(R.id.container, new DashBoardFragment())
				.commit();
			}
			

		}
	}

}