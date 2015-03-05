package com.sbchaoa_application.userfragment;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.sbchaoa_application.common.FileUtility;
import com.sbchaoa_application.common.ServiceHandler;
import com.sbchaoa_application.common.SqliteController;

public class Votingcast_nomineesFragment extends Fragment {

	private ListView mListView;
	private String getUrl = "http://172.16.35.244:8080/nominatemyself";
	private ProgressDialog pDialog;
	private ArrayList<HashMap<String, String>> listCatagory;
	String selectedCatagory ;
	String candidateName;
	String flatNbr;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(
				R.layout.fragment_voting_nomination_candidatelist, container,
				false);	
		mListView = (ListView)rootView.findViewById(R.id.nominationcandidatelist);
		selectedCatagory = getArguments().getString("selectedCatagory"); 
		getUrl= getUrl+"/"+selectedCatagory;
		SqliteController sqliteController =new SqliteController(getActivity());
		flatNbr =sqliteController.getName();
		new GetContacts().execute();
		
		return rootView;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
	//	new GetContacts().execute();
		super.onResume();
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
					String name = obj.getString("name");
					String flatNbr = obj.getString("flatNbr");
					String catagoryName = obj.getString("catagoryName");
					String dateTime = obj.getString("dateTime");
					HashMap<String, String> mCatagory = new HashMap<String, String>();
					
					mCatagory.put("name", name);
					mCatagory.put("flatNbr", flatNbr);
					mCatagory.put("catagoryName", catagoryName);
					mCatagory.put("dateTime", dateTime);

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
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
		//mListView.setAdapter(new VotingNomineesAdapter(getActivity(), listCatagory));
			ListAdapter adapter = new SimpleAdapter(getActivity(),
					listCatagory, R.layout.list_item,
					new String[] { "name" },
					new int[] { R.id.textView1 });
			mListView.setAdapter(adapter);
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
		HashMap<String, String> map = (HashMap<String, String>)mListView
						.getItemAtPosition(position);

			 candidateName = map.get("name");
			
				Toast.makeText(getActivity(), candidateName,
						Toast.LENGTH_LONG).show();
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
				 myAlertDialog.setTitle("Do You Want to Continue...");
				 myAlertDialog.setMessage("Thanks For voting"+" " +candidateName+" "+ "As Your"+" " + selectedCatagory);
				 myAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

				  public void onClick(DialogInterface arg0, int arg1) {
				  // do something when the OK button is clicked
					//  new HttpAsyncTask().execute("http://172.16.35.244:8080/pollresult");
					  getFragmentManager().beginTransaction().addToBackStack(null)
						.replace(R.id.container, new VotingPagefragment())
						.commit();
				  }});
				 myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				       
				  public void onClick(DialogInterface arg0, int arg1) {
				  // do something when the Cancel button is clicked
				  }});
				 myAlertDialog.setCancelable(false);
				 myAlertDialog.show();
			
			}
		});
			
		}
}
	
	/*private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			JSONObject jsonObject = new JSONObject();
			
			try {
				jsonObject.accumulate("flatNbr", flatNbr);
				jsonObject.accumulate("candidateName",candidateName );
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

		}
	}*/
}
