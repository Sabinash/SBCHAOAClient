package com.sbchaoa_application.userfragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sbchaoa_application.R;
import com.sbchaoa_application.common.ServiceHandler;

public class VotingPollingresult extends Fragment {
	private static String getUrl = "http://172.16.35.244:9000/api/mntnbillstatus/getList";
	private ListView mListView;
	private ProgressDialog pDialog;
	private ArrayList<HashMap<String, String>> listCatagory;
	String selectedCatagory;
	HashMap<String, String> map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_voting_pollresult,
				container, false);
		mListView = (ListView) rootView.findViewById(R.id.votingpolllist);
		new GetContacts().execute();
		return rootView;
	}

	private class GetContacts extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(getUrl, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			listCatagory = new ArrayList<HashMap<String, String>>();

			try {
				JSONArray jsonarray = new JSONArray(jsonStr);
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject obj = jsonarray.getJSONObject(i);
					String catagoryName = obj.getString("catagoryName");
					String flatNbr = obj.getString("flatNbr");
					String status = obj.getString("status");
					HashMap<String, String> mCatagory = new HashMap<String, String>();

					mCatagory.put("catagoryName", catagoryName);
					mCatagory.put("flatNbr", flatNbr);
					mCatagory.put("status", status);

					listCatagory.add(mCatagory);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return jsonStr;
		}

		@Override
		protected void onPostExecute(String result) {
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (result.equalsIgnoreCase("[]")) {
				Toast.makeText(getActivity(), "No Records Found",
						Toast.LENGTH_LONG).show();
			}
			ListAdapter adapter = new SimpleAdapter(getActivity(),
					listCatagory, R.layout.list_pollresultinfo, new String[] {
							"candidateName", "flatNbr", "status" }, new int[] {
							R.id.TextView01, R.id.TextView02, R.id.TextView03 });
			mListView.setAdapter(adapter);

		}
	}
}
