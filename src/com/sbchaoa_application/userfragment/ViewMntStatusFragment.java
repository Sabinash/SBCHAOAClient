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
import android.widget.ListView;
import android.widget.Toast;

import com.sbchaoa_application.R;
import com.sbchaoa_application.R.id;
import com.sbchaoa_application.R.layout;
import com.sbchaoa_application.adapter.CustomAdapter;
import com.sbchaoa_application.adapter.LazyAdapter;
import com.sbchaoa_application.common.ServiceHandler;
import com.sbchaoa_application.common.SqliteController;

public class ViewMntStatusFragment extends Fragment{
	
	private ListView mListView;
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> listCatagory;
	private static String getUrl="http://172.16.35.244:8080/mntnbillstatus";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_mntstatus,
				container, false);
		mListView = (ListView) rootView.findViewById(R.id.mntstslist);
	
		SqliteController sqliteController = new SqliteController(getActivity());
		String flatNbr =sqliteController.getName();
		getUrl= getUrl+"/"+flatNbr;
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
					String flatNbr = obj.getString("flatNbr");
					String datetime = obj.getString("datetime");
					String paymentStatus = obj.getString("paymentStatus");
					String modeOfPayment = obj.getString("modeOfPayment");
				HashMap<String,String> mCatagory = new HashMap<String, String>();
					mCatagory.put("flatNbr", flatNbr);
					mCatagory.put("datetime", datetime);
					mCatagory.put("paymentStatus", paymentStatus);
					mCatagory.put("modeOfPayment", modeOfPayment);

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
				Toast.makeText(getActivity(), "No Records Found", Toast.LENGTH_LONG).show();
			}
			mListView.setAdapter(new CustomAdapter(getActivity(), listCatagory));
	
		}
	}
}