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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sbchaoa_application.R;
import com.sbchaoa_application.R.id;
import com.sbchaoa_application.R.layout;
import com.sbchaoa_application.adapter.LazyAdapter;
import com.sbchaoa_application.common.FileUtility;
import com.sbchaoa_application.common.ServiceHandler;
import com.sbchaoa_application.common.SqliteController;

public class VotingNominateMeFrgament extends Fragment {
	private ListView mListView;
	private static String getUrl = "http://172.16.35.244:8080/nominationcatagory/list";
	private static String postUrl = "http://172.16.35.244:8080/nominatemyself/nominate";
	private ProgressDialog pDialog;
	private ArrayList<HashMap<String, String>> listCatagory;
	String selectedCatagory;
	String name, flatNbr;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_voting_nominateme,
				container, false);
		mListView = (ListView) rootView.findViewById(R.id.catagorylist);
		new GetContacts().execute();
		SqliteController sqliteController = new SqliteController(getActivity());
		name = sqliteController.getName();
		flatNbr = sqliteController.getFlatNbr();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				HashMap<String, String> map = (HashMap<String, String>) mListView
						.getItemAtPosition(position);

				selectedCatagory = map.get("catagoryName");

				//new HttpAsyncTask().execute(postUrl);
			}
		});

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
					String name = obj.getString("catagoryName");
					HashMap<String, String> mCatagory = new HashMap<String, String>();
					mCatagory.put("catagoryName", name);

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
					listCatagory, R.layout.list_item,
					new String[] { "catagoryName" },
					new int[] { R.id.textView1 });
			mListView.setAdapter(adapter);

			

		}
	}

	/*private class HttpAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			
			 * pDialog = new ProgressDialog(getActivity());
			 * pDialog.setMessage("Please wait...");
			 * pDialog.setCancelable(false); pDialog.show();
			 

		}

		@Override
		protected String doInBackground(String... urls) {

			JSONObject jsonObject = new JSONObject();

			try {

				jsonObject.accumulate("name", flatNbr);
				jsonObject.accumulate("flatNbr", name);
				jsonObject.accumulate("catagoryName", selectedCatagory);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return FileUtility.POST(urls[0], jsonObject);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

			if (pDialog.isShowing())
				pDialog.dismiss();

		}
	}*/
}
